using Microsoft.AspNetCore.Mvc;
using static System.Net.Mime.MediaTypeNames;
using System.Drawing;
using System.ComponentModel.DataAnnotations;

namespace webVTYSvize.Data
{
    public class Otel
    {

        public Otel(int idOtel, string adres,int HizmetBilgisi_idHizmetBilgisi)
        {
            this.idOtel = idOtel;
            this.adres = adres;
            this.HizmetBilgisi_idHizmetBilgisi = HizmetBilgisi_idHizmetBilgisi;

        }
        [Key]
        public int idOtel  { get; set; }
        public string adres { get; set; }
        public int HizmetBilgisi_idHizmetBilgisi { get; set; }
        public HizmetBilgisi? hizmetBilgisi { get; set; }
        public double puan { get; set; }
        public string tema { get; set; }


    }
}
