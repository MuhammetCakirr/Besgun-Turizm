package com.muhammetcakir.turizmacentasi.Views

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import androidx.annotation.RequiresApi

import com.muhammetcakir.turizmacentasi.databinding.ActivityMyAccountBinding
import com.muhammetcakir.turizmacentasi.kullanankisi

import kotlinx.coroutines.*
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class MyAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyAccountBinding
    private val url = "http://192.168.1.122/turizmacentasi/v1/?op="

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()



            binding.kullaniciisim.setText(kullanankisi[0].adi.toString())
            binding.kullanicisoyisim.setText(kullanankisi[0].soyadi.toString())
            binding.kullanicieposta.setText(kullanankisi[0].epostaAdresi.toString())
            binding.kullanicisifre.setText(kullanankisi[0].sifre.toString())
        val formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale("tr"))
        val dtarihi = LocalDate.parse(kullanankisi[0].dogumTarihi.toString(), formatter)
        val cikistarihiFormatter =
            DateTimeFormatter.ofPattern("dd LLLL yyyy", Locale("tr"))
        val cikistarihi = dtarihi.format(cikistarihiFormatter)

            binding.kullanicidogumtarihi.setText(cikistarihi.toString())
            binding.kullanicitelno.setText(kullanankisi[0].telefonNumarasi.toString())

            val mycalender = Calendar.getInstance()
            val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                mycalender.set(Calendar.YEAR, year)
                mycalender.set(Calendar.MONTH, month)
                mycalender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateLable(mycalender)
            }
            binding.dogumtbtn.setOnClickListener {
                DatePickerDialog(
                    this, datePicker, mycalender.get(Calendar.YEAR), mycalender.get(Calendar.MONTH),
                    mycalender.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
            binding.guncellebtn.setOnClickListener {
                val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("tr"))
                val dogumt = sdf.format(mycalender.time)

                UpdateMy(
                    kullanankisi[0].idMusteri,
                    binding.kullaniciisim.text.toString(),
                    binding.kullanicisoyisim.text.toString(),
                    binding.kullanicisifre.text.toString(),
                    binding.kullanicitelno.text.toString(),
                    binding.kullanicieposta.text.toString(),
                    dogumt.toString()

                )
                postt()
                println("XXXXXXXX"+dogumt.toString())
            }

    }

    private fun UpdateMy(
        id: String,
        adi: String,
        soyadi: String,
        sifre: String,
        telno: String,
        eposta: String,
        dogumtarihi: String
    ) {
        GlobalScope.launch {
            val client = OkHttpClient()
            val formBody = FormBody.Builder()
                .add("id", id.toString())
                .add("adi", adi.toString())
                .add("soyadi", soyadi.toString())
                .add("sifre", sifre.toString())
                .add("telefonNumarasi", telno.toString())
                .add("epostaAdresi", eposta.toString())
                .add("dogumTarihi", dogumtarihi.toString())
                .build()
            val request = Request.Builder()
                .url(url+"updateMusteri")
                .post(formBody)
                .build()
            val response = client.newCall(request).execute()

            Log.d("response", response.toString())
        }

    }

    private  fun postt() {
        GlobalScope.launch {
        try {
            val urlHizmet = URL(url + "updateMusteri")
            val conn = withContext(Dispatchers.IO) {
                urlHizmet.openConnection() as HttpURLConnection
            }
            conn.readTimeout = 10000
            conn.connectTimeout = 15000
            conn.requestMethod = "POST"
            conn.doInput = true
            conn.doOutput = true

            val postData = StringBuilder().apply {
                append(URLEncoder.encode("id", "UTF-8"))
                    .append("=")
                    .append(URLEncoder.encode("4", "UTF-8"))
                append(URLEncoder.encode("adi", "UTF-8"))
                    .append("=")
                    .append(URLEncoder.encode("Mustafa", "UTF-8"))
                append("&")
                append(URLEncoder.encode("soyadi", "UTF-8"))
                    .append("=")
                    .append(URLEncoder.encode("Çakır", "UTF-8"))
                append("&")
                append(URLEncoder.encode("sifre", "UTF-8"))
                    .append("=")
                    .append(URLEncoder.encode("musti123456", "UTF-8"))
                append("&")
                append(URLEncoder.encode("telefonNumarasi", "UTF-8"))
                    .append("=")
                    .append(URLEncoder.encode("123456789", "UTF-8"))
                append("&")
                append(URLEncoder.encode("epostaAdresi", "UTF-8"))
                    .append("=")
                    .append(URLEncoder.encode("mustafa@gmail.com", "UTF-8"))
                append(URLEncoder.encode("dogumTarihi", "UTF-8"))
                    .append("=")
                    .append(URLEncoder.encode("2023-10-10 21:17:09", "UTF-8"))
            }

            val postDataBytes = postData.toString().toByteArray(Charsets.UTF_8)
            conn.setRequestProperty("Content-Length", postDataBytes.size.toString())
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
            withContext(Dispatchers.IO) {
                conn.connect()
                conn.outputStream.use { outputStream ->
                    outputStream.write(postDataBytes)
                    outputStream.flush()
                }

                val responseCode = conn.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val inputStream = conn.inputStream
                    val bufferedReader = BufferedReader(InputStreamReader(inputStream))
                    val response = StringBuilder()
                    var line: String?
                    while (bufferedReader.readLine().also { line = it } != null) {
                        response.append(line)
                    }
                    Log.d("response", response.toString())
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        }
    }




    private fun updateLable(mycalender: Calendar) {
        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale("tr"))
        binding.kullanicidogumtarihi.text = sdf.format(mycalender.time)
    }

}