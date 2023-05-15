-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 15 May 2023, 14:01:57
-- Sunucu sürümü: 10.4.28-MariaDB
-- PHP Sürümü: 8.1.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `turizmacentasi`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `hizmet`
--

CREATE TABLE `hizmet` (
  `idHizmet` int(11) NOT NULL,
  `hizmetTabloId` int(11) NOT NULL,
  `hizmetTabloAdi` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `hizmet`
--

INSERT INTO `hizmet` (`idHizmet`, `hizmetTabloId`, `hizmetTabloAdi`) VALUES
(1, 1, 'otel'),
(2, 3, 'otel'),
(3, 3, 'otel'),
(4, 2, 'otel'),
(5, 1, 'otel'),
(6, 2, 'otel'),
(7, 2, 'otel'),
(8, 4, 'otel'),
(9, 3, 'otel'),
(13, 10, 'yurtdisitur'),
(14, 10, 'yurtdisitur'),
(15, 10, 'yurtdisitur'),
(17, 2, 'otel'),
(18, 1, 'otel'),
(19, 1, 'teknetur'),
(21, 1, 'otel'),
(22, 5, 'otel');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `hizmetbilgisi`
--

CREATE TABLE `hizmetbilgisi` (
  `idHizmetBilgisi` int(11) NOT NULL,
  `adi` varchar(100) NOT NULL,
  `fiyat` int(11) NOT NULL,
  `resimUrl` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `hizmetbilgisi`
--

INSERT INTO `hizmetbilgisi` (`idHizmetBilgisi`, `adi`, `fiyat`, `resimUrl`) VALUES
(1, 'Arnavutluk:Keşfedilmeyi Bekleyen Balkan Turu', 14000, 'https://media.istockphoto.com/id/529358575/tr/foto%C4%9Fraf/sarandas-port-at-ionian-sea-albania.jpg?s=612x612&w=0&k=20&c=FlLGKmJjhG1GgGbnSdJlJSav-s2D_N5-gt52zwUKkbA='),
(2, 'Dubai Turu:Lüks,Macera ve Modernitenin Buluştuğu Kültürel Cennet', 18000, 'https://cdnp.flypgs.com/files/Sehirler-long-tail/Dubai/dubai-palmiye-ada.jpg'),
(3, 'Italya:Tarihi, Lezzetli ve Sanat Dolu Bir Akdeniz Macerası', 24000, 'https://media.istockphoto.com/id/539115110/tr/foto%C4%9Fraf/colosseum-in-rome-and-morning-sun-italy.jpg?s=612x612&w=0&k=20&c=wjC8WZlGQBv9tbPLZy5AOp98br-F8f92g_9zOO24eAE='),
(4, 'Bulgaristan: Tarihi ve Kültürel Zenginlikleriyle Dolu Balkan Turu', 8000, 'https://media.istockphoto.com/id/621112110/tr/foto%C4%9Fraf/the-cathedral-of-the-assumption-in-varna-aerial-view.jpg?s=612x612&w=0&k=20&c=RF1HmqypfGJ9gb_Yg-G6c2GgCWX32wtYc9D_GeLYslI='),
(5, 'İran: Doğunun Tarihi, Doğal Güzellikleri ve Kültürel Mirasıyla Dolu Bir Keşif', 25000, 'https://media.istockphoto.com/id/1134992307/tr/foto%C4%9Fraf/isfahan-da-naqsh-e-cihan-meydan%C4%B1-iran-januray-2019-%C3%A7ekilen-hdr.jpg?s=612x612&w=0&k=20&c=JBy9MFzRXK6epwU8L0u4gDCn6BKC33oCLhKOGFVA2iE='),
(6, 'İspanya: Lezzetli Mutfağı, Renkli Festivalleri ve Tarihi Mirasıyla Dolu Bir İber Yarımadası Deneyimi', 20000, 'https://cdn.ytur.net/fit-in/697x465/filters:quality(100)/filters:format(webp)/post/ispanya-ya-ne-zaman-gidilir-220420-112316-8021.jpg'),
(7, 'Sırbistan:Bir Balkan Kaçamağı', 10000, 'https://media.istockphoto.com/id/1326144217/tr/foto%C4%9Fraf/temple-saint-sava.jpg?s=612x612&w=0&k=20&c=cmQQFFc904AkJF1O4UDrXeg6WFBbnNbSLOr_XZiw8EY='),
(8, 'Yunanistan: Antik Harikaları, Güneşli Adaları ve Lezzetli Mutfağıyla Dolu Bir Akdeniz Turu', 30000, 'https://media.istockphoto.com/id/1145450965/tr/foto%C4%9Fraf/santorini-adas%C4%B1-yunanistan.jpg?s=612x612&w=0&k=20&c=L7huttq6zPJ0a_x2pp1VIj3F5QjEOH-p9eiuIKj8UqY='),
(9, 'Maldivler: Beyaz Kumlu Plajları, Mercan Resifleri ve Turkuaz Sularıyla Dolu Cennet Bir Ada Tatili', 50000, 'https://media.istockphoto.com/id/155139968/tr/foto%C4%9Fraf/island-of-maldives.jpg?s=612x612&w=0&k=20&c=257DND0NNnGuoP-4aLWPRAt4nq33ngO_MCVQrWtkQks='),
(10, 'Mısır: Antik Harikaları, Nil Nehri\'ni Keşif ve Kültürel Zenginlikleriyle Dolu Bir Orta Doğu Turu', 29000, 'https://blog.obilet.com/wp-content/uploads/2019/05/shutterstock_1169166295.jpg'),
(11, 'Bavyera\'nın Güzellikleri: Münih, Regensburg, Nürnberg ve Bamberg Tekne Turu', 44000, 'https://cdn2.setur.com.tr/image/tour/large/cruise-7af81746-4920-4043-be4a-8366b906ffb4.jpg'),
(12, 'Sırbistan\'ı Keşfedin: Sava Nehri, Đerdap Milli Parkı ve Kaleleri İle Tekne Turu', 21000, 'https://wp.oggusto.com/wp-content/uploads/2022/03/dunyanin-en-buyuk-10-superyati-1.jpg'),
(13, 'Hollanda\'nın Tarihi Kanallarında Geleneksel Köyleri Keşfedin: Rotterdam Tekne Turu', 27000, 'https://www.karavancruises.com/shipphotos/holland-america-line-rotterdam.jpg'),
(14, 'Panama Macerası: Kanal Keşfi, Adaların Keyfi ve Tarihi Casco Viejo Tekne Turu', 34000, 'https://images.gemiturlari.info/gemi/turlar/visionoftheseasgemituru-1172019121706.jpg'),
(15, 'Batı Amerika\'nın En İkonik Şehirleri: Los Angeles, Las Vegas ve San Francisco\'yu Tekne turu ile keşf', 57000, 'https://www.goldenbaytour.com/UserFiles/Images/odyssey-exterior-03_extendededge_200.jpg'),
(16, 'Ege Denizi\'nin İncileri: Santorini, Mykonos, Rodos ve Selanik Tekne Turu', 43000, 'https://ucdn.tatilbudur.net/tur/Tour-Category/780x446/539434.jpg'),
(17, 'Ege Adaları\'nın Tarihi ve Doğal Güzellikleri: Kuşadası, Samos, Patmos, Lipsi ve Leros Tekne Turu', 49000, 'https://library.coraltatil.com/resources/pageImages/1/original/93/1.jpg'),
(18, 'Baltık Denizi\'nin İncileri: Helsinki, St. Petersburg, Tallinn, Riga ve Stockholm Tekne Turu', 40000, 'https://www.karavancruises.com/uploads/baltik-baskentleri-gemi-turu.jpg'),
(19, 'Tropical Paradise: Bahamalar, Jamaika, Kaiman Adaları ve Meksika\'da Unutulmaz Bir Tekne Turu', 70000, 'https://images.gemiturlari.info/gemi/turlar/cococay-bahamalar-18102022170440.jpg'),
(20, 'Beş Günde Macaristan\'ın En Güzel Şehirleri: Budapeşte, Visegrád, Esztergom ve Komárom Tekne Turu', 56000, 'https://www.mngturizm.com/images/tour/12141_b.jpg'),
(21, 'Voyage Torba', 5000, 'https://images.etstur.com/files/images/hotelImages/TR/52480/l/Voyage-Torba-Genel-313740.jpg'),
(22, 'Asteria Kemer Resort', 3100, 'https://images.etstur.com/files/images/hotelImages/TR/97205/l/Selectum-Exclusive-Resort-Kemer-Genel-360477.jpg'),
(23, 'Sacred House Hotel', 6115, 'https://cdng.jollytur.com/files/cms/media/hotel/816d2e94-789d-4714-a0f2-d1e32537e506-600.jpg'),
(24, 'Nuhun Gemisi Deluxe Hotel & Spa', 2850, 'https://images.etstur.com/files/images/hotelImages/TR/52480/l/Voyage-Torba-Genel-313740.jpg'),
(25, 'Les Terrasses De Selimiye', 2162, 'https://cdng.jollytur.com/files/cms/media/hotel/0fd9e75c-5763-409c-ac50-51173cfcb2be-1024.jpg'),
(26, 'Melas Hotel Istanbul', 4232, 'https://cdng.jollytur.com/files/cms/media/hotel/82bf7c68-9ab3-46f7-855f-271114db114b-600.jpg'),
(27, 'Abant Lotus Otel', 2562, 'https://cdng.jollytur.com/files/cms/media/hotel/57856e54-04a8-40e8-b266-3d3120eb593f-600.jpg'),
(28, 'Grand Bursa Hotel', 1200, 'https://cdng.jollytur.com/files/cms/media/hotel/7bb7f6b8-c79d-4f27-a277-d33ffdcfcc41-600.JPG'),
(29, 'Villa Levante Hotel', 3203, 'https://cdng.jollytur.com/files/cms/media/hotel/a21bd9e7-9229-4a93-9080-19e7a14cf3f4-600.jpg'),
(30, 'Elite World Grand Sapanca Hotel', 3795, 'https://cdng.jollytur.com/files/cms/media/hotel/899c4015-6306-464d-bff0-85d487374b64-600.jpg');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `musteri`
--

CREATE TABLE `musteri` (
  `idMusteri` int(11) NOT NULL,
  `adi` varchar(45) NOT NULL,
  `soyadi` varchar(45) NOT NULL,
  `sifre` varchar(45) NOT NULL,
  `telefonNumarasi` varchar(45) NOT NULL,
  `epostaAdresi` varchar(45) NOT NULL,
  `dogumTarihi` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `musteri`
--

INSERT INTO `musteri` (`idMusteri`, `adi`, `soyadi`, `sifre`, `telefonNumarasi`, `epostaAdresi`, `dogumTarihi`) VALUES
(1, 'Mustafa', 'Toprak', '3232', '05331327841', 'musttoprakk@gmail.com', '2003-07-29 00:00:00');

--
-- Tetikleyiciler `musteri`
--
DELIMITER $$
CREATE TRIGGER `after_rezervasyon_update` AFTER UPDATE ON `musteri` FOR EACH ROW BEGIN
  INSERT INTO newmusteri (adi,soyadi,sifre,telefonNumarasi,epostaAdresi,dogumTarihi,idMusteri)
  VALUES (old.adi,old.soyadi,old.sifre,old.telefonNumarasi,old.epostaAdresi,old.dogumTarihi,old.idMusteri);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `newmusteri`
--

CREATE TABLE `newmusteri` (
  `idNewMusteri` int(11) NOT NULL,
  `adi` varchar(45) NOT NULL,
  `soyadi` varchar(45) NOT NULL,
  `sifre` varchar(45) NOT NULL,
  `telefonNumarasi` varchar(45) NOT NULL,
  `epostaAdresi` varchar(45) NOT NULL,
  `dogumTarihi` datetime NOT NULL,
  `idMusteri` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `newmusteri`
--

INSERT INTO `newmusteri` (`idNewMusteri`, `adi`, `soyadi`, `sifre`, `telefonNumarasi`, `epostaAdresi`, `dogumTarihi`, `idMusteri`) VALUES
(0, 'Musteeeee', 'Topraaak', '3131', '05331327841', 'musttoprakk@gmail.com', '2003-07-29 00:00:00', 1),
(0, 'Mustafa', 'Topraaak', '3131', '05331327841', 'musttoprakk@gmail.com', '2003-07-29 00:00:00', 1),
(0, 'Mustafa', 'Toprak', '3131', '05331327841', 'musttoprakk@gmail.com', '2003-07-29 00:00:00', 1),
(0, 'Mustafagfbgfgg', 'Toprak', '3131', '05331327841', 'musttoprakk@gmail.com', '2003-07-29 00:00:00', 1),
(0, 'Mustafagfbgfgg', 'Toprak', '3232', '05331327841', 'musttoprakk@gmail.com', '2003-07-29 00:00:00', 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `otel`
--

CREATE TABLE `otel` (
  `idOtel` int(11) NOT NULL,
  `adres` varchar(45) NOT NULL,
  `HizmetBilgisi_idHizmetBilgisi` int(11) NOT NULL,
  `puan` double NOT NULL,
  `tema` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `otel`
--

INSERT INTO `otel` (`idOtel`, `adres`, `HizmetBilgisi_idHizmetBilgisi`, `puan`, `tema`) VALUES
(1, 'Torba, Bodrum, Muğla', 21, 7.5, 'Balayı Otel'),
(2, 'Çamyuva, Kemer, Antalya', 22, 8.6, 'Termal Otel'),
(3, 'Ürgüp, Kapadokya, Muğla', 23, 9.2, 'Butik Otel'),
(4, 'Bafra, Samsun', 24, 7.2, 'Balayı Otel'),
(5, 'Selimiye, Marmaris, Muğla', 25, 7.9, 'Termal Otel'),
(6, 'Gayrettepe, İstanbul', 26, 8.5, 'Şehir Otel'),
(7, 'Abant, Bolu', 27, 9.7, 'Tatil Köyü'),
(8, 'Osmangazi, Bursa', 28, 8.7, 'Tatil Köyü'),
(9, 'Bornava, İzmir', 29, 8.2, 'Şehir Otel'),
(10, 'Sapanca, Sakarya', 30, 6.5, 'Termal Otel');

-- --------------------------------------------------------

--
-- Görünüm yapısı durumu `otelview`
-- (Asıl görünüm için aşağıya bakın)
--
CREATE TABLE `otelview` (
`idOtel` int(11)
,`adres` varchar(45)
,`puan` double
,`tema` varchar(45)
,`adi` varchar(100)
,`fiyat` int(11)
,`resimUrl` varchar(300)
);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `rezervasyon`
--

CREATE TABLE `rezervasyon` (
  `idRezervasyon` int(11) NOT NULL,
  `tarihi` datetime NOT NULL,
  `yetiskinSayisi` int(11) NOT NULL,
  `cocukSayisi` int(11) NOT NULL,
  `Musteri_idMusteri` int(11) NOT NULL,
  `Hizmet_idHizmet` int(11) NOT NULL,
  `girisTarihi` datetime DEFAULT NULL,
  `cikisTarihi` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `rezervasyon`
--

INSERT INTO `rezervasyon` (`idRezervasyon`, `tarihi`, `yetiskinSayisi`, `cocukSayisi`, `Musteri_idMusteri`, `Hizmet_idHizmet`, `girisTarihi`, `cikisTarihi`) VALUES
(2, '2023-05-14 19:27:37', 16, 9, 1, 22, '2023-05-14 00:00:00', '2023-05-15 00:00:00');

-- --------------------------------------------------------

--
-- Görünüm yapısı durumu `rezervasyonview`
-- (Asıl görünüm için aşağıya bakın)
--
CREATE TABLE `rezervasyonview` (
`idRezervasyon` int(11)
,`tarihi` datetime
,`yetiskinSayisi` int(11)
,`cocukSayisi` int(11)
,`girisTarihi` datetime
,`cikisTarihi` datetime
,`idMusteri` int(11)
,`adi` varchar(45)
,`soyadi` varchar(45)
,`telefonNumarasi` varchar(45)
,`epostaAdresi` varchar(45)
,`hizmetTabloAdi` varchar(45)
,`hizmettabloId` int(11)
);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `teknetur`
--

CREATE TABLE `teknetur` (
  `idTekneTur` int(11) NOT NULL,
  `TurProgrami_idTurProgrami` varchar(20) NOT NULL,
  `HizmetBilgisi_idHizmetBilgisi` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `teknetur`
--

INSERT INTO `teknetur` (`idTekneTur`, `TurProgrami_idTurProgrami`, `HizmetBilgisi_idHizmetBilgisi`) VALUES
(1, 'ttistalmanya', 11),
(2, 'ttistsirbistan', 12),
(3, 'ttizmirhollanda', 13),
(4, 'ttizmirpanama', 14),
(5, 'ttistbatiabd', 15),
(6, 'ttcanakkaleyunan', 16),
(7, 'ttkusadasiege', 17),
(8, 'ttistbaltikbas', 18),
(9, 'ttizmirkarayip', 19),
(10, 'ttistmacaristan', 20);

-- --------------------------------------------------------

--
-- Görünüm yapısı durumu `tekneturview`
-- (Asıl görünüm için aşağıya bakın)
--
CREATE TABLE `tekneturview` (
`idTekneTur` int(11)
,`adi` varchar(100)
,`fiyat` int(11)
,`resimUrl` varchar(300)
,`baslangicTarihi` datetime
,`baslangicKonumu` varchar(45)
,`guzergah` varchar(150)
,`vizeDurumu` varchar(10)
,`gunBir` varchar(200)
,`gunIki` varchar(200)
,`gunUc` varchar(200)
,`gunDort` varchar(200)
,`gunBes` varchar(200)
);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `turprogrami`
--

CREATE TABLE `turprogrami` (
  `idTurProgrami` varchar(20) NOT NULL,
  `baslangicTarihi` datetime NOT NULL,
  `baslangicKonumu` varchar(45) NOT NULL,
  `guzergah` varchar(150) NOT NULL,
  `vizeDurumu` varchar(10) NOT NULL,
  `gunBir` varchar(200) NOT NULL,
  `gunIki` varchar(200) NOT NULL,
  `gunUc` varchar(200) NOT NULL,
  `gunDort` varchar(200) NOT NULL,
  `gunBes` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `turprogrami`
--

INSERT INTO `turprogrami` (`idTurProgrami`, `baslangicTarihi`, `baslangicKonumu`, `guzergah`, `vizeDurumu`, `gunBir`, `gunIki`, `gunUc`, `gunDort`, `gunBes`) VALUES
('ttcanakkaleyunan', '2023-08-10 09:00:00', 'Çanakkale', 'Santorini Adası, Mykonos Adası, Rodos Adası, Selanik', 'Evet', '(1.Gün Yunanistan / 10 Ağustos) Atina\'da buluşma ve tekne turuna başlama. Sabah erken saatlerde, Ege Denizi\'nin kalbinde yer alan Santorini Adası\'na doğru yola çıkacağız. Burası, kendine özgü beyaz ev', '(2.Gün Yunanistan / 11 Ağustos) Mykonos Adası\'na doğru yola çıkacağız. Adanın kumlu plajları, turistik mağazaları, restoranları ve gece hayatı ile ünlüdür. Burada, plajda güneşlenebilir, yüzme molası ', '(3.Gün Yunanistan / 12 Ağustos) Rodos Adası\'na doğru yola çıkacağız. Burası, Antik Yunan döneminden kalma tarihi kalıntıları, muhteşem manzaraları ve doğal güzellikleri ile ünlüdür. Adada, tarihi kalı', '(4.Gün Yunanistan / 13 Ağustos) Selanik\'e doğru yola çıkacağız. Burası, Yunanistan\'ın ikinci büyük şehridir ve tarihi yapıları, müzeleri, alışveriş merkezleri ve restoranları ile ünlüdür. Şehirde serb', '(5.Gün Yunanistan / 14 Ağustos) Tekne turumuzun son gününe başlayacağız ve Atina\'ya geri döneceğiz. Yolda, Ege Denizi\'nin güzel manzaralarının keyfini çıkarabilirsiniz. Tur sonunda, Atina\'ya döneceğiz'),
('ttistalmanya', '2023-08-20 10:00:00', 'İstanbul', 'Münih, Regensburg, Nürnberg, Bamberg', 'Evet', '(1.Gün Almanya / 20 Ağustos) Turumuz kişiye özel aracın sizi adresinizden alması ile başlıyor. İstanbul Yeni Havalimanı, Dış Hatlar Terminali Türk Havayolları kontuarı önünde belirlenen saatte bulu', '(2.Gün Almanya / 21 Ağustos) Gemimiz Regensburg limanına saat 09:00’da yanaşacaktır. . Arzu eden misafirlerimiz Weltenburg manastırı & Kelheım yürüyüş turu & Regensburg yürüyüş turuna katılabilirler. ', '(3.Gün Almanya / 22 Ağustos) Gemimiz Nuremberg limanına saat 14:00’da yanaşacaktır. . Arzu eden misafirlerimiz Nuremberg şehir ve yürüyüş turuna katılabilirler. Buradan 19:00’da Bamberg limanına harek', '(4.Gün Almanya / 23 Ağustos) Gemimiz Bamberg limanına sabah saat 08:00’da yanaşacaktır. Arzu eden misafirlerimiz Bamberg şehir ve yürüyüş turuna katılabilirler. Buradan 11:00’da hareket edecektir. Gec', '(5.Gün Almanya / 24 Ağustos) Sabah erken saatlerde Köln limanına varış. Kahvaltı sonrası gemiden çıkış işlemlerinin ardından havalimanına transfer. Check-in ve Pasaport kontrolü sonrası Türk Havayolla'),
('ttistbaltikbas', '2023-08-01 09:00:00', 'İstanbul', 'Helsinki, St. Petersburg, Tallinn, Riga, Stockholm', 'Evet', '(1.Gün Baltık Başkentleri / 1 Ağustos) Tekne turumuza Stockholm\'den başlayacağız. Sabah saatlerinde tekneye binip, Baltık Denizi\'ne açılacağız. İlk durağımız, Finlandiya\'nın başkenti Helsinki olacak. ', '(2. Gün  Baltık Başkentleri / 2 Ağustos) Rusya\'nın güzel şehri St. Petersburg\'a doğru yola çıkacağız. St. Petersburg, tarihi sarayları, müzeleri ve kanallarıyla ünlüdür. Burada, Hermitage Müzesi\'ni zi', '(3. Gün  Baltık Başkentleri / 3 Ağustos) Estonya\'nın başkenti Tallinn\'e doğru yola çıkacağız. Tallinn, Orta Çağ\'dan kalma tarihi şehir merkezi ve modern alışveriş merkezleriyle ünlüdür. Burada, tarihi', '(4. Gün  Baltık Başkentleri / 4 Ağustos) Letonya\'nın başkenti Riga\'ya doğru yola çıkacağız. Riga, tarihi yapıları, sanat galerileri ve müzeleriyle ünlüdür. Burada, tarihi şehir merkezini ziyaret edebi', '(5. Gün  Baltık Başkentleri / 5 Ağustos) Daha sonra, Stockholm\'e geri dönüş yapacağız ve tekne turumuzu tamamlayacağız.'),
('ttistbatiabd', '2023-07-01 09:00:00', 'İstanbul', 'Los Angeles, Grand Canyon, Las Vegas, San Francisco', 'Hayır', '(1.Gün Batı Amerika / 1 Temmuz) Los Angeles\'dan başlayan turumuzda, Hollywood, Beverly Hills ve Santa Monica gibi turistik yerleri ziyaret edeceğiz. Daha sonra Grand Canyon\'a doğru yola çıkacağız ve m', '(2. Gün Batı Amerika / 2 Temmuz) Grand Canyon\'dan ayrılarak Las Vegas\'a doğru yola çıkacağız. Burada bir gece konaklayacağız ve şehrin ışıl ışıl gece hayatını deneyimleyeceğiz.', '(3. Gün Batı Amerika / 3 Temmuz) Las Vegas\'tan ayrılarak San Francisco\'ya doğru yola çıkacağız. Yolda, Death Valley ve Yosemite Milli Parkı gibi yerleri ziyaret edeceğiz. Yosemite, ABD\'nin en popüler ', '(4. Gün Batı Amerika / 4 Temmuz) San Francisco\'da bir tur yapacağız ve Golden Gate Köprüsü, Alcatraz Adası ve Chinatown gibi turistik yerleri ziyaret edeceğiz.', '(5. Gün Batı Amerika / 5 Temmuz) Turumuzun son gününde, serbest zamanınız olacak ve San Francisco\'nun turistik yerlerini keşfedebilirsiniz. Daha sonra, Los Angeles\'a dönüş yapacağız ve turumuzu tamaml'),
('ttistmacaristan', '2023-08-01 09:00:00', 'İstanbul', 'Budapeşte, Visegrád, Esztergom, Komárom', 'Hayır', '(1.Gün Macaristan / 1 Ağustos) Tuna Nehri\'nde tekne turumuza Budapeşte\'den başlayacağız. Tekne turumuzda Budapeşte\'nin ünlü Parlamento Binası, Buda Kalesi ve Fisherman\'s Bastion gibi önemli turistik y', '(2. Gün Macaristan / 2 Ağustos) Sabahleyin, Visegrád\'a doğru yola çıkacağız. Bu tarihi kasaba, 14. yüzyılda Macaristan Krallığı\'nın başkenti olarak hizmet vermiştir. Burada, Visegrád Kalesi\'ni ziyaret', '(3. Gün Macaristan / 3 Ağustos) Esztergom\'a doğru yola çıkacağız. Esztergom, Macaristan\'ın en eski ve en önemli şehirlerinden biridir ve ülkenin en büyük katedraline ev sahipliği yapar. Burada, katedr', '(4. Gün Macaristan / 4 Ağustos) Tekne turumuzda Komárom\'a doğru yola çıkacağız. Komárom, Macaristan\'ın kuzeybatısındaki Tuna Nehri\'nin kıyısında yer alan bir kasabadır. Burada, tarihi Komárom Kalesi\'n', '(5. Gün Macaristan / 5 Ağustos) Daha sonra, Budapeşte\'ye geri dönüş yapacağız ve tekne turumuzu tamamlayacağız. Budapeşte\'de serbest zamanınız olacak ve dilediğiniz gibi şehri keşfedebileceksiniz.'),
('ttistsirbistan', '2023-07-15 11:20:00', 'İstanbul', 'Sava Nehri, Đerdap Milli Parkı, Golubac Kalesi, Smederevo Kalesi, Ada Ciganlija', 'Hayır', '(1.Gün Sırbistan / 15 Temmuz) Belgrad\'da buluşma ve tekne turuna başlama. Sava Nehri boyunca ilerleyerek, tarihi Golubac Kalesi\'ne doğru yola çıkacağız. Kale, Tuna Nehri\'nin kanyon bölgesinde yer alma', '(2. Gün Sırbistan / 16 Temmuz) Đerdap Milli Parkı\'na doğru yola çıkacağız. Tuna Nehri\'nin en dar geçidi olan bu bölgede, yüksek kayalıklar arasında ilerleyeceğiz. Milli park, doğal güzelliği, flora ve', '(3. Gün Sırbistan / 17 Temmuz) Smederevo Kalesi\'ne doğru yola çıkacağız. Bu kale, Ortaçağ\'da inşa edilmiştir ve bugün hala ayakta olan en büyük Sırp kalesidir. Burada, tarihi bir tur yapabilir, Smeder', '(4. Gün Sırbistan / 18 Temmuz) Tekne turumuzun son gününe başlayacağız. Sabah kahvaltısı sonrası, Sava Nehri üzerinde bir tur yapacağız. Bu sırada, Belgrad\'ın muhteşem manzaralarını, tarihi yapılarını', '(5. Gün Sırbistan / 19 Temmuz) Belgrad\'da serbest zaman. Kahvaltı sonrası gemiden çıkış işlemlerinin ardından havalimanına transfer. Check-in ve Pasaport kontrolü sonrası Türk Havayolları’nın tarifeli'),
('ttizmirhollanda', '2023-08-10 09:00:00', 'İzmir', 'Rotterdam Kanalları, Kinderdijk Rüzgar Değirmenleri, Amsterdam Kanalları, Zaanse Schans, Volendam', 'Hayır', '(1.Gün Hollanda / 10 Ağustos) Rotterdam\'da buluşma ve tekne turuna başlama. Rotterdam Kanalları boyunca ilerleyerek, Kinderdijk Rüzgar Değirmenleri\'ne doğru yola çıkacağız. Burası, UNESCO Dünya Mirası', '(2. Gün  Hollanda / 11 Ağustos) Amsterdam Kanalları\'nda gezinti yapacağız. Bu kanallar, Amsterdam\'ın simgesi haline gelmiştir ve şehrin tarihi merkezinde yer almaktadır. Tur sırasında, şehrin ünlü yap', '(3. Gün  Hollanda / 12 Ağustos) Zaanse Schans\'a doğru yola çıkacağız. Burası, Hollanda\'nın ünlü peynirleri, ahşap ayakkabıları ve diğer geleneksel ürünleri ile ünlü bir açık hava müzesidir. Burada, Ho', '(4. Gün  Hollanda / 13 Ağustos) Volendam\'a doğru yola çıkacağız. Burası, Hollanda\'nın ünlü balıkçı köylerinden biridir ve geleneksel evleri, restoranları ve diğer turistik yerleri ile ünlüdür. Tur boy', '(5. Gün  Hollanda / 14 Ağustos) Tekne turumuzun son gününe başlayacağız. Sabah kahvaltısı sonrası, Rotterdam Kanalları üzerinde bir tur yapacağız. Bu sırada, Rotterdam\'ın muhteşem manzaralarını, tarih'),
('ttizmirkarayip', '2023-11-01 10:00:00', 'İzmir', 'Bahamalar, Jamaika, Kaiman Adaları, Meksika', 'Hayır', '(1.Gün Bahamalar / 1 Kasım) Tekne turumuz Miami\'den başlayacak. Bahamalar\'a doğru yola çıkacağız. Bahamalar, turkuaz suları, beyaz kumlu plajları ve doğal güzellikleriyle ünlüdür. Burada serbest zaman', '(2. Gün  Bahamalar / 2 Kasım) Jamaika\'ya doğru yola çıkacağız. Jamaika, Bob Marley ve reggae müziğiyle tanınır. Burada, ünlü müzisyenin evini ve stüdyosunu ziyaret edebilir ve yerel kültürü keşfedebil', '(3. Gün  Bahamalar / 3 Kasım) Kaiman Adaları\'na doğru yola çıkacağız. Kaiman Adaları, berrak suları ve mercan resifleriyle ünlüdür. Burada, şnorkelle dalış yapabilir ve deniz yaşamını keşfedebilirsini', '(4. Gün  Bahamalar / 4 Kasım) Son olarak, Meksika\'ya doğru yola çıkacağız. Meksika, tarihi yapıları, doğal güzellikleri ve lezzetli yemekleriyle ünlüdür. Burada, yerel pazarları ziyaret edebilir ve Me', '(5. Gün  Bahamalar / 5 Kasım) Tekne turumuzun son günü ve Meksika\'dan Miami\'ye geri dönüş yapacağız.'),
('ttizmirpanama', '2023-07-10 09:00:00', 'İzmir', 'Panama Kanalı, Casco Viejo, San Blas Adaları, Bocas del Toro', 'Evet', '(1.Gün Panama / 10 Temmuz) Turumuz kişiye özel aracın sizi adresinizden alması ile başlıyor. Check-in ve pasaport işlemleri sonrası Panama City\'de buluşma ve turumuza başlama. İlk durağımız, dünyanın ', '(2. Gün Panama / 11 Temmuz) San Blas Adaları\'na doğru yola çıkacağız. Bu adalar, kristal berraklığındaki sularda yüzmeniz, güneşlenmeniz ve rahatlamanız için mükemmel bir yerdir. Burada, yerel Kuna Ye', '(3. Gün Panama / 12 Temmuz) Bocas del Toro\'ya doğru yola çıkacağız. Bu bölge, zengin doğal yaşamı, renkli karayipler kültürü ve sıcak atmosferi ile ünlüdür. Burada, tekne turları, yüzme, sörf, dalış v', '(4. Gün Panama / 13 Temmuz) Sabah kahvaltısı sonrası, serbest zaman. Daha sonra, Panama City\'ye doğru yola çıkacağız. Şehir turu yaparken, modern ve tarihi yapıları, müzeleri, parkları ve diğer turist', '(5. Gün Panama / 14 Temmuz) Sabah erken saatlerde, havalimanına transfer için hazırlanacağız. Sonrasında, Panama\'dan ayrılacak ve turumuzu tamamlayacağız.'),
('ttkusadasiege', '2023-07-10 09:00:00', 'Kuşadası', 'Samos, Patmos, Lipsi, Leros', 'Hayır', '(1.Gün Ege / 10 Temmuz) Tekne turumuza Kuşadası\'nda başlayacağız. Sabah saatlerinde tekneye binip, Ege Denizi\'ne açılacağız. İlk durağımız, tarihi Samos adası olacak. Samos, antik dönemde filozof Pyth', '(2. Gün Ege / 11 Temmuz) Patmos adasına doğru yola çıkacağız. Patmos, Aziz Yuhanna\'nın Hristiyanlıkla ilgili vizyonlarını aldığı yer olarak bilinir ve bugün bu nedenle bir Hristiyan hac yeri olarak ka', '(3. Gün Ege / 12 Temmuz) Lipsi adasına doğru yola çıkacağız. Lipsi, sakin plajları ve doğal güzellikleriyle ünlüdür. Burada, güneşin tadını çıkarmak ve kumsallarda yüzmenin keyfini çıkarmak için serbe', '(4. Gün Ege / 13 Temmuz) Leros adasına doğru yola çıkacağız. Leros, tarihi kaleleri ve doğal limanlarıyla ünlüdür. Burada, tarihi yerleri ziyaret edebilir ve ada turu yapabilirsiniz.', '(5. Gün Ege / 14 Temmuz) Daha sonra, Kuşadası\'na geri dönüş yapacağız ve tekne turumuzu tamamlayacağız.'),
('ytankarnavutluk', '2023-08-15 09:00:00', 'Ankara', 'Tiran, Berat, Saranda, Gjirokastra', 'Hayır', '(1.Gün Arnavutluk / 15 Ağustos) Sabah saatlerinde Tiran\'a varış yapabilirsiniz. Tiran\'ın merkezinde bulunan Ethem Bey Camii\'ni ziyaret edebilirsiniz. 18. yüzyılda inşa edilmiş olan bu camii, Arnavutlu', '(2. Gün Arnavutluk / 16 Ağustos) Berat, \"bin pencereli şehir\" olarak bilinir. Burada Orta Çağ\'dan kalma bir kale ve geleneksel Osmanlı evleri bulunur. Sabah saatlerinde Tiran\'dan Berat\'a doğru yola çı', '(3. Gün Arnavutluk / 17 Ağustos) Saranda, Arnavutluk\'un güney kıyısındaki bir tatil beldesidir. Sabah saatlerinde Berat\'tan Saranda\'ya doğru yola çıkabilirsiniz. Saranda\'da plaj keyfi yapabilir ve Akd', '(4. Gün Arnavutluk / 18 Ağustos) Gjirokastra, Arnavutluk\'un güneyindeki bir şehirdir. UNESCO Dünya Mirası Listesi\'nde yer alan Gjirokastra Kalesi ve Osmanlı döneminden kalma evleriyle ünlüdür. Sabah s', '(5. Gün Arnavutluk / 19 Ağustos) Son gün serbest zaman. Saranda\'da denize girip güneşlenebilir veya Ksamil Plajı\'na gitmek için bir tekne turu yapabilirsiniz. Akşam saatlerinde İstanbul\'a dönüş.'),
('ytankdubai', '2023-06-15 08:30:00', 'Ankara', 'Abu Dhabi, Sharjah, Fujairah', 'Evet', '(1.Gün Dubai / 15 Haziran) Dubai\'ye varış ve otelinize transfer. Öğleden sonra Dubai şehir turuna başlayın. Burj Khalifa, Dubai Fountain, Dubai Mall ve Dubai Museum\'u ziyaret edebilirsiniz.Akşam yemeğ', '(2. Gün  Dubai / 16 Haziran) Sabah kahvaltısından sonra, çöl safari turu için hazırlanın.Safari turu sırasında, kum tepeleri üzerinde araba sürmek, deve gezintisi yapmak, kum kaydırağına binmek ve Bed', '(3. Gün  Dubai / 17 Haziran) Sabah Aquaventure Su Parkı\'na gidin. Burada, heyecan verici kaydıraklar, dalga havuzları ve hayvanlarla dolu bir akvaryum bulunmaktadır.Öğleden sonra, Atlantis The Palm\'de', '(4. Gün  Dubai / 18 Haziran) Sabah kahvaltısından sonra Dubai Miracle Garden\'ı ziyaret edin. Burada, dünyanın en büyük çiçek bahçelerinden birinde renkli çiçeklerin ve heykellerin keyfini çıkarabilirs', '(5. Gün  Dubai / 19 Haziran) Dubai\'de serbest zaman. Burada Dubai Mall, Burj Khalifa ve Dubai Fountain gibi popüler yerleri ziyaret edebilirsiniz.'),
('ytankitalya', '2023-06-07 09:45:00', 'Ankara', 'Venedik,Florence,Roma,Napoli', 'Hayır', '(1.Gün İtalya / 7 Haziran) Sabah erken saatlerde İstanbul\'dan Venedik\'e uçuş. Şehirde kanallar üzerindeki gezintileri ile ünlü San Marco Meydanı ve Kanal ile birbirine bağlanan birçok küçük köprüsü il', '(2.Gün  İtalya / 8 Haziran) Sabah erkenden Floransa\'ya gitmek için trenle hareket edin. Floransa\'nın en ünlü turistik yerleri arasında Galleria dell\'Accademia\'da Michelangelo\'nun Davut heykeli ve Uffi', '(3.Gün  İtalya / 9 Haziran) Sabah erkenden Roma\'ya gitmek için trenle hareket edin. Roma, tarihi ve mimari açıdan zengin bir şehir olduğu için Colosseum, Pantheon, Vatikan Müzeleri ve Roma Forumu gibi', '(4.Gün  İtalya / 10 Haziran) Sabah erkenden Napoli\'ye gitmek için trenle hareket edin. Napoli, tarihi ve kültürel açıdan önemli bir yer olduğu gibi, ünlü Napoli pizzası da burada doğmuştur. Şehirdeki ', '(5.Gün  İtalya / 11 Haziran) Gezinin son günü Napoli\'den İstanbul\'a uçuş. Otelde kahvaltı sonrası serbest zaman.'),
('ytistbulgaria', '2023-06-07 09:45:00', 'İstanbul', 'Plovdiv,Varna,Nessebar,Sofya', 'Evet', '(1.Gün Bulgaristan / 7 Haziran) Sabah erken saatlerde İstanbul\'dan otobüsle Plovdiv\'e hareket edin. Plovdiv, antik Roma kalıntıları, Osmanlı mimarisi ve modern sanat sahnesi ile ünlüdür. Güzel Evler v', '(2.Gün Bulgaristan / 8 Haziran) Sabah erkenden otobüsle Varna\'ya gitmek için hareket edin. Varna, Karadeniz kıyısında yer alan bir sahil şehridir. Burada, Varna Arkeoloji Müzesi ve Aziz Dimitar Katedr', '(3.Gün Bulgaristan / 9 Haziran) Sabah erkenden Nessebar\'a gitmek için otobüsle hareket edin. Nessebar, Karadeniz kıyısında yer alan küçük bir tarihi kasabadır ve UNESCO Dünya Mirası listesinde yer alm', '(4.Gün Bulgaristan / 10 Haziran) Sabah erkenden Plovdiv\'e geri dönün ve Plovdiv\'deki diğer tarihi ve kültürel yerleri ziyaret edin. Eski Plovdiv bölgesi, Plovdiv Roman Stadyumu ve Saat Kulesi gibi yer', '(5.Gün Bulgaristan / 11 Haziran) Gezinin son günü, Varna\'daki plajları ve sahil şeridini keşfedebilir veya Varna\'da alışveriş yapabilirsiniz. Dönüş yolculuğu için öğleden sonra İstanbul\'a geri dönün.'),
('ytistiran', '2023-05-06 09:45:00', 'İstanbul', 'Tebriz,Tahran,İsfahan,Şiraz', 'Evet', '(1.Gün İran / 6 Mayıs) Sabah erken saatlerde Tahran\'a varış.İlk önce Golestan Sarayı gibi kültürel mirasları ziyaret edin.Ardından, İran Ulusal Müzesi\'ne gidin ve İran\'ın tarihi hakkında daha fazla bi', '(2.Gün İran / 7 Mayıs) Sabah erkenden otobüsle İsfahan\'a gitmek için hareket edin.İsfahan\'ın tarihi merkezi Meydan-e Imam\'ı (İmam Meydanı) ziyaret edin ve Ali Kapısı ve İmam Camii gibi mimari harikala', '(3.Gün İran / 8 Mayıs) Sabah erkenden otobüsle Şiraz\'a gitmek için hareket edin. Şiraz\'daki ilk duraklarınız arasında Pers İmparatorluğu\'nun kalıntılarından oluşan Persepolis ve Naqsh-e Rustam kaya ka', '(4.Gün İran / 9 Mayıs) Sabah erkenden otobüsle Tebriz\'e gitmek için hareket edin. azd\'ın tarihi merkezi, UNESCO Dünya Mirası listesine dahil edilmiştir. Burada, Meydan-e Amir Chakhmaq, Jameh Camii ve ', '(5.Gün İran / 10 Mayıs) Gezinin son günü otelde alacağımız kahvaltı sonrası öğlen saatlerine kadar serbest zaman'),
('ytistispanya', '2023-07-15 10:00:00', 'İstanbul', 'Barselona,Valensiya,Madrid,Toledo', 'Evet', '(1.Gün İspanya / 15 Temmuz) Barselona\'ya varış ve turistik alanlara transfer. Casa Batlló, La Pedrera ve Park Güell gibi mimari harikaları görebilirsiniz.', '(2.Gün İspanya / 16 Temmuz) Şehrin tadını çıkarın ve tarihi La Rambla caddesi ve Boqueria pazarını ziyaret edin. Daha sonra Sagrada Familia\'yı ziyaret edin.', '(3.Gün İspanya / 17 Temmuz) Sabah erken kalkın ve Valensiya\'ya gitmek için hareket edin. City of Arts and Sciences\'i, özellikle de Oceanografik akvaryumunu ziyaret edin. Ayrıca Mercado Central\'da lezz', '(4.Gün İspanya / 18 Temmuz) Sabah Madrid\'e gitmek için hareket edin ve Prado Müzesi\'ni ziyaret edin. Daha sonra Plaza Mayor ve Retiro Parkı gibi diğer turistik yerleri ziyaret edin.', '(5.Gün İspanya / 19 Temmuz) Sabah Toledo\'ya gitmek için hareket edin ve Toledo Katedrali\'ni, Alcazar\'ı ve Toledo\'da dar sokakları keşfedin. Daha sonra İstanbul\'a dönüş için Madrid\'e geri dönün.'),
('ytistsrbstn', '2023-07-01 09:00:00', 'İstanbul', 'Belgrad, Novi Sad, Niş, Subotica', 'Evet', '(1.Gün Sırbistan / 1 Temmuz) Sabah saatlerinde İstanbul Havalimanı\'ndan Belgrad\'a hareket ediyoruz. Varışımızın ardından şehir turu yapıyoruz ve Kalemegdan Kalesi, Knez Mihailova Caddesi ve Ulusal Müz', '(2. Gün Sırbistan / 2 Temmuz) Sabah kahvaltısı sonrası Novi Sad\'a doğru yola çıkıyoruz. Varışımızın ardından Petrovaradin Kalesi ve Sava Nehri kıyısındaki yürüyüş yollarını keşfediyoruz. Daha sonra Sr', '(3. Gün Sırbistan / 3 Temmuz) Sabah erken kahvaltı sonrası Niş şehrine doğru yola çıkıyoruz. Varışımızın ardından Niş Kalesi, Mediana Arkeoloji Parkı ve Çele Kula Kalesi gibi yerleri ziyaret ediyoruz.', '(4. Gün Sırbistan / 4 Temmuz) Sabah kahvaltısının ardından Subotica şehrine doğru yola çıkıyoruz. Varışımızın ardından Subotica Sinagogu, Palić Gölü ve Raichle Sarayı gibi turistik yerleri ziyaret edi', '(5. Gün Sırbistan / 5 Temmuz) Kahvaltı sonrası Belgrad\'a geri dönüyoruz ve serbest zamanınızı geçirmek için öneriler sunuyoruz. Akşam saatlerinde İstanbul\'a geri dönüyoruz.'),
('ytistyunan', '2023-06-20 10:00:00', 'İstanbul', 'Selanik, Atina, Mykonos, Santorini', 'Evet', '(1.Gün Yunanistan / 20 Haziran) Selanik\'e varış ve otelde konaklama. Selanik\'te Beyaz Kule, Osmanlı Hamamı ve Aristoteles Meydanı gibi tarihi yerleri ziyaret edin.', '(2.Gün Yunanistan / 21 Haziran) Sabah erken saatlerde Atina\'ya gitmek için hareket edin. Atina\'da Olimpiyat Stadyumu, Akropolis ve Parthenon Tapınağı gibi tarihi yapıları görün. Ayrıca Plaka bölgesind', '(3.Gün Yunanistan / 22 Haziran) Sabah Mykonos\'a uçakla gitmek için hareket edin. Burada turkuaz renkli denizinde yüzme ve Platis Gialos plajında güneşlenme fırsatı yakalayın. Gece hayatı ile ünlü Litt', '(4.Gün Yunanistan / 23 Haziran) Sabah Santorini\'ye feribotla gitmek için hareket edin. Santorini\'de Kızıl Plaj ve Perissa Plajı gibi güzel plajları keşfedin. Eski Thira ve Fira\'daki tarihi kalıntıları', '(5.Gün Yunanistan / 24 Haziran) Sabah otelde alacağınız kahvaltının ardından Atina\'ya geri dönün ve Türkiye\'ye uçmak için havalimanına gitmek için hareket edin.'),
('ytizmirmaldivler', '2023-08-10 10:00:00', 'İzmir', 'Male, Kuzey Ari Atoll, Rasdhoo Atoll', 'Hayır', '(1.Gün Maldivler / 10 Ağustos) Male Havaalanı\'na varış ve otele transfer. Akşam yemeği ve serbest zaman.', '(2.Gün  Maldivler / 11 Ağustos) Sabah kahvaltısından sonra Kuzey Ari Atoll\'daki mavi sulara açılmak için teknede bir gün geçirin. Şnorkelle dalın ve renkli mercan resiflerinde yüzün.', '(3.Gün  Maldivler / 12 Ağustos) Sabah kahvaltısından sonra Rasdhoo Atoll\'una hareket edin ve günün geri kalanında rahatlayın veya adayı keşfetmek için serbest zaman geçirin.', '(4.Gün  Maldivler / 13 Ağustos) Sabah kahvaltısından sonra, Maldivler\'de ünlü bir plaj olan Bikini Beach\'e gitmek için tekneye binin. Akşam yemeği ve serbest zaman.', '(5.Gün  Maldivler / 14 Ağustos) Otelde alınacak kahvaltının ardından, Male Havaalanı\'na transfer ve İstanbul\'a uçuş'),
('ytizmirmisir', '2023-07-15 08:30:00', 'İzmir', 'Kahire, Giza Piramitleri', 'Hayır', '(1.Gün Mısır / 15 Temmuz) Sabah saatlerinde Kahire\'ye varış. Check-in işlemlerinin ardından ilk durağımız Mısır Müzesi olacak. Ardından Giza Piramitleri\'ni ve Sfenks\'i ziyaret edeceğiz. ', '(2. Gün Mısır / 16 Temmuz) Sabah erkenden kalkarak, Mısır\'ın en önemli turistik yerlerinden biri olan Luxor\'a gitmek için havalimanına transfer. Luxor\'da Karnak Tapınağı, Kral Vadisi ve Hatshepsut Tap', '(3. Gün Mısır / 17 Temmuz) Sabah Kahire\'deki otelimizde kahvaltı yaparak günümüze başlayacağız. Daha sonra Kahire\'nin en eski bölgesi olan İslam Mahallesi\'ni keşfedeceğiz ve El Muizz Caddesi\'nde yürüy', '(4. Gün Mısır / 18 Temmuz) Sabah kahvaltısının ardından Mısır\'ın en büyük antik kentlerinden biri olan Şarm El-Şeyh\'e gitmek için havalimanına transfer yapacağız. Şarm El-Şeyh\'de serbest zaman geçireb', '(5. Gün Mısır / 19 Temmuz) Gezinin son günü, Kahire\'deki otelimizde kahvaltı yaptıktan sonra alışveriş yapmak için El Moez Caddesi\'ne gidebilirsiniz. Sonrasında havalimanına transfer olacak ve İstanbu');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `yurtdisitur`
--

CREATE TABLE `yurtdisitur` (
  `idYurtdisiTur` int(11) NOT NULL,
  `TurProgrami_idTurProgrami` varchar(20) NOT NULL,
  `HizmetBilgisi_idHizmetBilgisi` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `yurtdisitur`
--

INSERT INTO `yurtdisitur` (`idYurtdisiTur`, `TurProgrami_idTurProgrami`, `HizmetBilgisi_idHizmetBilgisi`) VALUES
(1, 'ytankarnavutluk', 1),
(2, 'ytankdubai', 2),
(3, 'ytankitalya', 3),
(4, 'ytistbulgaria', 4),
(5, 'ytistiran', 5),
(6, 'ytistispanya', 6),
(7, 'ytistsrbstn', 7),
(8, 'ytistyunan', 8),
(9, 'ytizmirmaldivler', 9),
(10, 'ytizmirmisir', 10);

-- --------------------------------------------------------

--
-- Görünüm yapısı durumu `yurtdisiturview`
-- (Asıl görünüm için aşağıya bakın)
--
CREATE TABLE `yurtdisiturview` (
`idYurtdisiTur` int(11)
,`adi` varchar(100)
,`fiyat` int(11)
,`resimUrl` varchar(300)
,`baslangicTarihi` datetime
,`baslangicKonumu` varchar(45)
,`guzergah` varchar(150)
,`vizeDurumu` varchar(10)
,`gunBir` varchar(200)
,`gunIki` varchar(200)
,`gunUc` varchar(200)
,`gunDort` varchar(200)
,`gunBes` varchar(200)
);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `__efmigrationshistory`
--

CREATE TABLE `__efmigrationshistory` (
  `MigrationId` varchar(150) NOT NULL,
  `ProductVersion` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Görünüm yapısı `otelview`
--
DROP TABLE IF EXISTS `otelview`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `otelview`  AS SELECT `otel`.`idOtel` AS `idOtel`, `otel`.`adres` AS `adres`, `otel`.`puan` AS `puan`, `otel`.`tema` AS `tema`, `hizmetbilgisi`.`adi` AS `adi`, `hizmetbilgisi`.`fiyat` AS `fiyat`, `hizmetbilgisi`.`resimUrl` AS `resimUrl` FROM (`otel` join `hizmetbilgisi` on(`otel`.`HizmetBilgisi_idHizmetBilgisi` = `hizmetbilgisi`.`idHizmetBilgisi`)) ;

-- --------------------------------------------------------

--
-- Görünüm yapısı `rezervasyonview`
--
DROP TABLE IF EXISTS `rezervasyonview`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `rezervasyonview`  AS SELECT `rezervasyon`.`idRezervasyon` AS `idRezervasyon`, `rezervasyon`.`tarihi` AS `tarihi`, `rezervasyon`.`yetiskinSayisi` AS `yetiskinSayisi`, `rezervasyon`.`cocukSayisi` AS `cocukSayisi`, `rezervasyon`.`girisTarihi` AS `girisTarihi`, `rezervasyon`.`cikisTarihi` AS `cikisTarihi`, `musteri`.`idMusteri` AS `idMusteri`, `musteri`.`adi` AS `adi`, `musteri`.`soyadi` AS `soyadi`, `musteri`.`telefonNumarasi` AS `telefonNumarasi`, `musteri`.`epostaAdresi` AS `epostaAdresi`, `hizmet`.`hizmetTabloAdi` AS `hizmetTabloAdi`, `hizmet`.`hizmetTabloId` AS `hizmettabloId` FROM ((`rezervasyon` join `musteri` on(`rezervasyon`.`Musteri_idMusteri` = `musteri`.`idMusteri`)) join `hizmet` on(`rezervasyon`.`Hizmet_idHizmet` = `hizmet`.`idHizmet`)) ;

-- --------------------------------------------------------

--
-- Görünüm yapısı `tekneturview`
--
DROP TABLE IF EXISTS `tekneturview`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `tekneturview`  AS SELECT `teknetur`.`idTekneTur` AS `idTekneTur`, `hizmetbilgisi`.`adi` AS `adi`, `hizmetbilgisi`.`fiyat` AS `fiyat`, `hizmetbilgisi`.`resimUrl` AS `resimUrl`, `turprogrami`.`baslangicTarihi` AS `baslangicTarihi`, `turprogrami`.`baslangicKonumu` AS `baslangicKonumu`, `turprogrami`.`guzergah` AS `guzergah`, `turprogrami`.`vizeDurumu` AS `vizeDurumu`, `turprogrami`.`gunBir` AS `gunBir`, `turprogrami`.`gunIki` AS `gunIki`, `turprogrami`.`gunUc` AS `gunUc`, `turprogrami`.`gunDort` AS `gunDort`, `turprogrami`.`gunBes` AS `gunBes` FROM ((`teknetur` join `hizmetbilgisi` on(`teknetur`.`HizmetBilgisi_idHizmetBilgisi` = `hizmetbilgisi`.`idHizmetBilgisi`)) join `turprogrami` on(`teknetur`.`TurProgrami_idTurProgrami` = `turprogrami`.`idTurProgrami`)) ;

-- --------------------------------------------------------

--
-- Görünüm yapısı `yurtdisiturview`
--
DROP TABLE IF EXISTS `yurtdisiturview`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `yurtdisiturview`  AS SELECT `yurtdisitur`.`idYurtdisiTur` AS `idYurtdisiTur`, `hizmetbilgisi`.`adi` AS `adi`, `hizmetbilgisi`.`fiyat` AS `fiyat`, `hizmetbilgisi`.`resimUrl` AS `resimUrl`, `turprogrami`.`baslangicTarihi` AS `baslangicTarihi`, `turprogrami`.`baslangicKonumu` AS `baslangicKonumu`, `turprogrami`.`guzergah` AS `guzergah`, `turprogrami`.`vizeDurumu` AS `vizeDurumu`, `turprogrami`.`gunBir` AS `gunBir`, `turprogrami`.`gunIki` AS `gunIki`, `turprogrami`.`gunUc` AS `gunUc`, `turprogrami`.`gunDort` AS `gunDort`, `turprogrami`.`gunBes` AS `gunBes` FROM ((`yurtdisitur` join `hizmetbilgisi` on(`yurtdisitur`.`HizmetBilgisi_idHizmetBilgisi` = `hizmetbilgisi`.`idHizmetBilgisi`)) join `turprogrami` on(`yurtdisitur`.`TurProgrami_idTurProgrami` = `turprogrami`.`idTurProgrami`)) ;

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `hizmet`
--
ALTER TABLE `hizmet`
  ADD PRIMARY KEY (`idHizmet`);

--
-- Tablo için indeksler `hizmetbilgisi`
--
ALTER TABLE `hizmetbilgisi`
  ADD PRIMARY KEY (`idHizmetBilgisi`);

--
-- Tablo için indeksler `musteri`
--
ALTER TABLE `musteri`
  ADD PRIMARY KEY (`idMusteri`),
  ADD KEY `idx_eposta` (`epostaAdresi`),
  ADD KEY `idx_sifre` (`sifre`);

--
-- Tablo için indeksler `newmusteri`
--
ALTER TABLE `newmusteri`
  ADD KEY `FK_musteri` (`idMusteri`);

--
-- Tablo için indeksler `otel`
--
ALTER TABLE `otel`
  ADD PRIMARY KEY (`idOtel`),
  ADD KEY `fk_Otel_HizmetBilgisi1_idx` (`HizmetBilgisi_idHizmetBilgisi`);

--
-- Tablo için indeksler `rezervasyon`
--
ALTER TABLE `rezervasyon`
  ADD PRIMARY KEY (`idRezervasyon`),
  ADD KEY `fk_Rezervasyon_Musteri1_idx` (`Musteri_idMusteri`),
  ADD KEY `fk_Rezervasyon_Hizmet1_idx` (`Hizmet_idHizmet`);

--
-- Tablo için indeksler `teknetur`
--
ALTER TABLE `teknetur`
  ADD PRIMARY KEY (`idTekneTur`),
  ADD KEY `fk_TekneTur_TurProgrami1_idx` (`TurProgrami_idTurProgrami`),
  ADD KEY `fk_TekneTur_HizmetBilgisi1_idx` (`HizmetBilgisi_idHizmetBilgisi`);

--
-- Tablo için indeksler `turprogrami`
--
ALTER TABLE `turprogrami`
  ADD PRIMARY KEY (`idTurProgrami`);

--
-- Tablo için indeksler `yurtdisitur`
--
ALTER TABLE `yurtdisitur`
  ADD PRIMARY KEY (`idYurtdisiTur`),
  ADD KEY `fk_YurtdisiTur_TurProgrami1_idx` (`TurProgrami_idTurProgrami`),
  ADD KEY `fk_YurtdisiTur_HizmetBilgisi1_idx` (`HizmetBilgisi_idHizmetBilgisi`);

--
-- Tablo için indeksler `__efmigrationshistory`
--
ALTER TABLE `__efmigrationshistory`
  ADD PRIMARY KEY (`MigrationId`);

--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `newmusteri`
--
ALTER TABLE `newmusteri`
  ADD CONSTRAINT `FK_musteri` FOREIGN KEY (`idMusteri`) REFERENCES `musteri` (`idMusteri`);

--
-- Tablo kısıtlamaları `otel`
--
ALTER TABLE `otel`
  ADD CONSTRAINT `fk_Otel_HizmetBilgisi1` FOREIGN KEY (`HizmetBilgisi_idHizmetBilgisi`) REFERENCES `hizmetbilgisi` (`idHizmetBilgisi`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Tablo kısıtlamaları `rezervasyon`
--
ALTER TABLE `rezervasyon`
  ADD CONSTRAINT `fk_Rezervasyon_Hizmet1` FOREIGN KEY (`Hizmet_idHizmet`) REFERENCES `hizmet` (`idHizmet`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Rezervasyon_Musteri1` FOREIGN KEY (`Musteri_idMusteri`) REFERENCES `musteri` (`idMusteri`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Tablo kısıtlamaları `teknetur`
--
ALTER TABLE `teknetur`
  ADD CONSTRAINT `fk_TekneTur_HizmetBilgisi1` FOREIGN KEY (`HizmetBilgisi_idHizmetBilgisi`) REFERENCES `hizmetbilgisi` (`idHizmetBilgisi`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_TekneTur_TurProgrami1` FOREIGN KEY (`TurProgrami_idTurProgrami`) REFERENCES `turprogrami` (`idTurProgrami`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Tablo kısıtlamaları `yurtdisitur`
--
ALTER TABLE `yurtdisitur`
  ADD CONSTRAINT `fk_YurtdisiTur_HizmetBilgisi1` FOREIGN KEY (`HizmetBilgisi_idHizmetBilgisi`) REFERENCES `hizmetbilgisi` (`idHizmetBilgisi`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_YurtdisiTur_TurProgrami1` FOREIGN KEY (`TurProgrami_idTurProgrami`) REFERENCES `turprogrami` (`idTurProgrami`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
