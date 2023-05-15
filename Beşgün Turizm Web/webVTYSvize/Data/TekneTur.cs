using System.ComponentModel.DataAnnotations;

namespace webVTYSvize.Data
{
    public class TekneTur
    {
        public TekneTur(int idTekneTur, string TurProgrami_idTurProgrami, int HizmetBilgisi_idHizmetBilgisi)
        {
            this.idTekneTur = idTekneTur;
            this.TurProgrami_idTurProgrami = TurProgrami_idTurProgrami;
            this.HizmetBilgisi_idHizmetBilgisi = HizmetBilgisi_idHizmetBilgisi;
        }
        [Key]
        public int idTekneTur { get; set; }
        public string TurProgrami_idTurProgrami { get; set; }
        public TurProgrami? turProgrami { get; set; }
        public int HizmetBilgisi_idHizmetBilgisi { get; set; }
        public HizmetBilgisi? hizmetBilgisi { get; set; }
    }
}
