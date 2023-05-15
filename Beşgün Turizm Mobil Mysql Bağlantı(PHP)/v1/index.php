<?php 

	require_once '../includes/DbOperation.php';
	
	$response = array(); 

	if(isset($_GET['op'])){
		
		switch($_GET['op']){
			case 'getYurtdisiTurView':
				$db = new DbOperation();
			    $yurtdisitur = $db->getYurtdisiTurView();
				if(count($yurtdisitur)<=0){
					$response['error'] = true; 
					$response['message'] = 'Veritabanı bulunamadı';
				}else{
					$response['error'] = false; 
					$response['yurtdisitur'] = $yurtdisitur;
				}
			break;
			case 'getTekneTurView':
				$db = new DbOperation();
			    $teknetur = $db->getTekneTurView();
				if(count($teknetur)<=0){
					$response['error'] = true; 
					$response['message'] = 'Veritabanı bulunamadı';
				}else{
					$response['error'] = false; 
					$response['teknetur'] = $teknetur;
				}
			break;
			case 'getOtelView':
				$db = new DbOperation();
			    $otel = $db->getOtelView();
				if(count($otel)<=0){
					$response['error'] = true; 
					$response['message'] = 'Veritabanı bulunamadı';
				}else{
					$response['error'] = false; 
					$response['otel'] = $otel;
				}
			break;
			case 'updateMusteri':
			    if(isset($_POST['id']) && isset ($_POST['adi']) && isset ($_POST['soyadi']) && isset ($_POST['sifre']) && isset($_POST['telefonNumarasi']) && isset ($_POST['epostaAdresi']) && isset ($_POST['dogumTarihi'])) {

					$db = new DbOperation(); 
					if($db->updateByIdMusteri($_POST['id'],$_POST['adi'],$_POST['soyadi'],$_POST['sifre'],$_POST['telefonNumarasi'],$_POST['epostaAdresi'],$_POST['dogumTarihi'])){
						$response['error'] = false;
						$response['message'] = 'Musteri güncellendi';
					}else{
						$response['error'] = true;
						$response['message'] = 'Musteri güncellenemedi';
					}
				}
			break;
			
			case 'updateRezervasyon':
			    if(isset($_POST['id']) && isset($_POST['yetiskinSayisi']) && isset($_POST['cocukSayisi']) && isset($_POST['girisTarihi']) && isset($_POST['cikisTarihi'])){
					$db = new DbOperation(); 
					if($db->updateByIdRezervasyon($_POST['id'],$_POST['yetiskinSayisi'],$_POST['cocukSayisi'],$_POST['girisTarihi'],$_POST['cikisTarihi'])){
						$response['error'] = false;
						$response['message'] = 'Rezervasyon güncellendi';
					}else{
						$response['error'] = true;
						$response['message'] = 'Rezervasyon güncellenemedi';
					}
				}
			break;
            
			case 'deleteMusteri':
				if(isset($_POST['id'])){
					$db = new DbOperation(); 
					if($db->deleteByIdMusteri($_POST['id'])){
						$response['error'] = false;
						$response['message'] = 'Musteri silindi';
					}else{
						$response['error'] = true;
							$response['message'] = 'Musteri silinemedi';
					}
				}
			break;
			
			case 'deleteRezervasyon':
				if(isset($_POST['id'])){
					$db = new DbOperation(); 
					if($db->deleteByIdRezervasyon($_POST['id'])){
						$response['error'] = false;
						$response['message'] = 'Rezervasyon silindi';
					}else{
						$response['error'] = true;
						$response['message'] = 'Rezervasyon silinemedi';
					}
				}
			break;
			
			case 'createRezervasyon':
				if(isset($_POST['tarihi']) && isset($_POST['yetiskinSayısı']) && isset ($_POST['cocukSayisi'])&&isset($_POST['Musteri_idMusteri']) && isset($_POST['hizmetTabloId']) && isset($_POST['hizmetTabloAdi'])  && isset($_POST['girisTarihi']) && isset($_POST['cikisTarihi'])){
					$db = new DbOperation(); 
					if($db->createHizmet($_POST['hizmetTabloId'],$_POST['hizmetTabloAdi'])){
						if($db->createRezervasyon($_POST['tarihi'] , $_POST['yetiskinSayısı'], $_POST['cocukSayisi'],$_POST['Musteri_idMusteri'],$_POST['girisTarihi'], $_POST['cikisTarihi'])){
							$response['error'] = false;
							$response['message'] = 'Rezervasyon eklendi';
						}else{
							$response['error'] = true;
							$response['message'] = 'Rezervasyon eklenemedi';
						}
					}				
				}else{
					$response['error'] = true; 
					$response['message'] = 'Parametreler doğru şekilde girilmedi';
				}
			break; 
			
			case 'createMusteri':
				if( isset($_POST['adi']) && isset ($_POST['soyadi'])&& isset ($_POST['sifre'])&& isset($_POST['telefonNumarasi']) && isset ($_POST['epostaAdresi']) && isset ($_POST['dogumTarihi'])){
					$db = new DbOperation(); 
					if($db->createMusteri($_POST['adi'], $_POST['soyadi'],$_POST['sifre'],$_POST['telefonNumarasi'],$_POST['epostaAdresi'],$_POST['dogumTarihi'])){
						$response['error'] = false;
						$response['message'] = 'Musteri eklendi';
					}else{
						$response['error'] = true;
						$response['message'] = 'Musteri eklenemedi';
					}
				}else{
					$response['error'] = true; 
					$response['message'] = 'Parametreler doğru şekilde girilmedi';
				}
			break;

			case 'getOtel':
				$db = new DbOperation();
				if(isset($_GET['id']) || !empty($_GET['id'])) {
					$id = $_GET['id'];
					$otel = $db->getByIdOtel($id);
				} else {
					$otel = $db->getOtel();
				}
				if(count($otel)<=0){
					$response['error'] = true; 
					$response['message'] = 'Veritabanı bulunamadı';
				}else{
					$response['error'] = false; 
					$response['otel'] = $otel;
				}
			break; 

			case 'getHizmet':
				$db = new DbOperation();
				
				if(isset($_GET['id']) || !empty($_GET['id'])){
					$id = $_GET['id'];
					$hizmet = $db->getByIdHizmet($id);
				}else{
					$response['error'] = true; 
					$response['message'] = 'Veritabanı bulunamadı';
				}
				if(count($hizmet)<=0){
					$response['error'] = true; 
					$response['message'] = 'Veritabanı bulunamadı';
				}else{
					$response['error'] = false; 
					$response['hizmet'] = $hizmet;
				}
			break;

			case 'getMusteri':
				$db = new DbOperation();	
				if(isset($_GET['id']) || !empty($_GET['id'])){
					$id = $_GET['id'];
					$musteri = $db->getByIdMusteri($id);
				}else{
					$musteri= $db->getMusteri();
				}
				if(count($musteri)<=0){
					$response['error'] = true; 
					$response['message'] = 'Veritabanı bulunamadı';
				}else{
					$response['error'] = false; 
					$response['musteri'] = $musteri;
				}
			break;

			case 'getRezervasyon':
				$db = new DbOperation();
				
				if(isset($_GET['id']) || !empty($_GET['id'])){
					$id = $_GET['id'];
					$rezervasyon = $db->getByIdRezervasyon($id);
				}else{
					$rezervasyon = $db->getRezervasyon();
				}
				if(count($rezervasyon)<=0){
					$response['error'] = true; 
					$response['message'] = 'Veritabanı bulunamadı';
				}else{
					$response['error'] = false; 
					$response['rezervasyon'] = $rezervasyon;
				}
			break; 

			case 'getHizmetBilgisi':
				$db = new DbOperation();
				if(isset($_GET['id']) || !empty($_GET['id'])){
					$id = $_GET['id'];
					$hizmetbilgisi = $db->getByIdHizmetBilgisi($id);
				}else{
					$response['error'] = true; 
					$response['message'] = 'Veritabanı bulunamadı';
				}
				if(count($hizmetbilgisi)<=0){
					$response['error'] = true; 
					$response['message'] = 'Veritabanı bulunamadı';
				}else{
					$response['error'] = false; 
					$response['hizmetbilgisi'] = $hizmetbilgisi;
				}
			break;

			case 'getTurProgrami':
				$db = new DbOperation();
				if(isset($_GET['id']) || !empty($_GET['id'])){
					$id = $_GET['id'];
					$turprogrami = $db->getByIdTurProgrami($id);
				}else{
					$response['error'] = true; 
					$response['message'] = 'Veritabanı bulunamadı';
				}
				if(count($turprogrami)<=0){
					$response['error'] = true; 
					$response['message'] = 'Veritabanı bulunamadı';
				}else{
					$response['error'] = false; 
					$response['turprogrami'] = $turprogrami;
				}
			break;
			case 'getTekneTur':
				$db = new DbOperation();
				if(isset($_GET['id']) || !empty($_GET['id'])){
					$id = $_GET['id'];
					$teknetur = $db->getByIdTekneTur($id);
				}else{
					$teknetur = $db->getTekneTur();
				}
				if(count($teknetur)<=0){
					$response['error'] = true; 
					$response['message'] = 'Veritabanı bulunamadı';
				}else{
					$response['error'] = false; 
					$response['teknetur'] = $teknetur;
				}
			break; 
			
			case 'getYurtdisiTur':
				$db = new DbOperation();
				if(isset($_GET['id']) || !empty($_GET['id'])){
					$id = $_GET['id'];
					$yurtdisitur = $db->getByIdYurtdisiTur($id);
				}else{

					$yurtdisitur = $db->getYurtdisiTur();
				}
				if(count($yurtdisitur)<=0){
					$response['error'] = true; 
					$response['message'] = 'Veritabanı bulunamadı';
				}else{
					$response['error'] = false; 
					$response['yurtdisitur'] = $yurtdisitur;
				}
			break; 
			
			default:
				$response['error'] = true;
				$response['message'] = 'Gerçekleştirilecek işlem yok';
			
		}
		
	}else{
		$response['error'] = false; 
		$response['message'] = 'Geçersiz istek';
	}
	
	echo json_encode($response);