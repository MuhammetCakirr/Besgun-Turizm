using Microsoft.EntityFrameworkCore;
using System.ComponentModel.DataAnnotations;
using webVTYSvize.Data;

namespace webVTYSvize.Data
{
    public class Hizmet
    {
        public Hizmet(int idHizmet,int hizmetTabloId, string hizmetTabloAdi)
        {
            this.idHizmet = idHizmet;
            this.hizmetTabloId = hizmetTabloId;
            this.hizmetTabloAdi = hizmetTabloAdi;
        }

        [Key]
        public int idHizmet { get; set; }

        // bu id foreignkey değil sadece sorgu ile gidilecek tablonun id numarasını tutar
        public int hizmetTabloId { get; set; }

        public string hizmetTabloAdi{ get; set; }
        public Rezervasyon? rezervasyon { get; set; }
    }

}
