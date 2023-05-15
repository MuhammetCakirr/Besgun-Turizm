using Google.Protobuf.WellKnownTypes;

namespace webVTYSvize.Models
{
    public class HizmetBase
    {
        public HizmetBase(string adi, int fiyat , string resimUrl)
        {
            this.adi = adi;
            this.fiyat = fiyat;
            this.resimUrl = resimUrl;

            Console.WriteLine("hizmetbase çalıştı");                
        }
        public string adi { get; set; }
        public int fiyat { get; set; }
        public string resimUrl { get; set; }


    }
}
