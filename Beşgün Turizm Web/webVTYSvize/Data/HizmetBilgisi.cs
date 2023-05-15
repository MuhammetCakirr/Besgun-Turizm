using Microsoft.EntityFrameworkCore;
using System.ComponentModel.DataAnnotations;
using webVTYSvize.Data;

namespace webVTYSvize.Data
{
    public class HizmetBilgisi
    {
        public HizmetBilgisi(int idHizmetBilgisi,string adi,int fiyat,string resimUrl)
        {
            this.idHizmetBilgisi = idHizmetBilgisi;
            this.adi = adi;
            this.fiyat = fiyat;
            this.resimUrl = resimUrl;
        }
        [Key]
        public int idHizmetBilgisi { get; set; }
        public string adi { get; set; }
        public int fiyat { get; set; }
        public string resimUrl { get; set; }


    }
}




