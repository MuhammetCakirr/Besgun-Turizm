using Google.Protobuf.WellKnownTypes;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.EntityFrameworkCore.Metadata.Conventions;
using Org.BouncyCastle.Ocsp;
using Org.BouncyCastle.Utilities;
using System.Data.Entity;
using webVTYSvize.Data;
using static System.Collections.Specialized.BitVector32;

namespace webVTYSvize.Controllers
{
    public class AnasayfaController : Controller
    {
        private readonly ILogger<AnasayfaController> _logger;
        private readonly MyDbContext _context;
        public AnasayfaController(ILogger<AnasayfaController> logger, MyDbContext context)
        {
            _logger = logger;
            _context = context;
        }

        public IActionResult Anasayfa()
        {
            var otel = _context.Otel.OrderByDescending(o => o.puan).FirstOrDefault();
            var yurtdisiTur = _context.YurtdisiTur.OrderByDescending(y => y.idYurtdisiTur).FirstOrDefault();
            var tekneTur = _context.TekneTur.OrderByDescending(y => y.idTekneTur).FirstOrDefault();
            List<HizmetBilgisi> hizmetBilgisi = _context.HizmetBilgisi.ToList();
            foreach (HizmetBilgisi bilgisi in hizmetBilgisi)
            {
                if (otel.HizmetBilgisi_idHizmetBilgisi == bilgisi.idHizmetBilgisi)
                {
                    otel.hizmetBilgisi = bilgisi;
                }
                if (yurtdisiTur.HizmetBilgisi_idHizmetBilgisi == bilgisi.idHizmetBilgisi)
                {
                    yurtdisiTur.hizmetBilgisi = bilgisi;
                }
                if (yurtdisiTur.HizmetBilgisi_idHizmetBilgisi == bilgisi.idHizmetBilgisi)
                {
                    yurtdisiTur.hizmetBilgisi = bilgisi;
                }
            }

            ViewBag.Otel = otel;
            ViewBag.TekneTur = tekneTur;
            ViewBag.YurtdisiTur = yurtdisiTur;

            return View();
        }

        public IActionResult Hizmetler()
        {
            return View();
        }
        [HttpGet]
        public IActionResult Oteller()
        {
            ViewBag.girisTarihi = DateTime.Now.ToString("yyyy-MM-dd");
            ViewBag.cikisTarihi = DateTime.Now.AddDays(1).ToString("yyyy-MM-dd");
            TimeSpan gunSayisi = DateTime.Now.AddDays(1) - DateTime.Now;
            ViewBag.gunSayisi = (gunSayisi.Days > 1 ? gunSayisi.Days - 1 : 1) + " gece " + 1 + " gün ";
            ViewBag.fiyatCarpan = 1;

            List<OtelView> oteller = _context.OtelView.ToList();

            return View(oteller);
        }

        [HttpPost]
        public IActionResult Oteller(string yıldız, string aralıkMin, string aralıkMax, string[] tatilTemaları, string[] adresler, string sırala, DateTime girisTarihi, DateTime cikisTarihi)
        {

            List<OtelView> oteller = _context.OtelView.ToList();
            if (girisTarihi!= DateTime.MinValue)
            {
                ViewBag.girisTarihi = girisTarihi.ToString("yyyy-MM-dd");
                ViewBag.cikisTarihi = cikisTarihi.ToString("yyyy-MM-dd");
                TimeSpan gunSayisi = cikisTarihi - girisTarihi;
                ViewBag.gunSayisi = (gunSayisi.Days != 1 ? gunSayisi.Days - 1 : gunSayisi.Days) + " gece " + gunSayisi.Days + " gün ";
                ViewBag.fiyatCarpan = gunSayisi.Days;
            }
            else
            {
                ViewBag.girisTarihi = DateTime.Now.ToString("yyyy-MM-dd");
                ViewBag.cikisTarihi = DateTime.Now.AddDays(1).ToString("yyyy-MM-dd");
                TimeSpan gunSayisi = DateTime.Now.AddDays(1) - DateTime.Now;
                ViewBag.gunSayisi = (gunSayisi.Days > 1 ? gunSayisi.Days - 1 : 1) + " gece " + 1 + " gün ";
                ViewBag.fiyatCarpan = 1;
            }
           




            //Yıldız değerine göre arama işlemi
            if (yıldız != null)
            {

                Console.WriteLine("yıldız=" + yıldız);
                switch (yıldız)
                {
                    case "9":
                        oteller = oteller.Where(o => o.puan >= 9).ToList();
                        break;
                    case "8":
                        oteller = oteller.Where(o => o.puan >= 8).ToList();
                        break;
                    case "7":
                        oteller = oteller.Where(o => o.puan >= 7).ToList();
                        break;
                    case "6":
                        oteller = oteller.Where(o => o.puan >= 6).ToList();
                        break;
                    default:
                        break;
                }
            }

            //Fiyat aralığı belirterek arama işlemi
            if (aralıkMin != null)
            {
                oteller = oteller.Where(t => int.Parse(aralıkMin) <= t.fiyat).ToList();
            }
            if (aralıkMax != null)
            {
                oteller = oteller.Where(t => t.fiyat <= int.Parse(aralıkMax)).ToList();
            }

            //Tatil temasına göer işlemler
            if (tatilTemaları != null && tatilTemaları.Count() > 0)
            {
                List<OtelView> temaOtel = new List<OtelView>();
                foreach (string tatiltema in tatilTemaları)
                {
                    temaOtel.AddRange(oteller.Where(t => t.tema == tatiltema).ToList());
                }
                oteller = temaOtel;
            }
            // adrese göre filtreleem
            if (adresler != null && adresler.Count() > 0)
            {
                List<OtelView> bölgeTekneTurları = new List<OtelView>();
                foreach (string bölge in adresler)
                {
                    bölgeTekneTurları.AddRange(oteller.Where(t => t.adres.Contains(bölge)).ToList());
                }
                oteller = bölgeTekneTurları;
            }
            //Sıralama işlemi
            if (sırala != null)
            {
                switch (sırala)
                {
                    case "fiyat_azdan_coka":
                        // Fiyat Az'dan Çok'a sıralama işlemleri burada yapılır.
                        oteller = oteller.OrderBy(p => p.fiyat).ToList();
                        break;
                    case "fiyat_coktan_aza":
                        // Fiyat Çok'tan Az'a sıralama işlemleri burada yapılır.
                        oteller = oteller.OrderByDescending(p => p.fiyat).ToList();
                        break;
                    case "puan_azdan_coka":
                        // Puan Az'dan Çok'a sıralama işlemleri burada yapılır.
                        oteller = oteller.OrderBy(p => p.puan).ToList();
                        break;
                    case "puan_coktan_aza":
                        // Puan Çok'tan Az'a sıralama işlemleri burada yapılır.
                        oteller = oteller.OrderByDescending(p => p.puan).ToList();
                        break;
                    default:
                        // Geçersiz bir sıralama seçeneği gönderildiğinde, varsayılan olarak fiyat_azdan_coka sıralama yapılır.
                        oteller = oteller.OrderBy(p => p.fiyat).ToList();
                        break;
                }

            }


            return View(oteller);
        }
        [HttpGet]
        public IActionResult TekneTurları()
        {
            //List<TekneTur> tekneTurları = _context.TekneTur.ToList();
            //List<HizmetBilgisi> hizmetBilgisi = _context.HizmetBilgisi.ToList();
            //List<TurProgrami> turProgramları = _context.TurProgrami.ToList();
            //foreach (TekneTur tekneTur in tekneTurları)
            //{
            //    foreach (HizmetBilgisi bilgisi in hizmetBilgisi)
            //    {
            //        if (tekneTur.HizmetBilgisi_idHizmetBilgisi == bilgisi.idHizmetBilgisi)
            //        {
            //            tekneTur.hizmetBilgisi = bilgisi;
            //        }
            //    }

            //}
            //foreach (TekneTur tekneTur in tekneTurları)
            //{
            //    foreach (TurProgrami turProgrami in turProgramları)
            //    {
            //        if (tekneTur.TurProgrami_idTurProgrami == turProgrami.idTurProgrami)
            //        {
            //            tekneTur.turProgrami = turProgrami;
            //        }
            //    }

            //}
            List<TekneTurView> tekneTurları=_context.TekneTurView.ToList();
            return View(tekneTurları);
        }
        [HttpPost]
        public IActionResult TekneTurları(string sırala, string minFiyat ,string maxFiyat, string vizeDurumu , string[] bölgeler)
        {
            
            List<TekneTurView> tekneTurları = _context.TekneTurView.ToList();

            if (minFiyat!=null)
            {
                tekneTurları = tekneTurları.Where(t => int.Parse(minFiyat) <= t.fiyat).ToList();
            }
            if (maxFiyat!=null)
            {
                tekneTurları = tekneTurları.Where(t =>  t.fiyat <=int.Parse(maxFiyat)).ToList();
            }
            if (vizeDurumu!=null)
            {
                tekneTurları = tekneTurları.Where(t => t.vizeDurumu == vizeDurumu).ToList();
            }
            if (bölgeler!=null && bölgeler.Count()>0)
            {
                List<TekneTurView> bölgeTekneTurları = new List<TekneTurView>();
                foreach(string bölge in bölgeler)
                {
                    bölgeTekneTurları.AddRange(tekneTurları.Where(t => t.baslangicKonumu == bölge).ToList());
                }
                tekneTurları = bölgeTekneTurları;
            }

            //Sıralama işlemi
            if (sırala != null)
            {
                switch (sırala)
                {
                    case "fiyat_azdan_coka":
                        // Fiyat Az'dan Çok'a sıralama işlemleri burada yapılır.
                        tekneTurları = tekneTurları.OrderBy(t => t.fiyat).ToList();
                        break;
                    case "fiyat_coktan_aza":
                        // Fiyat Çok'tan Az'a sıralama işlemleri burada yapılır.
                        tekneTurları = tekneTurları.OrderByDescending(t => t.fiyat).ToList();
                        break;
                    default:
                        // Geçersiz bir sıralama seçeneği gönderildiğinde, varsayılan olarak fiyat_azdan_coka sıralama yapılır.
                        tekneTurları = tekneTurları.OrderBy(t => t.fiyat).ToList();
                        break;
                }

            }
            return View(tekneTurları);
        }

        [HttpGet]
        public IActionResult YurtdisiTurlar()
        {
            //List<YurtdisiTur> yurtdisiTurlar = _context.YurtdisiTur.ToList();
            //List<HizmetBilgisi> hizmetBilgisi = _context.HizmetBilgisi.ToList();
            //List<TurProgrami> turProgramları = _context.TurProgrami.ToList();
            //foreach (YurtdisiTur yurtdisiTur in yurtdisiTurlar)
            //{
            //    foreach (HizmetBilgisi bilgisi in hizmetBilgisi)
            //    {
            //        if (yurtdisiTur.HizmetBilgisi_idHizmetBilgisi == bilgisi.idHizmetBilgisi)
            //        {
            //            yurtdisiTur.hizmetBilgisi = bilgisi;
            //        }
            //    }

            //}
            //foreach (YurtdisiTur yurtdisiTur in yurtdisiTurlar)
            //{
            //    foreach (TurProgrami turProgrami in turProgramları)
            //    {
            //        if (yurtdisiTur.TurProgrami_idTurProgrami == turProgrami.idTurProgrami)
            //        {
            //            yurtdisiTur.turProgrami = turProgrami;
            //        }
            //    }

            //}
            List<YurtdisiTurView> yurtdisiTurlar = _context.YurtdisiTurView.ToList();
            return View(yurtdisiTurlar);
        }
        [HttpPost]
        public IActionResult YurtdisiTurlar(string sırala, string minFiyat, string maxFiyat, string vizeDurumu, string[] bölgeler)
        {
            
            List<YurtdisiTurView> yurtdisiTurlar = _context.YurtdisiTurView.ToList();

            if (minFiyat != null)
            {
                yurtdisiTurlar = yurtdisiTurlar.Where(t => int.Parse(minFiyat) <= t.fiyat).ToList();
            }
            if (maxFiyat != null)
            {
                yurtdisiTurlar = yurtdisiTurlar.Where(t => t.fiyat <= int.Parse(maxFiyat)).ToList();
            }
            if (vizeDurumu != null)
            {
                yurtdisiTurlar = yurtdisiTurlar.Where(t => t.vizeDurumu == vizeDurumu).ToList();
            }
            if (bölgeler != null && bölgeler.Count() > 0)
            {
                List<YurtdisiTurView> bölgeTekneTurları = new List<YurtdisiTurView>();
                foreach (string bölge in bölgeler)
                {
                    bölgeTekneTurları.AddRange(yurtdisiTurlar.Where(t => t.baslangicKonumu == bölge).ToList());
                }
                yurtdisiTurlar = bölgeTekneTurları;
            }
            //Sıralama işlemi
            if (sırala != null)
            {
                switch (sırala)
                {
                    case "fiyat_azdan_coka":
                        // Fiyat Az'dan Çok'a sıralama işlemleri burada yapılır.
                        yurtdisiTurlar = yurtdisiTurlar.OrderBy(y => y.fiyat).ToList();
                        break;
                    case "fiyat_coktan_aza":
                        // Fiyat Çok'tan Az'a sıralama işlemleri burada yapılır.
                        yurtdisiTurlar = yurtdisiTurlar.OrderByDescending(y => y.fiyat).ToList();
                        break;
                    default:
                        // Geçersiz bir sıralama seçeneği gönderildiğinde, varsayılan olarak fiyat_azdan_coka sıralama yapılır.
                        yurtdisiTurlar = yurtdisiTurlar.OrderBy(y => y.fiyat).ToList();
                        break;
                }

            }
            return View(yurtdisiTurlar);
        }
        public IActionResult İletişim()
        {
            return View();
        }
    }
}
