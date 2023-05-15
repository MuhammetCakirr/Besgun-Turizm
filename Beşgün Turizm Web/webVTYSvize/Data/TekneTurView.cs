using System.ComponentModel.DataAnnotations;
using webVTYSvize.Models;

namespace webVTYSvize.Data
{
    public class TekneTurView : HizmetBase
    {
        public TekneTurView(int idTekneTur ,string adi ,int fiyat, string resimUrl ,DateTime baslangicTarihi ,string baslangicKonumu,string guzergah,string vizeDurumu,string gunBir,string gunIki,string gunUc,string gunDort,string gunBes) : base(adi, fiyat, resimUrl)
        {
            this.idTekneTur = idTekneTur;
            this.adi = adi;
            this.fiyat = fiyat;
            this.resimUrl = resimUrl;
            this.baslangicTarihi = baslangicTarihi;
            this.baslangicKonumu = baslangicKonumu;
            this.guzergah = guzergah;
            this.vizeDurumu = vizeDurumu;
            this.gunBir = gunBir;
            this.gunIki = gunIki;
            this.gunUc = gunUc;
            this.gunDort = gunDort;
            this.gunBes = gunBes;   
        }

        [Key]
        public int idTekneTur { get; set; }
        public string adi { get; set; }
        public int fiyat { get; set; }
        public string resimUrl { get; set; }
        public DateTime baslangicTarihi { get; set; }
        public string baslangicKonumu { get; set; }
        public string guzergah { get; set; }
        public string vizeDurumu { get; set; }
        public string gunBir { get; set; }
        public string gunIki { get; set; }
        public string gunUc { get; set; }
        public string gunDort { get; set; }
        public string gunBes { get; set; }
    }
}
