using System.ComponentModel.DataAnnotations;

namespace webVTYSvize.Data
{
    public class YurtdisiTur
    {

        public YurtdisiTur(int idYurtdisiTur,string TurProgrami_idTurProgrami, int HizmetBilgisi_idHizmetBilgisi)
        {
            this.idYurtdisiTur = idYurtdisiTur;
            this.TurProgrami_idTurProgrami = TurProgrami_idTurProgrami;
            this.HizmetBilgisi_idHizmetBilgisi = HizmetBilgisi_idHizmetBilgisi;
        }
        [Key]
        public int idYurtdisiTur { get; set; }
        public string TurProgrami_idTurProgrami { get; set; }
        public TurProgrami? turProgrami { get; set; }
        public int HizmetBilgisi_idHizmetBilgisi { get; set; }
        public HizmetBilgisi? hizmetBilgisi { get; set; }
    }
}
