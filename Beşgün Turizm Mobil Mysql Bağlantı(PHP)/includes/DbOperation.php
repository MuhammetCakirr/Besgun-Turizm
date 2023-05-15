<?php 
class DbOperation
{
    private $con;
 
    function __construct()
    {
        require_once dirname(__FILE__) . '/DbConnect.php';
        $db = new DbConnect();
        $this->con = $db->connect();
    }
	public function getYurtdisiTurView(){
		$stmt = $this->con->prepare("SELECT * FROM yurtdisiturview");
		$stmt->execute();
		$stmt->bind_result($idYurtdisiTur,$adi,$fiyat,$resimUrl,$baslangicTarihi,$baslangicKonumu,$guzergah,$vizeDurumu,$gunBir,$gunIki,$gunUc,$gunDort,$gunBes);
		$artists = array();
		
		while($stmt->fetch()){
			$temp = array(); 
			$temp['idYurtdisiTur'] = $idYurtdisiTur; 
			$temp['adi'] = $adi; 
			$temp['fiyat'] = $fiyat; 
			$temp['resimUrl'] = $resimUrl; 
			$temp['baslangicTarihi'] = $baslangicTarihi; 
			$temp['baslangicKonumu'] = $baslangicKonumu; 
			$temp['guzergah'] = $guzergah; 
			$temp['vizeDurumu'] = $vizeDurumu;
			$temp['gunBir'] = $gunBir; 
			$temp['gunIki'] = $gunIki; 
			$temp['gunUc'] = $gunUc; 
			$temp['gunDort'] = $gunDort;
			$temp['gunBes'] = $gunBes;
			array_push($artists, $temp);
		}
		$stmt->close();
		return $artists; 
	}
	public function getTekneTurView(){
		$stmt = $this->con->prepare("SELECT * FROM tekneturview");
		$stmt->execute();
		$stmt->bind_result($idTekneTur,$adi,$fiyat,$resimUrl,$baslangicTarihi,$baslangicKonumu,$guzergah,$vizeDurumu,$gunBir,$gunIki,$gunUc,$gunDort,$gunBes);
		$artists = array();
		
		while($stmt->fetch()){
			$temp = array(); 
			$temp['idTekneTur'] = $idTekneTur; 
			$temp['adi'] = $adi; 
			$temp['fiyat'] = $fiyat; 
			$temp['resimUrl'] = $resimUrl; 
			$temp['baslangicTarihi'] = $baslangicTarihi; 
			$temp['baslangicKonumu'] = $baslangicKonumu; 
			$temp['guzergah'] = $guzergah; 
			$temp['vizeDurumu'] = $vizeDurumu;
			$temp['gunBir'] = $gunBir; 
			$temp['gunIki'] = $gunIki; 
			$temp['gunUc'] = $gunUc; 
			$temp['gunDort'] = $gunDort;
			$temp['gunBes'] = $gunBes;
			array_push($artists, $temp);
		}
		$stmt->close();
		return $artists; 
	}
    public function getOtelView(){
		$stmt = $this->con->prepare("SELECT * FROM otelview");
		$stmt->execute();
		$stmt->bind_result($idOtel, $adres,$puan,$tema,$adi,$fiyat,$resimUrl);
		$artists = array();
		
		while($stmt->fetch()){
			$temp = array(); 
			$temp['idOtel'] = $idOtel; 
			$temp['adres'] = $adres; 
			$temp['puan'] = $puan; 
			$temp['tema'] = $tema; 
			$temp['adi'] = $adi; 
			$temp['fiyat'] = $fiyat; 
			$temp['resimUrl'] = $resimUrl; 
			array_push($artists, $temp);
		}
		$stmt->close();
		return $artists; 
	}
	public function updateByIdRezervasyon($id, $yetiskinSayisi,$cocukSayisi,$girisTarihi,$cikisTarihi){
		$stmt = $this->con->prepare("UPDATE rezervasyon SET yetiskinSayisi=?, cocukSayisi=?, girisTarihi=?, cikisTarihi=? WHERE idRezervasyon = ?");
		$stmt->bind_param("ssssi", $yetiskinSayisi,$cocukSayisi,$girisTarihi,$cikisTarihi, $id);
		if($stmt->execute()) {
			return true; 
		} else {
			return false;
		}
	}
	public function updateByIdMusteri($id,$adi, $soyadi,$sifre,$telefonNumarasi,$epostaAdresi,$dogumTarihi){
		$stmt = $this->con->prepare("UPDATE musteri SET adi=?, soyadi=?, sifre=?, telefonNumarasi=?, epostaAdresi=?, dogumTarihi=? WHERE idMusteri = ?");
		$stmt->bind_param("ssssssi", $adi, $soyadi, $sifre, $telefonNumarasi, $epostaAdresi, $dogumTarihi, $id);
		if($stmt->execute()) {
			return true; 
		} else {
			return false;
		}
	}
	public function deleteByIdMusteri($id){
		$stmt = $this->con->prepare("DELETE FROM musteri WHERE idMusteri = $id");
		if($stmt->execute()) {
			return true; 
		} else {
			return false;
		}
	}
	public function deleteByIdRezervasyon($id){
		$stmt = $this->con->prepare("DELETE FROM rezervasyon WHERE idMusteri = $id");
		if($stmt->execute()) {
			return true; 
		} else {
			return false;
		}
	}
    public function getHizmetCount(){
		$sql = "SELECT COUNT(*) as count FROM hizmet";
		$result = $this->con->query($sql);
        $row = $result->fetch_assoc();
		$count = $row['count'];
		return $count;

	}
    public function createHizmet($hizmetTabloId,$hizmetTabloAdi){
		$sql = "SELECT COUNT(*) as count FROM hizmet";
		$result = $this->con->query($sql);
        $row = $result->fetch_assoc();
		$count = $row['count'];
		if ($count > 0) {
			$stmt = $this->con->prepare("INSERT INTO hizmet (`idHizmet`, `hizmetTabloId`, `hizmetTabloAdi`) VALUES ($count+1, ?, ?)");
			$stmt->bind_param("is", $hizmetTabloId, $hizmetTabloAdi);
			if($stmt->execute()) {
				return true; 
			} else {
				return false;
			}
		} else {
			$stmt = $this->con->prepare("INSERT INTO hizmet (`idHizmet`, `hizmetTabloId`, `hizmetTabloAdi`) VALUES (1, ?, ?)");
			$stmt->bind_param("is", $hizmetTabloId, $hizmetTabloAdi);
			if($stmt->execute()) {
				return true; 
			} else {
				return false;
			}
		}
		return false;

	}
    public function createRezervasyon($tarihi, $yetiskinSayisi,$cocukSayisi,$Musteri_idMusteri,$girisTarihi,$cikisTarihi){

		$result = $this->con->query("SELECT COUNT(*) as count FROM hizmet");
		$row = $result->fetch_assoc();
		$idHizmet = $row['count'];
		$sql = "SELECT COUNT(*) as count FROM rezervasyon";
		$result = $this->con->query($sql);
		$row = $result->fetch_assoc();
		$count = $row['count'];
		if ($result->num_rows > 0) {
			$stmt = $this->con->prepare("INSERT INTO rezervasyon (`idRezervasyon`, `tarihi`, `yetiskinSayisi`,`cocukSayisi`,`Musteri_idMusteri`,`Hizmet_idHizmet`,`girisTarihi`,`cikisTarihi`) VALUES ($count+1, ?, ?, ?, ?, ?, ?, ?)");
			$stmt->bind_param("siiiiss", $tarihi, $yetiskinSayisi, $cocukSayisi, $Musteri_idMusteri, $idHizmet,$girisTarihi,$cikisTarihi);
			if($stmt->execute()) {
				return true; 
			} else {
				return false;
			}
		} else {
			$stmt = $this->con->prepare("INSERT INTO rezervasyon (`idRezervasyon`, `tarihi`, `yetiskinSayisi`,`cocukSayisi`,`Musteri_idMusteri`,`Hizmet_idHizmet`,`girisTarihi`,`cikisTarihi`) VALUES (1, ?, ?, ?, ?, ?, ?, ?)");
			$stmt->bind_param("siiiiss", $tarihi, $yetiskinSayisi, $cocukSayisi, $Musteri_idMusteri, $idHizmet,$girisTarihi,$cikisTarihi);
			if($stmt->execute()) {
				return true; 
			} else {
				return false;
			}
		}
		
		return false;
	}
	public function createMusteri($adi, $soyadi,$sifre,$telefonNumarasi,$epostaAdresi,$dogumTarihi){
		$sql = "SELECT COUNT(*) as count FROM musteri";
		$result = $this->con->query($sql);
        $row = $result->fetch_assoc();
		$count = $row['count'];
		if ($result->num_rows > 0) {
			$stmt = $this->con->prepare("INSERT INTO musteri (`idMusteri`, `adi`, `soyadi`,`sifre`,`telefonNumarasi`,`epostaAdresi`,`dogumTarihi`) VALUES ($count+1, ?, ?,?, ?, ?, ?)");
			$stmt->bind_param("ssssss", $adi, $soyadi,$sifre, $telefonNumarasi, $epostaAdresi, $dogumTarihi);
			if($stmt->execute()) {
				return true; 
			} else {
				return false;
			}
		} else {
			$stmt = $this->con->prepare("INSERT INTO musteri (`idMusteri`, `adi`, `soyadi`,`sifre`,`telefonNumarasi`,`epostaAdresi`,`dogumTarihi`) VALUES (1, ?,?, ?, ?, ?, ?)");
			$stmt->bind_param("ssssss", $adi, $soyadi,$sifre, $telefonNumarasi, $epostaAdresi, $dogumTarihi);
			if($stmt->execute()) {
				return true; 
			} else {
				return false;
			} 
		}
		return false;
		
	}
	public function getOtel(){
		$stmt = $this->con->prepare("SELECT * FROM otel");
		$stmt->execute();
		$stmt->bind_result($idOtel, $adres,$HizmetBilgisi_idHizmetBilgisi,$puan,$tema);
		$artists = array();
		
		while($stmt->fetch()){
			$temp = array(); 
			$temp['idOtel'] = $idOtel; 
			$temp['adres'] = $adres; 
			$temp['HizmetBilgisi_idHizmetBilgisi'] = $HizmetBilgisi_idHizmetBilgisi; 
			$temp['puan'] = $puan; 
			$temp['tema'] = $tema; 
			array_push($artists, $temp);
		}
		$stmt->close();
		return $artists; 
	}
	public function getByIdOtel($id){
		$stmt = $this->con->prepare("SELECT * FROM otel WHERE idOtel = ?");
		$stmt->bind_param("i", $id);
		$stmt->execute();
		$stmt->bind_result($idOtel, $adres,$HizmetBilgisi_idHizmetBilgisi,$puan,$tema);
		$artists = array();
		
		while($stmt->fetch()){
			$temp = array(); 
			$temp['idOtel'] = $idOtel; 
			$temp['adres'] = $adres; 
			$temp['HizmetBilgisi_idHizmetBilgisi'] = $HizmetBilgisi_idHizmetBilgisi; 
			$temp['puan'] = $puan; 
			$temp['tema'] = $tema; 
			array_push($artists, $temp);
		}
		$stmt->close();
		return $artists; 
	}
	public function getByIdHizmet($id){
		$stmt = $this->con->prepare("SELECT * FROM hizmet WHERE idHizmet = ?");
		$stmt->bind_param("i", $id);
		$stmt->execute();
		$stmt->bind_result($idHizmet, $hizmetTabloId, $hizmetTabloAdi);
		$artists = array();
		
		while($stmt->fetch()){
			$temp = array(); 
			$temp['idHizmet'] = $idHizmet; 
			$temp['hizmetTabloId'] = $hizmetTabloId; 
			$temp['hizmetTabloAdi'] = $hizmetTabloAdi; 
			array_push($artists, $temp);
		}
		$stmt->close();
		return $artists; 
	}
	public function getMusteri(){
		$stmt = $this->con->prepare("SELECT * FROM musteri ");
		$stmt->execute();
		$stmt->bind_result($idMusteri, $adi, $soyadi,$sifre,$telefonNumarasi,$epostaAdresi,$dogumTarihi);
		$artists = array();
		
		while($stmt->fetch()){
			$temp = array(); 
			$temp['idMusteri'] = $idMusteri; 
			$temp['adi'] = $adi; 
			$temp['soyadi'] = $soyadi; 
			$temp['sifre'] = $sifre; 
			$temp['telefonNumarasi'] = $telefonNumarasi; 
			$temp['epostaAdresi'] = $epostaAdresi; 
			$temp['dogumTarihi'] = $dogumTarihi; 
			array_push($artists, $temp);
		}
		$stmt->close();
		return $artists; 
	}
	public function getByIdMusteri($id){
		$stmt = $this->con->prepare("SELECT * FROM musteri WHERE idMusteri = ?");
		$stmt->bind_param("i", $id);
		$stmt->execute();
		$stmt->bind_result($idMusteri, $adi, $soyadi,$sifre,$telefonNumarasi,$epostaAdresi,$dogumTarihi);
		$artists = array();
		
		while($stmt->fetch()){
			$temp = array(); 
			$temp['idMusteri'] = $idMusteri; 
			$temp['adi'] = $adi; 
			$temp['soyadi'] = $soyadi; 
			$temp['sifre'] = $sifre; 
			$temp['telefonNumarasi'] = $telefonNumarasi; 
			$temp['epostaAdresi'] = $epostaAdresi; 
			$temp['dogumTarihi'] = $dogumTarihi; 
			array_push($artists, $temp);
		}
		$stmt->close();
		return $artists; 
	}
	public function getRezervasyon(){
		$stmt = $this->con->prepare("SELECT * FROM rezervasyon");
		$stmt->execute();
		$stmt->bind_result($idRezervasyon, $tarihi, $yetiskinSayısı,$cocukSayisi,$Musteri_idMusteri,$Hizmet_idHizmet,$girisTarihi,$cikisTarihi);
		$artists = array();
		
		while($stmt->fetch()){
			$temp = array(); 
			$temp['idRezervasyon'] = $idRezervasyon; 
			$temp['tarihi'] = $tarihi; 
			$temp['yetiskinSayısı'] = $yetiskinSayısı; 
			$temp['cocukSayisi'] = $cocukSayisi; 
			$temp['Musteri_idMusteri'] = $Musteri_idMusteri; 
			$temp['Hizmet_idHizmet'] = $Hizmet_idHizmet; 
			$temp['girisTarihi'] = $girisTarihi; 
			$temp['cikisTarihi'] = $cikisTarihi; 
			array_push($artists, $temp);
		}
		$stmt->close();
		return $artists; 
	}
	public function getByIdRezervasyon($id){
		$stmt = $this->con->prepare("SELECT * FROM rezervasyon WHERE idRezervasyon = ?");
		$stmt->bind_param("i", $id);
		$stmt->execute();
		$stmt->bind_result($idRezervasyon, $tarihi, $yetiskinSayısı,$cocukSayisi,$Musteri_idMusteri,$Hizmet_idHizmet,$girisTarihi,$cikisTarihi);
		$artists = array();
		
		while($stmt->fetch()){
			$temp = array(); 
			$temp['idRezervasyon'] = $idRezervasyon; 
			$temp['tarihi'] = $tarihi; 
			$temp['yetiskinSayısı'] = $yetiskinSayısı; 
			$temp['cocukSayisi'] = $cocukSayisi; 
			$temp['Musteri_idMusteri'] = $Musteri_idMusteri; 
			$temp['Hizmet_idHizmet'] = $Hizmet_idHizmet;
			$temp['girisTarihi'] = $girisTarihi; 
			$temp['cikisTarihi'] = $cikisTarihi;  
			array_push($artists, $temp);
		}
		$stmt->close();
		return $artists; 
	}
	public function getByIdHizmetBilgisi($id){
		$stmt = $this->con->prepare("SELECT * FROM hizmetBilgisi WHERE idHizmetBilgisi = ?");
		$stmt->bind_param("i", $id);
		$stmt->execute();
		$stmt->bind_result($idHizmetBilgisi, $adi, $fiyat,$resimUrl);
		$artists = array();
		
		while($stmt->fetch()){
			$temp = array(); 
			$temp['idHizmetBilgisi'] = $idHizmetBilgisi; 
			$temp['adi'] = $adi; 
			$temp['fiyat'] = $fiyat; 
			$temp['resimUrl'] = $resimUrl;  
			array_push($artists, $temp);
		}
		$stmt->close();
		return $artists; 
	}
	public function getByIdTurProgrami($id){
		$stmt = $this->con->prepare("SELECT * FROM turprogrami WHERE idTurProgrami = ?");
		$stmt->bind_param("s", $id);
		$stmt->execute();
		$stmt->bind_result($idTurProgrami, $baslangicTarihi, $baslangicKonumu,$guzergah,$vizeDurumu,$gunBir,$gunIki,$gunUc,$gunDort,$gunBes);
		$artists = array();
		
		while($stmt->fetch()){
			$temp = array(); 
			$temp['idTurProgrami'] = $idTurProgrami; 
			$temp['baslangicTarihi'] = $baslangicTarihi; 
			$temp['baslangicKonumu'] = $baslangicKonumu; 
			$temp['guzergah'] = $guzergah; 
			$temp['vizeDurumu'] = $vizeDurumu; 
			$temp['gunBir'] = $gunBir; 
			$temp['gunIki'] = $gunIki; 
			$temp['gunUc'] = $gunUc; 
			$temp['gunDort'] = $gunDort; 
			$temp['gunBes'] = $gunBes; 
			array_push($artists, $temp);
		}
		$stmt->close();
		return $artists; 
	}
	public function getTekneTur(){
		$stmt = $this->con->prepare("SELECT * FROM teknetur");
		$stmt->execute();
		$stmt->bind_result($idTekneTur, $TurProgrami_idTurProgrami, $HizmetBilgisi_idHizmetBilgisi);
		$artists = array();
		
		while($stmt->fetch()){
			$temp = array(); 
			$temp['idTekneTur'] = $idTekneTur; 
			$temp['TurProgrami_idTurProgrami'] = $TurProgrami_idTurProgrami; 
			$temp['HizmetBilgisi_idHizmetBilgisi'] = $HizmetBilgisi_idHizmetBilgisi; 
			array_push($artists, $temp);
		}
		$stmt->close();
		return $artists; 
	}
	public function getByIdTekneTur($id){
		$stmt = $this->con->prepare("SELECT * FROM teknetur WHERE idTekneTur = ?");
		$stmt->bind_param("i", $id);
		$stmt->execute();
		$stmt->bind_result($idTekneTur, $TurProgrami_idTurProgrami, $HizmetBilgisi_idHizmetBilgisi);
		$artists = array();
		
		while($stmt->fetch()){
			$temp = array(); 
			$temp['idTekneTur'] = $idTekneTur; 
			$temp['TurProgrami_idTurProgrami'] = $TurProgrami_idTurProgrami; 
			$temp['HizmetBilgisi_idHizmetBilgisi'] = $HizmetBilgisi_idHizmetBilgisi; 
			array_push($artists, $temp);
		}
		$stmt->close();
		return $artists; 
	}
	public function getYurtdisiTur(){
		$stmt = $this->con->prepare("SELECT * FROM yurtdisitur");
		$stmt->execute();
		$stmt->bind_result($idYurtdisiTur, $TurProgrami_idTurProgrami, $HizmetBilgisi_idHizmetBilgisi);
		$artists = array();
		
		while($stmt->fetch()){
			$temp = array(); 
			$temp['idYurtdisiTur'] = $idYurtdisiTur; 
			$temp['TurProgrami_idTurProgrami'] = $TurProgrami_idTurProgrami; 
			$temp['HizmetBilgisi_idHizmetBilgisi'] = $HizmetBilgisi_idHizmetBilgisi; 
			array_push($artists, $temp);
		}
		$stmt->close();
		return $artists; 
	}
	public function getByIdYurtdisiTur($id){
		$stmt = $this->con->prepare("SELECT * FROM yurtdisitur WHERE idYurtdisiTur = ?");
		$stmt->bind_param("i", $id);
		$stmt->execute();
		$stmt->bind_result($idYurtdisiTur, $TurProgrami_idTurProgrami, $HizmetBilgisi_idHizmetBilgisi);
		$artists = array();
		
		while($stmt->fetch()){
			$temp = array(); 
			$temp['idYurtdisiTur'] = $idYurtdisiTur; 
			$temp['TurProgrami_idTurProgrami'] = $TurProgrami_idTurProgrami; 
			$temp['HizmetBilgisi_idHizmetBilgisi'] = $HizmetBilgisi_idHizmetBilgisi; 
			array_push($artists, $temp);
		}
		$stmt->close();
		return $artists; 
	}
}



