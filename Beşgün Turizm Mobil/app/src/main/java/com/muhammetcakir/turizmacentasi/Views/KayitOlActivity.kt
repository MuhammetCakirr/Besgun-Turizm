package com.muhammetcakir.turizmacentasi.Views

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.muhammetcakir.turizmacentasi.Database.DbServices
import com.muhammetcakir.turizmacentasi.Database.musteriListesi
import com.muhammetcakir.turizmacentasi.Database.rezervasyonListesi
import com.muhammetcakir.turizmacentasi.MainActivity
import com.muhammetcakir.turizmacentasi.Models.Musteri
import com.muhammetcakir.turizmacentasi.Models.TurProgrami
import com.muhammetcakir.turizmacentasi.R
import com.muhammetcakir.turizmacentasi.databinding.ActivityKayitOlBinding
import com.muhammetcakir.turizmacentasi.kullanankisi
import com.muhammetcakir.turizmacentasi.suankullanicivarmi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

var suankikullanicieposta="1"
class KayitOlActivity : AppCompatActivity() {
    private lateinit var binding:ActivityKayitOlBinding
    private val url = "http://192.168.1.122/turizmacentasi/v1/?op="
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityKayitOlBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        runBlocking {
            musteriListesi.clear()
            val db= DbServices()
            val deferred = async {
                db.getMusteri()
            }
            val yenimusteriListesi = deferred.await()
            musteriListesi.addAll(yenimusteriListesi)
            println("AAAAAAAMUSTERİ"+ musteriListesi.count().toString())
        }
        binding.btngirisyap.setOnClickListener {
            startActivity(Intent(this,GirisYapActivity::class.java))
        }
        val mycalender = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            mycalender.set(Calendar.YEAR, year)
            mycalender.set(Calendar.MONTH, month)
            mycalender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLable(mycalender)
        }
        binding.dogumtarihibtn.setOnClickListener {
            DatePickerDialog(
                this, datePicker, mycalender.get(Calendar.YEAR), mycalender.get(Calendar.MONTH),
                mycalender.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        binding.btnkayitol.setOnClickListener {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("tr"))
            val formattedDateTime = sdf.format(mycalender.time)
            var epostaVarMi = false

            for (musteri in musteriListesi) {
                if (musteri.epostaAdresi.toString() == binding.kayitoleposta.text.toString()) {
                    epostaVarMi = true
                    break
                }
            }
            if (epostaVarMi==true)
            {
                binding.kayitoldurum.visibility = View.VISIBLE
            }
            else
            {
                postkayitol(
                    binding.kayitolisim.text.toString(),
                    binding.kayitolsoyisim.text.toString(),
                    binding.kayitoleposta.text.toString(),
                    formattedDateTime.toString(),
                    binding.kayitoltelno.text.toString(),
                    binding.kayitolsifre.text.toString()
                )
                suankikullanicieposta= binding.kayitoleposta.text.toString()
                suankullanicivarmi=true
                startActivity(Intent(this,MainActivity::class.java))

            }
        }
    }
    private fun postkayitol(
        adi: String,
        soyadi: String,
        eposta: String,
        dogumt: String,
        telno: String,
        sifre: String
    ) {
        GlobalScope.launch {
            val client = OkHttpClient()
            val formBody = FormBody.Builder()
                .add("adi",adi )
                .add("soyadi",soyadi)
                .add("epostaAdresi", eposta)
                .add("dogumTarihi",dogumt)
                .add("telefonNumarasi",telno)
                .add("sifre", sifre)
                .build()
            val request = Request.Builder()
                .url(url + "createMusteri")
                .post(formBody)
                .build()
            val response = client.newCall(request).execute()
            Log.d("response", response.toString())
        }
    }
    private fun updateLable(mycalender: Calendar) {
        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale("tr"))
        binding.kayitoldogumtarihi.setText(sdf.format(mycalender.time))

    }
}


