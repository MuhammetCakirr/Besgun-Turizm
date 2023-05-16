using Microsoft.AspNetCore.Mvc;
using System.Data.Entity;
using System.Text.RegularExpressions;
using webVTYSvize.Data;

namespace webVTYSvize.Controllers
{
    public class HizmetController : Controller
    {
        private readonly ILogger<HizmetController> _logger;
        private readonly MyDbContext _context;
        public HizmetController(ILogger<HizmetController> logger, MyDbContext context)
        {
            _logger = logger;
            _context = context;
        }

        public ActionResult OtelDetay(string bilgiler)
        {
            string[] veriler = bilgiler.Split('?');
            // 0 id 1 giristarihi 2 cikistarihi 
            Console.WriteLine(veriler[0] + "?" + veriler[1]+"?"+ veriler[2]);
            OtelView otel = _context.OtelView.Where(o => o.idOtel == int.Parse(veriler[0])).FirstOrDefault();

            string dateString = "2023-05-05";
            DateTime girisTarihi = DateTime.ParseExact(veriler[1], "yyyy-MM-dd", System.Globalization.CultureInfo.InvariantCulture);
            DateTime cikisTarihi = DateTime.ParseExact(veriler[2], "yyyy-MM-dd", System.Globalization.CultureInfo.InvariantCulture);
            TimeSpan gunSayisi = cikisTarihi - girisTarihi;
            ViewBag.id = veriler[0];
            ViewBag.girisTarihi = veriler[1];
            ViewBag.cikisTarihi = veriler[2];
            ViewBag.fiyatCarpan = gunSayisi.Days;
            return View(otel);
        }
        
        public ActionResult TekneTurDetay(int id)
        {

            TekneTurView tekneTur = _context.TekneTurView.Where(t => t.idTekneTur == id).FirstOrDefault();
            ViewBag.gunBir = ParantezIceriginiAyir(tekneTur.gunBir);
            ViewBag.gunIki = ParantezIceriginiAyir(tekneTur.gunIki);
            ViewBag.gunUc = ParantezIceriginiAyir(tekneTur.gunUc);
            ViewBag.gunDort = ParantezIceriginiAyir(tekneTur.gunDort);
            ViewBag.gunBes = ParantezIceriginiAyir(tekneTur.gunBes);
            return View(tekneTur);
        }

        public ActionResult YurtdisiTurDetay(int id)
        {

            YurtdisiTurView yurtdisiTur = _context.YurtdisiTurView.Where(y => y.idYurtdisiTur == id).FirstOrDefault();
            ViewBag.gunBir = ParantezIceriginiAyir(yurtdisiTur.gunBir);
            ViewBag.gunIki = ParantezIceriginiAyir(yurtdisiTur.gunIki);
            ViewBag.gunUc = ParantezIceriginiAyir(yurtdisiTur.gunUc);
            ViewBag.gunDort = ParantezIceriginiAyir(yurtdisiTur.gunDort);
            ViewBag.gunBes = ParantezIceriginiAyir(yurtdisiTur.gunBes);
            return View(yurtdisiTur);
        }
        
        static string ParantezIceriginiAyir(string deger)
        {
            // Parantez içindeki ifadeyi bulmak için bir düzenli ifade kullanın
            Regex regex = new Regex(@"\((.*?)\)");
            Match match = regex.Match(deger);

            // Eğer parantez içindeki ifade bulunursa, bu ifadeyi döndürün
            if (match.Success)
            {
                return match.Groups[1].Value;
            }

            return null;
        }

    }
}
