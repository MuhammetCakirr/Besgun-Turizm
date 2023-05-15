using Microsoft.EntityFrameworkCore;

namespace webVTYSvize.Data
{
    public class MyDbContext : DbContext
    {

        public MyDbContext(DbContextOptions<MyDbContext> options) : base(options)
        {

        }

        public DbSet<Hizmet> Hizmet { get; set; }
        public DbSet<HizmetBilgisi> HizmetBilgisi { get; set; }
        public DbSet<Musteri> Musteri { get; set; }
        public DbSet<Otel> Otel { get; set; }
        public DbSet<Rezervasyon> Rezervasyon { get; set; }
        public DbSet<TekneTur> TekneTur { get; set; }
        public DbSet<TurProgrami> TurProgrami { get; set; }
        public DbSet<YurtdisiTur> YurtdisiTur { get; set; }
        public DbSet<OtelView> OtelView { get; set; }
        public DbSet<YurtdisiTurView> YurtdisiTurView { get; set; }
        public DbSet<TekneTurView> TekneTurView { get; set; }
        public DbSet<RezervasyonView> RezervasyonView { get; set; }



        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            modelBuilder.Entity<Hizmet>(e => e.HasKey(x => x.idHizmet));
            modelBuilder.Entity<HizmetBilgisi>(e => e.HasKey(x => x.idHizmetBilgisi));
            modelBuilder.Entity<Musteri>(e => e.HasKey(x => x.idMusteri));
            modelBuilder.Entity<Otel>(e => e.HasKey(x => x.idOtel));
            modelBuilder.Entity<Rezervasyon>(e => e.HasKey(x => x.idRezervasyon));
            modelBuilder.Entity<TekneTur>(e => e.HasKey(x => x.idTekneTur));
            modelBuilder.Entity<TurProgrami>(e => e.HasKey(x => x.idTurProgrami));
            modelBuilder.Entity<YurtdisiTur>(e => e.HasKey(x => x.idYurtdisiTur));


            modelBuilder.Entity<Rezervasyon>()
               .HasOne(x => x.hizmet)
               .WithOne(x => x.rezervasyon)
               .HasForeignKey<Hizmet>(x => x.idHizmet);

            modelBuilder.Entity<Musteri>()
               .HasMany(x => x.Rezervasyonlar)
               .WithOne(x => x.Musteri)
               .HasForeignKey(x => x.Musteri_idMusteri);


            modelBuilder.Entity<Otel>()
                .HasOne(x => x.hizmetBilgisi)
                .WithMany()
                .HasForeignKey(x => x.HizmetBilgisi_idHizmetBilgisi);

            modelBuilder.Entity<TekneTur>()
                .HasOne(x => x.hizmetBilgisi)
                .WithMany()
                .HasForeignKey(x => x.HizmetBilgisi_idHizmetBilgisi);

            modelBuilder.Entity<YurtdisiTur>()
                .HasOne(x => x.hizmetBilgisi)
                .WithMany()
                .HasForeignKey(x => x.HizmetBilgisi_idHizmetBilgisi);
            modelBuilder.Entity<TekneTur>()
                .HasOne(x => x.turProgrami)
                .WithMany()
                .HasForeignKey(x => x.TurProgrami_idTurProgrami);

            modelBuilder.Entity<YurtdisiTur>()
                .HasOne(x => x.turProgrami)
                .WithMany()
                .HasForeignKey(x => x.TurProgrami_idTurProgrami);

        }
    }
}
