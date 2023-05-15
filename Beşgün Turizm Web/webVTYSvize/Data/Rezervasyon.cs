using System.ComponentModel.DataAnnotations;

namespace webVTYSvize.Data
{
    public class Rezervasyon
    {

        public Rezervasyon(int idRezervasyon, DateTime tarihi, int yetiskinSayisi, int cocukSayisi, int Hizmet_idHizmet, int Musteri_idMusteri, DateTime girisTarihi, DateTime cikisTarihi)
        {
            this.idRezervasyon = idRezervasyon;
            this.tarihi = tarihi;
            this.yetiskinSayisi = yetiskinSayisi;
            this.cocukSayisi = cocukSayisi;
            this.Hizmet_idHizmet = Hizmet_idHizmet;
            this.Musteri_idMusteri = Musteri_idMusteri;
            this.girisTarihi = girisTarihi;
            this.cikisTarihi = cikisTarihi;
        }
        //public Rezervasyon(Rezervasyon rezervasyon)
        //{
        //    this.idRezervasyon = rezervasyon.idRezervasyon;
        //    this.tarihi = rezervasyon.tarihi;
        //    this.yetiskinSayisi = rezervasyon.yetiskinSayisi;
        //    this.cocukSayisi = rezervasyon.cocukSayisi;
        //    this.Hizmet_idHizmet = rezervasyon.Hizmet_idHizmet;
        //    this.Musteri_idMusteri = rezervasyon.Musteri_idMusteri;
        //    this.girisTarihi = rezervasyon.girisTarihi;
        //    this.cikisTarihi = rezervasyon.cikisTarihi;
        //    this.cikisTarihi = rezervasyon.cikisTarihi;
        //    this.hizmet=rezervasyon.hizmet;
        //    this.Musteri=rezervasyon.Musteri;
        //}
        [Key]
        public int idRezervasyon { get; set; }
        public DateTime tarihi { get; set; }
        public int yetiskinSayisi { get; set; }
        public int cocukSayisi { get; set; }
        public int Hizmet_idHizmet { get; set; }
        public Hizmet? hizmet { get; set; }
        public int Musteri_idMusteri { get; set; }
        public Musteri? Musteri { get; set; }
        public DateTime girisTarihi { get; set; }
        public DateTime cikisTarihi { get; set; }

    }
}
