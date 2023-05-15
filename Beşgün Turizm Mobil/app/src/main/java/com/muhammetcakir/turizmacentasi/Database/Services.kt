package com.muhammetcakir.turizmacentasi.Database
import com.google.gson.Gson
import com.muhammetcakir.turizmacentasi.Models.*
import kotlinx.coroutines.*
import okhttp3.OkHttpClient


var hizmetListesi=ArrayList<Hizmet>()
var musteriListesi=ArrayList<Musteri>()
var rezervasyonListesi=ArrayList<Rezervasyon>()
var benimrezervasyonlarimlistesi=ArrayList<Rezervasyon>()

class DbServices {

    private  val url="http://192.168.1.113/turizmacentasi/v1/?op="


    suspend fun getOtelView(): List<OtelView> = withContext(Dispatchers.IO) {
        val geturl = url + "getOtelView"
        val request = okhttp3.Request.Builder()
            .url(geturl)
            .get()
            .build()
        val client = OkHttpClient()
        val response = client.newCall(request).execute()

        if (response.isSuccessful) {
            val json = response.body?.string()
            // JSON verisini işleyin
            val gson = Gson()
            val responseJson = gson.fromJson(json, ApiResponseOtelView::class.java)
            responseJson?.otel ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun getYurtdisiturView(): List<YurtDisiTurView> = withContext(Dispatchers.IO) {
        val geturl = url + "getYurtdisiTurView"
        val request = okhttp3.Request.Builder()
            .url(geturl)
            .get()
            .build()
        val client = OkHttpClient()
        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            val json = response.body?.string()
            // JSON verisini işleyin
            val gson = Gson()
            val responseJson = gson.fromJson(json, ApiResponseYurtDisiTurView::class.java)
            responseJson?.yurtdisitur ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun getTekneturView(): List<TekneTurView> = withContext(Dispatchers.IO){
        val geturl = url + "getTekneTurView"
        val request = okhttp3.Request.Builder()
            .url(geturl)
            .get()
            .build()
        val client = OkHttpClient()
        val response = client.newCall(request).execute()

        if (response.isSuccessful) {
            val json = response.body?.string()
            // JSON verisini işleyin
            val gson = Gson()
            val responseJson = gson.fromJson(json, ApiResponseTekneTurView::class.java)
            responseJson?.teknetur ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun getHizmet(id:String): List<Hizmet> = withContext(Dispatchers.IO) {
        val geturl = url+"getHizmet&id=${id}"
        val request = okhttp3.Request.Builder()
            .url(geturl)
            .get()
            .build()
        val client = OkHttpClient()
        val response = client.newCall(request).execute()

        if (response.isSuccessful) {
            val json = response.body?.string()
            // JSON verisini işleyin
            val gson = Gson()
            val responseJson = gson.fromJson(json, ApiResponseHizmet::class.java)
            responseJson?.hizmet ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun getRezervasyon(): List<Rezervasyon> = withContext(Dispatchers.IO) {
        val geturl = url+"getRezervasyon"
        val request = okhttp3.Request.Builder()
            .url(geturl)
            .get()
            .build()
        val client = OkHttpClient()
        val response = client.newCall(request).execute()

        if (response.isSuccessful) {
            val json = response.body?.string()
            // JSON verisini işleyin
            val gson = Gson()
            val responseJson = gson.fromJson(json, ApiResponseRezervasyon::class.java)
            responseJson?.rezervasyon ?: emptyList()
        } else {
            emptyList()
        }
    }
    suspend fun getMusteri(): List<Musteri> = withContext(Dispatchers.IO) {
        val geturl = url + "getMusteri"
        val request = okhttp3.Request.Builder()
            .url(geturl)
            .get()
            .build()
        val client = OkHttpClient()
        val response = client.newCall(request).execute()

        if (response.isSuccessful) {
            val json = response.body?.string()
            // JSON verisini işleyin
            val gson = Gson()
            val responseJson = gson.fromJson(json, ApiResponseMusteri::class.java)
            responseJson?.musteri ?: emptyList()
        } else {
            emptyList()
        }
    }

    /* suspend fun getOtel(): List<Otel> = withContext(Dispatchers.IO) {
       val geturl = url + "getOtel"
       val request = okhttp3.Request.Builder()
           .url(geturl)
           .get()
           .build()
       val client = OkHttpClient()
       val response = client.newCall(request).execute()

       if (response.isSuccessful) {
           val json = response.body?.string()
           // JSON verisini işleyin
           val gson = Gson()
           val responseJson = gson.fromJson(json, ApiResponseOtel::class.java)
           responseJson?.otel ?: emptyList()
       } else {
           emptyList()
       }
   }*/
    /* suspend fun getYurtDisiTur(): List<YurtdisiTur> = withContext(Dispatchers.IO) {
     val geturl = url + "getYurtdisiTur"
     val request = okhttp3.Request.Builder()
         .url(geturl)
         .get()
         .build()
     val client = OkHttpClient()
     val response = client.newCall(request).execute()

     if (response.isSuccessful) {
         val json = response.body?.string()
         // JSON verisini işleyin
         val gson = Gson()
         val responseJson = gson.fromJson(json, ApiResponseYurtD::class.java)
         responseJson?.yurtdisitur ?: emptyList()
     } else {
         emptyList()
     }
 }*/
    /* suspend fun getTekneTur(): List<TekneTur> = withContext(Dispatchers.IO) {
         val geturl = url + "getTekneTur"
         val request = okhttp3.Request.Builder()
             .url(geturl)
             .get()
             .build()
         val client = OkHttpClient()
         val response = client.newCall(request).execute()

         if (response.isSuccessful) {
             val json = response.body?.string()
             // JSON verisini işleyin
             val gson = Gson()
             val responseJson = gson.fromJson(json, ApiResponseTekneT::class.java)
             responseJson?.teknetur ?: emptyList()
         } else {
             emptyList()
         }
     }*/
    /* suspend fun getbyHizmetBilgisi(id:Int): List<HizmetBilgisi> = withContext(Dispatchers.IO) {
     val geturl = url+"getHizmetBilgisi&id=${id}"
     val request = okhttp3.Request.Builder()
         .url(geturl)
         .get()
         .build()
     val client = OkHttpClient()
     val response = client.newCall(request).execute()
     if (response.isSuccessful) {
         val json = response.body?.string()
         // JSON verisini işleyin
         val gson = Gson()
         val responseJson = gson.fromJson(json, ApiResponseHizmetBilgisi::class.java)
         responseJson?.hizmetbilgisi ?: emptyList()
     } else {
         emptyList()
     }
 }*/
    /* suspend fun getbyturprogrami(id:String): List<TurProgrami> = withContext(Dispatchers.IO) {
     val geturl = url+"getTurProgrami&id=${id}"
     val request = okhttp3.Request.Builder()
         .url(geturl)
         .get()
         .build()
     val client = OkHttpClient()
     val response = client.newCall(request).execute()
     if (response.isSuccessful) {
         val json = response.body?.string()
         // JSON verisini işleyin
         val gson = Gson()
         val responseJson = gson.fromJson(json, ApiResponseTurProgrami::class.java)
         responseJson?.turprogrami ?: emptyList()
     } else {
         emptyList()
     }
 }*/



}