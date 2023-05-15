using System.ComponentModel.DataAnnotations;

namespace webVTYSvize.Data
{
    public class Musteri
    {
        public Musteri(int idMusteri, string adi, string soyadi, string sifre, string telefonNumarasi, string epostaAdresi, DateTime dogumTarihi)
        {
            this.idMusteri = idMusteri;
            this.adi = adi;
            this.soyadi = soyadi;
            this.sifre = sifre;
            this.telefonNumarasi = telefonNumarasi;
            this.epostaAdresi = epostaAdresi;
            this.dogumTarihi = dogumTarihi;
            
        }

        [Key]
        public int idMusteri { get; set; }
        public string adi { get; set; }
        public string soyadi { get; set; }
        public string sifre { get; set; }
        public string telefonNumarasi { get; set; }
        public string epostaAdresi { get; set; }
        public DateTime dogumTarihi { get; set; }
        public ICollection<Rezervasyon>? Rezervasyonlar { get; set; }
    }
}
