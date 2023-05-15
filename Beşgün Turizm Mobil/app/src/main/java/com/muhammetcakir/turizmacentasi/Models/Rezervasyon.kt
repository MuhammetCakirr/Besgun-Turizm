package com.muhammetcakir.turizmacentasi.Models

data class Rezervasyon (
    val idRezervasyon: String,
    val tarihi:String,
    val yetiskinSayisi:Int,
    val cocukSayisi:Int,
    val Musteri_idMusteri:String,
    val Hizmet_idHizmet:String,
    val girisTarihi:String,
    val cikisTarihi:String
     )
