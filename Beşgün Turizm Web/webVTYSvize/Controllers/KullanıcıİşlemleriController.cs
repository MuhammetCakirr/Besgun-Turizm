using Google.Protobuf.WellKnownTypes;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore.Metadata;
using Microsoft.Extensions.Primitives;
using webVTYSvize.Data;
using webVTYSvize.Models;

namespace webVTYSvize.Controllers
{
    public class KullanıcıİşlemleriController : Controller
    {
        private readonly ILogger<KullanıcıİşlemleriController> _logger; 
        private readonly MyDbContext _context;
        public static bool isLogin=false;
        public static bool isSign = false;
        public static Musteri _musteri;
        public KullanıcıİşlemleriController(ILogger<KullanıcıİşlemleriController> logger, MyDbContext context)
        {
            _logger = logger;
            _context = context;
        }
        
        
        [HttpGet]
        public IActionResult Kayıt()
        {
            if (isLogin)
            {
                return RedirectToAction("Anasayfa", "Anasayfa");
            }
            return View();
        }
        [HttpPost]
        public IActionResult Kayıt(string name, string surname, string email, string phone, string password, DateTime date,string loginEmail,string loginPassword)
        {
            if (!isLogin)
            {
                if (name != null)
                {
                    var id = _context.Musteri.Count();
                    _context.Musteri.Add(new Musteri(id, name, surname, password, phone, email, date));
                    _context.SaveChanges();
                    _musteri = _context.Musteri.FirstOrDefault(m=>m.idMusteri==id);
                    isSign = true;
                    return View();
                }
                if (loginEmail != null)
                {
                    Musteri musteri = _context.Musteri.FirstOrDefault(m => m.epostaAdresi == loginEmail && m.sifre == loginPassword);
                    if (musteri != null)
                    {
                        isLogin = true;
                        _musteri = new Musteri(musteri.idMusteri,musteri.adi,musteri.soyadi,musteri.sifre,musteri.telefonNumarasi,musteri.epostaAdresi,musteri.dogumTarihi);
                        return RedirectToAction("Anasayfa", "Anasayfa");
                    }
                    else
                    {
                        return View();
                    }
                }
            }
            else
            {
                return RedirectToAction("Anasayfa", "Anasayfa");
            }
           

            return View();

        }
        
        
        [HttpGet]
        public IActionResult Ayarlar()
        {
            if (isLogin)
            {
                List<RezervasyonView> rezervasyonlar = _context.RezervasyonView.Where(r => r.idMusteri==_musteri.idMusteri).ToList();
                List<RezervasyonViewModel> rezervasyonlarView = new List<RezervasyonViewModel>();
                foreach (var rezervasyon in rezervasyonlar)
                {
                    if (rezervasyon.hizmetTabloAdi=="otel")
                    {
                        OtelView otel = _context.OtelView.Where(r => r.idOtel==rezervasyon.hizmetTabloId).FirstOrDefault();
                        rezervasyonlarView.Add(new RezervasyonViewModel() { Rezervasyon = rezervasyon, HizmetBase = otel });
                    }
                    else if (rezervasyon.hizmetTabloAdi == "teknetur")
                    {
                        TekneTurView tekneTur = _context.TekneTurView.Where(r => r.idTekneTur == rezervasyon.hizmetTabloId).FirstOrDefault();
                        rezervasyonlarView.Add(new RezervasyonViewModel() { Rezervasyon = rezervasyon, HizmetBase = tekneTur });
                    }
                    else
                    {
                        YurtdisiTurView yurtdisiTur = _context.YurtdisiTurView.Where(r => r.idYurtdisiTur == rezervasyon.hizmetTabloId).FirstOrDefault();
                        rezervasyonlarView.Add(new RezervasyonViewModel() { Rezervasyon = rezervasyon, HizmetBase = yurtdisiTur });
                    }
                    
                }
                ViewBag.Message = "Merhabalar  " + _musteri.adi;
                ViewBag.sifre = _musteri.sifre;
                ViewBag.dogumTarihi = _musteri.dogumTarihi;
                
                return View(rezervasyonlarView);  
            }
            return RedirectToAction("Kayıt");
        }
        [HttpPost]
        [ValidateAntiForgeryToken]
        public IActionResult Ayarlar(string hesap,string adi,string soyadi, string eposta, string telefonNumarasi,string sifre,DateTime dogumTarihi) 
        {
            if (hesap!=null && adi==null)
            {
                if (hesap=="cikisYap")
                {
                    _musteri = null;
                    isLogin = false;
                    return RedirectToAction("Kayıt");
                }
                else if (hesap=="hesabiSil")
                {
                    var musteri = _context.Musteri.FirstOrDefault(m => m.idMusteri == _musteri.idMusteri);
                    if (musteri != null)
                    {
                        _context.Musteri.Remove(musteri);
                        _context.SaveChanges();
                        _musteri = null;
                        isLogin = false;
                        return RedirectToAction("Kayıt");
                    }  
                }

            }
            else
            {
                var musteri = _context.Musteri.FirstOrDefault(m => m.idMusteri == _musteri.idMusteri);

                if (musteri != null)
                {
                    musteri.idMusteri = musteri.idMusteri;
                    musteri.adi = adi;
                    musteri.soyadi = soyadi;
                    musteri.sifre = sifre;
                    musteri.telefonNumarasi = telefonNumarasi;
                    musteri.epostaAdresi = eposta;
                    musteri.dogumTarihi = dogumTarihi;

                    _context.SaveChanges();
                    _musteri = musteri;
                    
                    List<RezervasyonView> rezervasyonlar = _context.RezervasyonView.Where(r => r.idMusteri == _musteri.idMusteri).ToList();
                    List<RezervasyonViewModel> rezervasyonlarView = new List<RezervasyonViewModel>();
                    foreach (var rezervasyon in rezervasyonlar)
                    {
                        if (rezervasyon.hizmetTabloAdi == "otel")
                        {
                            OtelView otel = _context.OtelView.Where(r => r.idOtel == rezervasyon.hizmetTabloId).FirstOrDefault();
                            rezervasyonlarView.Add(new RezervasyonViewModel() { Rezervasyon = rezervasyon, HizmetBase = otel });
                        }
                        else if (rezervasyon.hizmetTabloAdi == "teknetur")
                        {
                            TekneTurView tekneTur = _context.TekneTurView.Where(r => r.idTekneTur == rezervasyon.hizmetTabloId).FirstOrDefault();
                            rezervasyonlarView.Add(new RezervasyonViewModel() { Rezervasyon = rezervasyon, HizmetBase = tekneTur });
                        }
                        else
                        {
                            YurtdisiTurView yurtdisiTur = _context.YurtdisiTurView.Where(r => r.idYurtdisiTur == rezervasyon.hizmetTabloId).FirstOrDefault();
                            rezervasyonlarView.Add(new RezervasyonViewModel() { Rezervasyon = rezervasyon, HizmetBase = yurtdisiTur });
                        }
                    }
                    ViewBag.Message = "Hesabınız Güncellendi";
                    ViewBag.dogumTarihi = _musteri.dogumTarihi;
                    ViewBag.sifre = _musteri.sifre;
                    return View(rezervasyonlarView);
                }
                
            }
            return RedirectToAction("Kayıt");
        }
       
        
        [HttpGet]
        public IActionResult Rezervasyon(string otel, string teknetur, string yurtdisitur)
        {
            if (isLogin)
            {
                if (otel != null)
                {
                    var otelBilgiler = otel.Split('?');
                    // 0  oda tipi çarpanı 1 id 2 giristarihi 3 cikistarihi
                    //Console.WriteLine(otelBilgiler[0]+ otelBilgiler[1] + otelBilgiler[2] + otelBilgiler[3]);
                    var oteldb = _context.Otel.Where(o => o.idOtel == int.Parse(otelBilgiler[1])).FirstOrDefault();
                    ViewBag.id = int.Parse(otelBilgiler[1]);
                    ViewBag.hizmetTuru = "otel";
                    ViewBag.girisTarihi = otelBilgiler[2];
                    ViewBag.cikisTarihi = otelBilgiler[3];
                }
                else if (teknetur != null)
                {
                    ViewBag.id = int.Parse(teknetur);
                    var tekneturverisi = _context.TekneTur.Where(t => t.idTekneTur == int.Parse(teknetur)).FirstOrDefault();
                    var turProgrami = _context.TurProgrami.Where(t => t.idTurProgrami == tekneturverisi.TurProgrami_idTurProgrami).FirstOrDefault();
                    var hizmetBilgisi = _context.HizmetBilgisi.Where(o => o.idHizmetBilgisi == tekneturverisi.HizmetBilgisi_idHizmetBilgisi).FirstOrDefault();
                    ViewBag.hizmetTuru = "teknetur";
                    ViewBag.girisTarihi = turProgrami.baslangicTarihi.ToString("yyyy-MM-dd");
                    ViewBag.cikisTarihi = turProgrami.baslangicTarihi.AddDays(5).ToString("yyyy-MM-dd");

                }
                else if (yurtdisitur != null)
                {
                    ViewBag.id = int.Parse(yurtdisitur);
                    var yurtdisiTur = _context.YurtdisiTur.Where(t => t.idYurtdisiTur == int.Parse(yurtdisitur)).FirstOrDefault();
                    var turProgrami = _context.TurProgrami.Where(t => t.idTurProgrami == yurtdisiTur.TurProgrami_idTurProgrami).FirstOrDefault();
                    var hizmetBilgisi = _context.HizmetBilgisi.Where(o => o.idHizmetBilgisi == yurtdisiTur.HizmetBilgisi_idHizmetBilgisi).FirstOrDefault();
                    ViewBag.hizmetTuru = "yurtdisitur";
                    ViewBag.girisTarihi = turProgrami.baslangicTarihi.ToString("yyyy-MM-dd");
                    ViewBag.cikisTarihi = turProgrami.baslangicTarihi.AddDays(5).ToString("yyyy-MM-dd");
                }
            }
            else
            {
                return RedirectToAction("Kayıt");
            }
            return View();
        }
        [HttpPost]
        public IActionResult Rezervasyon(string bilgiler, string yetiskinSayisi , string cocukSayisi,string empty )
        {
            if (isLogin)
            {
                //   0 id  1 hizmetTuru  2  girisTarihi 3 cikisTarihi
                string[] veriler = bilgiler.Split('?');
                int countHizmet = _context.Hizmet.OrderByDescending(h=>h.idHizmet).FirstOrDefault().idHizmet+1;
                Hizmet newhizmet = new Hizmet(countHizmet, int.Parse(veriler[0]), veriler[1]);
                _context.Hizmet.Add(newhizmet);
                _context.SaveChanges();
                int countRervasyon = _context.Rezervasyon.OrderByDescending(h => h.idRezervasyon).FirstOrDefault().idRezervasyon + 1;
                _context.Rezervasyon.Add(new Rezervasyon(
                     countRervasyon,
                      DateTime.Now,
                      int.Parse(yetiskinSayisi),
                      int.Parse(cocukSayisi),
                      newhizmet.idHizmet,
                      _musteri.idMusteri,
                      DateTime.Parse(veriler[2]),
                      DateTime.Parse(veriler[3])
                      ));
                _context.SaveChanges();
                Console.WriteLine(bilgiler + "?" + yetiskinSayisi + "?" + cocukSayisi);
                return RedirectToAction("Ayarlar");
            }
           
            return View();
        }

       
        [HttpGet]
        public IActionResult RezervasyonDüzenle(string rezervasyonDetay) 
        {
            if (rezervasyonDetay != null)
            {
                RezervasyonView rezervasyon = _context.RezervasyonView.Where(r => r.idRezervasyon == int.Parse(rezervasyonDetay)).FirstOrDefault();
                RezervasyonViewModel rezervasyonlarView = new RezervasyonViewModel();
                if (rezervasyon.hizmetTabloAdi == "otel")
                {
                    OtelView otel = _context.OtelView.Where(r => r.idOtel == rezervasyon.hizmetTabloId).FirstOrDefault();
                    rezervasyonlarView = new RezervasyonViewModel() { Rezervasyon = rezervasyon, HizmetBase = otel };
                }
                else if (rezervasyon.hizmetTabloAdi == "teknetur")
                {
                    TekneTurView tekneTur = _context.TekneTurView.Where(r => r.idTekneTur == rezervasyon.hizmetTabloId).FirstOrDefault();
                    rezervasyonlarView = new RezervasyonViewModel() { Rezervasyon = rezervasyon, HizmetBase = tekneTur };
                }
                else
                {
                    YurtdisiTurView yurtdisiTur = _context.YurtdisiTurView.Where(r => r.idYurtdisiTur == rezervasyon.hizmetTabloId).FirstOrDefault();
                    rezervasyonlarView = new RezervasyonViewModel() { Rezervasyon = rezervasyon, HizmetBase = yurtdisiTur };
                }

                return View(rezervasyonlarView);
            }
            return RedirectToAction("Ayarlar");
           
        }
        [HttpPost]
        public IActionResult RezervasyonDüzenle(string rezervasyonBilgisi, int yetiskinSayisi, int cocukSayisi, DateTime girisTarihi, DateTime cikisTarihi) 
        {
            if (rezervasyonBilgisi != null)
            {
                string[] rezer = rezervasyonBilgisi.Split('?');
                if (rezer[0] =="sil")
                {
                    int id = int.Parse(rezer[1]);
                    var rezervasyon = _context.Rezervasyon.FirstOrDefault(rezer => rezer.idRezervasyon == id);
                    var hizmet = _context.Hizmet.FirstOrDefault(h => h.idHizmet == rezervasyon.Hizmet_idHizmet);
                    _context.Rezervasyon.Remove(rezervasyon);
                    _context.SaveChanges();
                    _context.Hizmet.Remove(hizmet);
                    _context.SaveChanges();

                    var rezervasyonlar = _context.Rezervasyon.Where(r => r.Musteri_idMusteri == _musteri.idMusteri).ToList();
                    ViewBag.Message = "Rezervasyonunuz İptal Edildi";
                    return RedirectToAction("Ayarlar");
                }
                else if (rezer[0]=="güncelle")
                {
                    int id = int.Parse(rezer[1]);
                    var rezervasyon = _context.Rezervasyon.FirstOrDefault(rezer => rezer.idRezervasyon == id);
                    var hizmet = _context.Hizmet.FirstOrDefault(h => h.idHizmet == rezervasyon.Hizmet_idHizmet);
                    if (rezervasyon!=null)
                    {
                        rezervasyon.yetiskinSayisi = yetiskinSayisi;
                        rezervasyon.cocukSayisi = cocukSayisi;
                        rezervasyon.girisTarihi = girisTarihi;
                        rezervasyon.cikisTarihi = cikisTarihi;
                        _context.SaveChanges();
                        return RedirectToAction("Ayarlar");
                    }
                    
                    
                }

            }

            return RedirectToAction("Ayarlar");
        }
   
    }
}
