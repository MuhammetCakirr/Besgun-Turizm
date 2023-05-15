using System.ComponentModel.DataAnnotations;

namespace webVTYSvize.Data
{
    public class RezervasyonView
    {
        public RezervasyonView(int idRezervasyon, DateTime tarihi, int yetiskinSayisi, int cocukSayisi, DateTime girisTarihi, DateTime cikisTarihi,int idMusteri, string adi, string soyadi, string telefonNumarasi, string epostaAdresi, int hizmetTabloId, string hizmetTabloAdi)
        {
            this.idRezervasyon = idRezervasyon;
            this.tarihi = tarihi;
            this.yetiskinSayisi = yetiskinSayisi;
            this.cocukSayisi = cocukSayisi;
            this.girisTarihi = girisTarihi;
            this.cikisTarihi = cikisTarihi;
            this.idMusteri= idMusteri;
            this.adi = adi;
            this.soyadi = soyadi;
            this.telefonNumarasi = telefonNumarasi;
            this.epostaAdresi = epostaAdresi;
            this.hizmetTabloId = hizmetTabloId;
            this.hizmetTabloAdi = hizmetTabloAdi;
        }
        [Key]
        public int idRezervasyon { get; set; }
        public DateTime tarihi { get; set; }
        public int yetiskinSayisi { get; set; }
        public int cocukSayisi { get; set; }
        public DateTime girisTarihi { get; set; }
        public DateTime cikisTarihi { get; set; }
        public int idMusteri { get; set; }
        public string adi { get; set; }
        public string soyadi { get; set; }
        public string telefonNumarasi { get; set; }
        public string epostaAdresi { get; set; }
        public int hizmetTabloId { get; set; }
        public string hizmetTabloAdi { get; set; }
    }
}
