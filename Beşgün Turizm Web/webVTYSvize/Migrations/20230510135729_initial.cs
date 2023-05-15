using System;
using Microsoft.EntityFrameworkCore.Migrations;
using MySql.EntityFrameworkCore.Metadata;

#nullable disable

namespace webVTYSvize.Migrations
{
    /// <inheritdoc />
    public partial class initial : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AlterDatabase()
                .Annotation("MySQL:Charset", "utf8mb4");

            migrationBuilder.CreateTable(
                name: "HizmetBilgisi",
                columns: table => new
                {
                    idHizmetBilgisi = table.Column<int>(type: "int", nullable: false)
                        .Annotation("MySQL:ValueGenerationStrategy", MySQLValueGenerationStrategy.IdentityColumn),
                    adi = table.Column<string>(type: "longtext", nullable: false),
                    fiyat = table.Column<int>(type: "int", nullable: false),
                    resimUrl = table.Column<string>(type: "longtext", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_HizmetBilgisi", x => x.idHizmetBilgisi);
                })
                .Annotation("MySQL:Charset", "utf8mb4");

            migrationBuilder.CreateTable(
                name: "Musteri",
                columns: table => new
                {
                    idMusteri = table.Column<int>(type: "int", nullable: false)
                        .Annotation("MySQL:ValueGenerationStrategy", MySQLValueGenerationStrategy.IdentityColumn),
                    adi = table.Column<string>(type: "longtext", nullable: false),
                    soyadi = table.Column<string>(type: "longtext", nullable: false),
                    telefonNumarasi = table.Column<string>(type: "longtext", nullable: false),
                    epostaAdresi = table.Column<string>(type: "longtext", nullable: false),
                    dogumTarihi = table.Column<DateTime>(type: "datetime(6)", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Musteri", x => x.idMusteri);
                })
                .Annotation("MySQL:Charset", "utf8mb4");

            migrationBuilder.CreateTable(
                name: "TurProgrami",
                columns: table => new
                {
                    idTurProgrami = table.Column<string>(type: "varchar(255)", nullable: false),
                    baslangicTarihi = table.Column<DateTime>(type: "datetime(6)", nullable: false),
                    baslangicKonumu = table.Column<string>(type: "longtext", nullable: false),
                    guzergah = table.Column<string>(type: "longtext", nullable: false),
                    vizeDurumu = table.Column<string>(type: "longtext", nullable: false),
                    gunBir = table.Column<string>(type: "longtext", nullable: false),
                    gunIki = table.Column<string>(type: "longtext", nullable: false),
                    gunUc = table.Column<string>(type: "longtext", nullable: false),
                    gunDort = table.Column<string>(type: "longtext", nullable: false),
                    gunBes = table.Column<string>(type: "longtext", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_TurProgrami", x => x.idTurProgrami);
                })
                .Annotation("MySQL:Charset", "utf8mb4");

            migrationBuilder.CreateTable(
                name: "Otel",
                columns: table => new
                {
                    idOtel = table.Column<int>(type: "int", nullable: false)
                        .Annotation("MySQL:ValueGenerationStrategy", MySQLValueGenerationStrategy.IdentityColumn),
                    adres = table.Column<string>(type: "longtext", nullable: false),
                    HizmetBilgisi_idHizmetBilgisi = table.Column<int>(type: "int", nullable: false),
                    puan = table.Column<double>(type: "double", nullable: false),
                    tema = table.Column<string>(type: "longtext", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Otel", x => x.idOtel);
                    table.ForeignKey(
                        name: "FK_Otel_HizmetBilgisi_HizmetBilgisi_idHizmetBilgisi",
                        column: x => x.HizmetBilgisi_idHizmetBilgisi,
                        principalTable: "HizmetBilgisi",
                        principalColumn: "idHizmetBilgisi",
                        onDelete: ReferentialAction.Cascade);
                })
                .Annotation("MySQL:Charset", "utf8mb4");

            migrationBuilder.CreateTable(
                name: "Rezervasyon",
                columns: table => new
                {
                    idRezervasyon = table.Column<int>(type: "int", nullable: false)
                        .Annotation("MySQL:ValueGenerationStrategy", MySQLValueGenerationStrategy.IdentityColumn),
                    tarihi = table.Column<DateTime>(type: "datetime(6)", nullable: false),
                    yetiskinSayısı = table.Column<int>(type: "int", nullable: false),
                    cocukSayisi = table.Column<int>(type: "int", nullable: false),
                    Hizmet_idHizmet = table.Column<int>(type: "int", nullable: false),
                    Musteri_idMusteri = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Rezervasyon", x => x.idRezervasyon);
                    table.ForeignKey(
                        name: "FK_Rezervasyon_Musteri_Musteri_idMusteri",
                        column: x => x.Musteri_idMusteri,
                        principalTable: "Musteri",
                        principalColumn: "idMusteri",
                        onDelete: ReferentialAction.Cascade);
                })
                .Annotation("MySQL:Charset", "utf8mb4");

            migrationBuilder.CreateTable(
                name: "TekneTur",
                columns: table => new
                {
                    idTekneTur = table.Column<int>(type: "int", nullable: false)
                        .Annotation("MySQL:ValueGenerationStrategy", MySQLValueGenerationStrategy.IdentityColumn),
                    TurProgrami_idTurProgrami = table.Column<string>(type: "varchar(255)", nullable: false),
                    HizmetBilgisi_idHizmetBilgisi = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_TekneTur", x => x.idTekneTur);
                    table.ForeignKey(
                        name: "FK_TekneTur_HizmetBilgisi_HizmetBilgisi_idHizmetBilgisi",
                        column: x => x.HizmetBilgisi_idHizmetBilgisi,
                        principalTable: "HizmetBilgisi",
                        principalColumn: "idHizmetBilgisi",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_TekneTur_TurProgrami_TurProgrami_idTurProgrami",
                        column: x => x.TurProgrami_idTurProgrami,
                        principalTable: "TurProgrami",
                        principalColumn: "idTurProgrami",
                        onDelete: ReferentialAction.Cascade);
                })
                .Annotation("MySQL:Charset", "utf8mb4");

            migrationBuilder.CreateTable(
                name: "YurtdisiTur",
                columns: table => new
                {
                    idYurtdisiTur = table.Column<int>(type: "int", nullable: false)
                        .Annotation("MySQL:ValueGenerationStrategy", MySQLValueGenerationStrategy.IdentityColumn),
                    TurProgrami_idTurProgrami = table.Column<string>(type: "varchar(255)", nullable: false),
                    HizmetBilgisi_idHizmetBilgisi = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_YurtdisiTur", x => x.idYurtdisiTur);
                    table.ForeignKey(
                        name: "FK_YurtdisiTur_HizmetBilgisi_HizmetBilgisi_idHizmetBilgisi",
                        column: x => x.HizmetBilgisi_idHizmetBilgisi,
                        principalTable: "HizmetBilgisi",
                        principalColumn: "idHizmetBilgisi",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_YurtdisiTur_TurProgrami_TurProgrami_idTurProgrami",
                        column: x => x.TurProgrami_idTurProgrami,
                        principalTable: "TurProgrami",
                        principalColumn: "idTurProgrami",
                        onDelete: ReferentialAction.Cascade);
                })
                .Annotation("MySQL:Charset", "utf8mb4");

            migrationBuilder.CreateTable(
                name: "Hizmet",
                columns: table => new
                {
                    idHizmet = table.Column<int>(type: "int", nullable: false),
                    hizmetTabloId = table.Column<int>(type: "int", nullable: false),
                    hizmetTabloAdi = table.Column<string>(type: "longtext", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Hizmet", x => x.idHizmet);
                    table.ForeignKey(
                        name: "FK_Hizmet_Rezervasyon_idHizmet",
                        column: x => x.idHizmet,
                        principalTable: "Rezervasyon",
                        principalColumn: "idRezervasyon",
                        onDelete: ReferentialAction.Cascade);
                })
                .Annotation("MySQL:Charset", "utf8mb4");

            migrationBuilder.CreateIndex(
                name: "IX_Otel_HizmetBilgisi_idHizmetBilgisi",
                table: "Otel",
                column: "HizmetBilgisi_idHizmetBilgisi");

            migrationBuilder.CreateIndex(
                name: "IX_Rezervasyon_Musteri_idMusteri",
                table: "Rezervasyon",
                column: "Musteri_idMusteri");

            migrationBuilder.CreateIndex(
                name: "IX_TekneTur_HizmetBilgisi_idHizmetBilgisi",
                table: "TekneTur",
                column: "HizmetBilgisi_idHizmetBilgisi");

            migrationBuilder.CreateIndex(
                name: "IX_TekneTur_TurProgrami_idTurProgrami",
                table: "TekneTur",
                column: "TurProgrami_idTurProgrami");

            migrationBuilder.CreateIndex(
                name: "IX_YurtdisiTur_HizmetBilgisi_idHizmetBilgisi",
                table: "YurtdisiTur",
                column: "HizmetBilgisi_idHizmetBilgisi");

            migrationBuilder.CreateIndex(
                name: "IX_YurtdisiTur_TurProgrami_idTurProgrami",
                table: "YurtdisiTur",
                column: "TurProgrami_idTurProgrami");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Hizmet");

            migrationBuilder.DropTable(
                name: "Otel");

            migrationBuilder.DropTable(
                name: "TekneTur");

            migrationBuilder.DropTable(
                name: "YurtdisiTur");

            migrationBuilder.DropTable(
                name: "Rezervasyon");

            migrationBuilder.DropTable(
                name: "HizmetBilgisi");

            migrationBuilder.DropTable(
                name: "TurProgrami");

            migrationBuilder.DropTable(
                name: "Musteri");
        }
    }
}
