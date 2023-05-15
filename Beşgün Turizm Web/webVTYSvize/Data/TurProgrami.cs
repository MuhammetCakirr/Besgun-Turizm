using System.ComponentModel.DataAnnotations;

namespace webVTYSvize.Data
{
    public class TurProgrami
    {


        public TurProgrami(string idTurProgrami, DateTime baslangicTarihi, string baslangicKonumu, string guzergah, string vizeDurumu, string gunBir, string gunIki, string gunUc, string gunDort, string gunBes)
        {
            this.idTurProgrami = idTurProgrami;
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
        public string idTurProgrami { get; set; }
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
