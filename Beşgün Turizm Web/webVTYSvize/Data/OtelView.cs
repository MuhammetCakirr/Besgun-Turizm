using System.ComponentModel.DataAnnotations;
using webVTYSvize.Models;

namespace webVTYSvize.Data
{
    public class OtelView : HizmetBase
    {
        public OtelView(int idOtel , string adres ,double puan ,string tema , string adi, int fiyat ,string resimUrl):base(adi, fiyat, resimUrl)
        {
            this.idOtel = idOtel;
            this.adres = adres;
            this.puan = puan;
            this.tema = tema;
            this.adi = adi;
            this.fiyat = fiyat;
            this.resimUrl = resimUrl;
        }
        [Key]
        public int idOtel { get; set; }
        public string adres { get; set; }
        public double puan { get; set; }
        public string tema { get; set; }
        public string adi { get; set; }
        public int fiyat { get; set; }
        public string resimUrl { get; set; }
    }
}
