package com.muhammetcakir.turizmacentasi.Views

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import com.muhammetcakir.turizmacentasi.*
import com.muhammetcakir.turizmacentasi.Database.*
import com.muhammetcakir.turizmacentasi.Models.Otel
import com.muhammetcakir.turizmacentasi.Models.Rezervasyon
import com.muhammetcakir.turizmacentasi.databinding.ActivityRezGuncelleBinding

import com.squareup.picasso.Picasso
import id.ionbit.ionalert.IonAlert
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

class RezGuncelleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRezGuncelleBinding
    private val url = "http://192.168.1.122/turizmacentasi/v1/?op="
    var yetiskinsayi2 = 1
    var cocuksayi2 = 0
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRezGuncelleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.kisisayisisec.setOnClickListener {
            val dialogbinding = layoutInflater.inflate(R.layout.kisisecimdialog, null)
            val mydialog = Dialog(this)
            mydialog.setContentView(dialogbinding)
            mydialog.setCancelable(true)
            mydialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            mydialog.show()
            val yetiskinarti = dialogbinding.findViewById<ImageView>(R.id.yetiskinekle)
            val yetiskineksi = dialogbinding.findViewById<ImageView>(R.id.yetiskincikar)
            val yetiskinsayi = dialogbinding.findViewById<TextView>(R.id.yetiskinsayi)
            val cocukarti = dialogbinding.findViewById<ImageView>(R.id.cocukekle)
            val cocukeksi = dialogbinding.findViewById<ImageView>(R.id.cocukcikar)
            val cocuksayi = dialogbinding.findViewById<TextView>(R.id.cocuksayi)
            val onayla = dialogbinding.findViewById<CardView>(R.id.onaylaa)

            yetiskinarti.setOnClickListener {
                yetiskinsayi2 = (yetiskinsayi.text.toString()).toInt()
                do {
                    if (yetiskinsayi2 < 5) {
                        yetiskinsayi2 = yetiskinsayi2 + 1
                        if (yetiskinsayi2 == 4) {
                            IonAlert(this, IonAlert.WARNING_TYPE)
                                .setTitleText("Dikkat!")
                                .setContentText("Bir Rezervasyonda en fazla 4 Yetişkin Seçebilirsin.")
                                .setCancelClickListener(object : IonAlert.ClickListener {
                                    override fun onClick(sDialog: IonAlert) {
                                        sDialog.dismissWithAnimation()
                                    }
                                })
                                .show()
                        }
                    }
                } while (yetiskinsayi2 == 5)
                yetiskinsayi.setText(yetiskinsayi2.toString())
            }
            yetiskineksi.setOnClickListener {

                yetiskinsayi2 = (yetiskinsayi.text.toString()).toInt()
                yetiskinsayi2 = yetiskinsayi2 - 1
                yetiskinsayi.text = (yetiskinsayi2.toString())
            }
            cocukeksi.setOnClickListener {

                cocuksayi2 = (cocuksayi.text.toString()).toInt()
                cocuksayi2 = cocuksayi2 - 1
                cocuksayi.text = (cocuksayi2.toString())
            }
            cocukarti.setOnClickListener {

                cocuksayi2 = (cocuksayi.text.toString()).toInt()

                do {
                    if (cocuksayi2 < 5) {
                        cocuksayi2 = cocuksayi2 + 1
                        if (cocuksayi2 == 4) {
                            IonAlert(this, IonAlert.WARNING_TYPE)
                                .setTitleText("Dikkat!")
                                .setContentText("Bir Rezervasyonda en fazla 4 Çocuk Seçebilirsin.")
                                .setCancelClickListener(object : IonAlert.ClickListener {
                                    override fun onClick(sDialog: IonAlert) {
                                        sDialog.dismissWithAnimation()
                                    }
                                })
                                .show()
                        }
                    }
                } while (cocuksayi2 == 5)

                cocuksayi.setText(cocuksayi2.toString())

            }
            onayla.setOnClickListener {
                mydialog.dismiss()
                binding.kisisayisi.text =
                    (yetiskinsayi.text.toString() + " Yetişkin," + cocuksayi.text.toString() + " Çocuk")
                binding.kisisayisi.visibility = View.VISIBLE

            }

        }
        val mycalender = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            mycalender.set(Calendar.YEAR, year)
            mycalender.set(Calendar.MONTH, month)
            mycalender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLable(mycalender)
        }
        binding.cardgiristarihi.setOnClickListener {
            DatePickerDialog(
                this, datePicker, mycalender.get(Calendar.YEAR), mycalender.get(Calendar.MONTH),
                mycalender.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        val mycalender2 = Calendar.getInstance()
        val datePicker2 = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            mycalender2.set(Calendar.YEAR, year)
            mycalender2.set(Calendar.MONTH, month)
            mycalender2.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLable2(mycalender2)

        }
        binding.cardcikistarihi.setOnClickListener {
            DatePickerDialog(
                this, datePicker2, mycalender2.get(Calendar.YEAR), mycalender2.get(Calendar.MONTH),
                mycalender2.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        val rezId = intent.getStringExtra(REZ_ID_EXRTRA)
        val rezervasyon = rezID(rezId.toString())

        if(rezervasyon!=null)
        {
            runBlocking {
                hizmetListesi.clear()
                val db= DbServices()
                val deferred = async {
                    db.getHizmet(rezervasyon.Hizmet_idHizmet.toString())
                }
                val yenihizmetListesi = deferred.await()
                hizmetListesi.addAll(yenihizmetListesi)
            }
            if (hizmetListesi[0].hizmetTabloAdi=="yurtdisitur")
            {
                for (ydt in yurtdisiviewListesi)
                {
                    if (hizmetListesi[0].hizmetTabloId.toString()== ydt.idYurtdisiTur.toString())
                    {
                                binding.turadi.text=ydt.adi.toString()
                                binding.rezkisisayisi.text="${rezervasyon.yetiskinSayisi.toString()} Yetişkin,${rezervasyon.cocukSayisi.toString()} Çocuk"

                                binding.guzergah.text="${ydt.baslangicKonumu}-->${ydt.guzergah}"
                                if (ydt.vizeDurumu=="Evet")
                                    binding.vize.text="Vizeli"
                                else
                                    binding.vize.text="Vizesiz"

                                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale("tr"))
                                val giristarihi = LocalDate.parse(ydt.baslangicTarihi.toString(), formatter)

                                val gelecekTarih = giristarihi.plusDays(5)
                                val cikistarihiFormatter = DateTimeFormatter.ofPattern("dd LLLL yyyy", Locale("tr"))
                                val cikistarihi = gelecekTarih.format(cikistarihiFormatter)

                                val reztarihi = LocalDate.parse(rezervasyon.tarihi.toString(), formatter)
                                val dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("tr"))
                                val tarihString = reztarihi.format(dateFormatter)

                                val giristarihi2 = LocalDate.parse(ydt.baslangicTarihi.toString(), formatter)
                                val giristring = giristarihi2.format(dateFormatter)
                                binding.reztarihi.text=tarihString.toString()
                                binding.giristarih.text=giristring.toString()
                                binding.cikistarih.text=cikistarihi.toString()
                                Picasso.get().load(ydt.resimUrl).into(binding.turfoto)
                                val yetiskinSayisi = rezervasyon.yetiskinSayisi.toInt()
                                val cocukSayisi = rezervasyon.cocukSayisi.toInt()
                                val fiyat = ydt.fiyat
                                val yetiskinFiyat = yetiskinSayisi * fiyat
                                var cocukFiyat = (cocukSayisi / 2) * fiyat

                                if (cocukSayisi==0)
                                {
                                    val toplamFiyat = yetiskinFiyat
                                    binding.rezfiyat.text=toplamFiyat.toString()+" TL"
                                }
                                else if(cocukSayisi==1)
                                {
                                    cocukFiyat = (0.5 * fiyat).toInt()
                                    val toplamFiyat = yetiskinFiyat + cocukFiyat
                                    binding.rezfiyat.text=toplamFiyat.toString()+" TL"
                                }
                                else if(cocukSayisi==2){
                                    cocukFiyat=fiyat
                                    val toplamFiyat = yetiskinFiyat + cocukFiyat
                                    binding.rezfiyat.text=toplamFiyat.toString()+" TL"
                                }
                                else if(cocukSayisi==3){
                                    cocukFiyat = (1.5 * fiyat).toInt()
                                    val toplamFiyat = yetiskinFiyat + cocukFiyat
                                    binding.rezfiyat.text=toplamFiyat.toString()+" TL"
                                }
                                else if(cocukSayisi==4){
                                    cocukFiyat = (2 * fiyat).toInt()
                                    val toplamFiyat = yetiskinFiyat + cocukFiyat
                                    binding.rezfiyat.text=toplamFiyat.toString()+" TL"
                                }
                    }
                }
                binding.duzenlebtn.setOnClickListener {

                    binding.layoutnormal.visibility=View.GONE
                    binding.layoutg.visibility=View.VISIBLE
                }
                binding.duzenlebtn2.setOnClickListener{
                    IonAlert(this, IonAlert.WARNING_TYPE)
                        .setTitleText("Rezervasyonu Güncellemek istediğine emin misin?")
                        .setConfirmText("Güncelle")
                        .setCancelText("İptal")
                        .setCancelClickListener(object : IonAlert.ClickListener {
                            override fun onClick(sDialog: IonAlert) {
                                sDialog.dismissWithAnimation()
                            }
                        })
                        .setConfirmClickListener(object : IonAlert.ClickListener {
                            override fun onClick(sDialog: IonAlert) {
                                updaterez(
                                    rezervasyon.idRezervasyon,
                                    yetiskinsayi2.toString(),
                                    cocuksayi2.toString(),
                                    "2023-07-10 09:00:00",
                                    "2023-07-15 09:00:00"
                                )
                                sDialog.dismissWithAnimation()
                                startActivity(Intent(applicationContext,MainActivity::class.java))
                            }
                        })
                        .show()
                }
                binding.iptaletbtn.setOnClickListener{
                    IonAlert(this, IonAlert.ERROR_TYPE)
                        .setTitleText("Rezervasyonu İptal etmek istediğine emin misin?")
                        .setConfirmText("İptal Et")
                        .setCancelText("Kapat")
                        .setCancelClickListener(object : IonAlert.ClickListener {
                            override fun onClick(sDialog: IonAlert) {
                                sDialog.dismissWithAnimation()
                            }
                        })
                        .setConfirmClickListener(object : IonAlert.ClickListener {
                            override fun onClick(sDialog: IonAlert) {
                                deleterez(rezervasyon.idRezervasyon)
                                sDialog.dismissWithAnimation()
                                startActivity(Intent(applicationContext,MainActivity::class.java))
                            }
                        })
                        .show()
                }
                binding.iptaletbtn2.setOnClickListener {
                    binding.layoutnormal.visibility=View.VISIBLE
                    binding.layoutg.visibility=View.GONE
                }
                binding.cardgiristarihi.visibility=View.GONE
                binding.cardcikistarihi.visibility=View.GONE
            }
            else if(hizmetListesi[0].hizmetTabloAdi=="teknetur")
            {
                for (teknetur in tekneturviewListesi)
                {
                    if (hizmetListesi[0].hizmetTabloId.toString()== teknetur.idTekneTur.toString())
                    {
                                binding.turadi.text=teknetur.adi.toString()
                                binding.reztarihi.text=rezervasyon.tarihi.toString()
                                binding.rezkisisayisi.text="${rezervasyon.yetiskinSayisi.toString()} Yetişkin,${rezervasyon.cocukSayisi.toString()} Çocuk"

                                binding.guzergah.text="${teknetur.baslangicKonumu}-->${teknetur.guzergah}"
                                if (teknetur.vizeDurumu=="Evet")
                                    binding.vize.text="Vizeli"
                                else
                                    binding.vize.text="Vizesiz"

                                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale("tr"))
                                val giristarihi = LocalDate.parse(teknetur.baslangicTarihi.toString(), formatter)

                                val gelecekTarih = giristarihi.plusDays(5)
                                val cikistarihiFormatter = DateTimeFormatter.ofPattern("dd LLLL yyyy", Locale("tr"))
                                val cikistarihi = gelecekTarih.format(cikistarihiFormatter)

                                val reztarihi = LocalDate.parse(rezervasyon.tarihi.toString(), formatter)
                                val dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("tr"))
                                val tarihString = reztarihi.format(dateFormatter)

                                val giristarihi2 = LocalDate.parse(teknetur.baslangicTarihi.toString(), formatter)
                                val giristring = giristarihi2.format(dateFormatter)
                                binding.reztarihi.text=tarihString.toString()
                                binding.giristarih.text=giristring.toString()
                                binding.cikistarih.text=cikistarihi.toString()

                                Picasso.get().load(teknetur.resimUrl).into(binding.turfoto)
                                val yetiskinSayisi = rezervasyon.yetiskinSayisi.toInt()
                                val cocukSayisi = rezervasyon.cocukSayisi.toInt()
                                val fiyat = teknetur.fiyat
                                val yetiskinFiyat = yetiskinSayisi * fiyat
                                var cocukFiyat = (cocukSayisi / 2) * fiyat

                                if (cocukSayisi==0)
                                {
                                    val toplamFiyat = yetiskinFiyat
                                    binding.rezfiyat.text=toplamFiyat.toString()+" TL"
                                }
                                else if(cocukSayisi==1)
                                {
                                    cocukFiyat = (0.5 * fiyat).toInt()
                                    val toplamFiyat = yetiskinFiyat + cocukFiyat
                                    binding.rezfiyat.text=toplamFiyat.toString()+" TL"
                                }
                                else if(cocukSayisi==2){
                                    cocukFiyat=fiyat
                                    val toplamFiyat = yetiskinFiyat + cocukFiyat
                                    binding.rezfiyat.text=toplamFiyat.toString()+" TL"
                                }
                                else if(cocukSayisi==3){
                                    cocukFiyat = (1.5 * fiyat).toInt()
                                    val toplamFiyat = yetiskinFiyat + cocukFiyat
                                    binding.rezfiyat.text=toplamFiyat.toString()+" TL"
                                }
                                else if(cocukSayisi==4){
                                    cocukFiyat = (2 * fiyat).toInt()
                                    val toplamFiyat = yetiskinFiyat + cocukFiyat
                                    binding.rezfiyat.text=toplamFiyat.toString()+" TL"
                                }
                    }
                }
                binding.duzenlebtn.setOnClickListener {

                    binding.layoutnormal.visibility=View.GONE
                    binding.layoutg.visibility=View.VISIBLE
                }
                binding.duzenlebtn2.setOnClickListener {

                    IonAlert(this, IonAlert.WARNING_TYPE)
                        .setTitleText("Rezervasyonu Güncellemek istediğine emin misin?")
                        .setConfirmText("Güncelle")
                        .setCancelText("İptal")
                        .setCancelClickListener(object : IonAlert.ClickListener {
                            override fun onClick(sDialog: IonAlert) {
                                sDialog.dismissWithAnimation()
                            }
                        })
                        .setConfirmClickListener(object : IonAlert.ClickListener {
                            override fun onClick(sDialog: IonAlert) {
                                updaterez(
                                    rezervasyon.idRezervasyon,
                                    yetiskinsayi2.toString(),
                                    cocuksayi2.toString(),
                                    "2023-07-10 09:00:00",
                                    "2023-07-15 09:00:00"
                                )
                                sDialog.dismissWithAnimation()
                                startActivity(Intent(applicationContext,MainActivity::class.java))
                            }
                        })
                        .show()
                }
                binding.iptaletbtn.setOnClickListener {
                    IonAlert(this, IonAlert.ERROR_TYPE)
                        .setTitleText("Rezervasyonu İptal etmek istediğine emin misin?")
                        .setConfirmText("İptal Et")
                        .setCancelText("Kapat")
                        .setCancelClickListener(object : IonAlert.ClickListener {
                            override fun onClick(sDialog: IonAlert) {
                                sDialog.dismissWithAnimation()
                            }
                        })
                        .setConfirmClickListener(object : IonAlert.ClickListener {
                            override fun onClick(sDialog: IonAlert) {
                                deleterez(rezervasyon.idRezervasyon)
                                sDialog.dismissWithAnimation()
                                startActivity(Intent(applicationContext,MainActivity::class.java))
                            }
                        })
                        .show()
                }
                binding.iptaletbtn2.setOnClickListener {
                    binding.layoutnormal.visibility=View.VISIBLE
                    binding.layoutg.visibility=View.GONE
                }
                binding.cardgiristarihi.visibility=View.GONE
                binding.cardcikistarihi.visibility=View.GONE
            }
            else if(hizmetListesi[0].hizmetTabloAdi=="otel")
            {
                for (otel in otelviewListesi)
                {
                    if (hizmetListesi[0].hizmetTabloId.toString()== otel.idOtel.toString())
                    {

                                binding.turadi.text=otel.adi.toString() +" Otel"
                                binding.reztarihi.text=rezervasyon.tarihi.toString()

                                binding.rezkisisayisi.text="${rezervasyon.yetiskinSayisi.toString()} Yetişkin,${rezervasyon.cocukSayisi.toString()} Çocuk"

                                binding.guzergahtext.text="Adres : "
                                binding.guzergah.text=otel.adres.toString()

                                binding.vizetext.text="Otel Teması : "
                                binding.vize.text=otel.tema.toString()

                                binding.turgiristarihtext.text="Otel Giriş Tarihi : "

                                binding.turcikistarihtext.text="Otel Çıkış Tarihi : "

                                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

                                val dateTime = LocalDateTime.parse(rezervasyon.tarihi.toString(), formatter)
                                val dateTime2 = LocalDateTime.parse(rezervasyon.girisTarihi.toString(), formatter)
                                val dateTime3 = LocalDateTime.parse(rezervasyon.cikisTarihi, formatter)

                                val reztarihi = dateTime.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
                                val otelgiristarihi = dateTime2.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
                                val otelcikistarihi = dateTime3.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))

                                binding.reztarihi.text=reztarihi.toString()
                                binding.giristarih.text=otelgiristarihi.toString()
                                binding.cikistarih.text=otelcikistarihi.toString()

                                Picasso.get().load(otel.resimUrl).into(binding.turfoto)

                                val formatter2 = DateTimeFormatter.ofPattern("dd MMMM yyyy")


                                val girisTarihi = LocalDate.parse(otelgiristarihi, formatter2)
                                val cikisTarihi = LocalDate.parse(otelcikistarihi, formatter2)

                                val gunSayisi = ChronoUnit.DAYS.between(girisTarihi, cikisTarihi)
                                val yetiskinSayisi = rezervasyon.yetiskinSayisi.toInt()
                                val cocukSayisi = rezervasyon.cocukSayisi.toInt()
                                val fiyat = otel.fiyat
                                val yetiskinFiyat = yetiskinSayisi * fiyat
                                var cocukFiyat = (cocukSayisi / 2) * fiyat

                                if (cocukSayisi==0)
                                {
                                    val toplamFiyat = yetiskinFiyat *gunSayisi
                                    binding.rezfiyat.text=toplamFiyat.toString()+" TL"
                                }
                                else if(cocukSayisi==1)
                                {
                                    cocukFiyat = (0.5 * fiyat).toInt()
                                    val toplamFiyat = (yetiskinFiyat + cocukFiyat)*gunSayisi
                                    binding.rezfiyat.text=toplamFiyat.toString()+" TL"
                                }
                                else if(cocukSayisi==2){
                                    cocukFiyat=fiyat
                                    val toplamFiyat = (yetiskinFiyat + cocukFiyat)*gunSayisi
                                    binding.rezfiyat.text=toplamFiyat.toString()+" TL"
                                }
                                else if(cocukSayisi==3){
                                    cocukFiyat = (1.5 * fiyat).toInt()
                                    val toplamFiyat = (yetiskinFiyat + cocukFiyat)*gunSayisi
                                    binding.rezfiyat.text=toplamFiyat.toString()+" TL"
                                }
                                else if(cocukSayisi==4){
                                    cocukFiyat = (2 * fiyat).toInt()
                                    val toplamFiyat = (yetiskinFiyat + cocukFiyat)*gunSayisi
                                    binding.rezfiyat.text=toplamFiyat.toString()+" TL"
                                }

                    }
                }
                binding.duzenlebtn.setOnClickListener {

                    binding.layoutnormal.visibility=View.GONE
                    binding.layoutg.visibility=View.VISIBLE

                }
                binding.duzenlebtn2.setOnClickListener {
                    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("tr"))
                    val giristarihi = sdf.format(mycalender.time)
                    val cikistarihi = sdf.format(mycalender2.time)

                    IonAlert(this, IonAlert.WARNING_TYPE)
                        .setTitleText("Rezervasyonu Güncellemek istediğine emin misin?")
                        .setConfirmText("Güncelle")
                        .setCancelText("İptal")
                        .setCancelClickListener(object : IonAlert.ClickListener {
                            override fun onClick(sDialog: IonAlert) {
                                sDialog.dismissWithAnimation()
                            }
                        })
                        .setConfirmClickListener(object : IonAlert.ClickListener {
                            override fun onClick(sDialog: IonAlert) {
                                updaterez(
                                    rezervasyon.idRezervasyon,
                                    yetiskinsayi2.toString(),
                                    cocuksayi2.toString(),
                                    giristarihi.toString(),
                                    cikistarihi.toString()
                                )
                                sDialog.dismissWithAnimation()
                                startActivity(Intent(applicationContext,MainActivity::class.java))
                            }
                        })
                        .show()

                }
                binding.iptaletbtn.setOnClickListener {
                    IonAlert(this, IonAlert.ERROR_TYPE)
                        .setTitleText("Rezervasyonu İptal etmek istediğine emin misin?")
                        .setConfirmText("İptal Et")
                        .setCancelText("Kapat")
                        .setCancelClickListener(object : IonAlert.ClickListener {
                            override fun onClick(sDialog: IonAlert) {
                                sDialog.dismissWithAnimation()
                            }
                        })
                        .setConfirmClickListener(object : IonAlert.ClickListener {
                            override fun onClick(sDialog: IonAlert) {
                                deleterez(rezervasyon.idRezervasyon)
                                sDialog.dismissWithAnimation()
                                startActivity(Intent(applicationContext,MainActivity::class.java))
                            }
                        })
                        .show()
                }
                binding.iptaletbtn2.setOnClickListener {
                    binding.layoutnormal.visibility=View.VISIBLE
                    binding.layoutg.visibility=View.GONE
                }
                binding.cardgiristarihi.visibility=View.VISIBLE
                binding.cardcikistarihi.visibility=View.VISIBLE
            }

        }
    }
    private fun rezID(rezid: String): Rezervasyon? {
        for (REZ in rezervasyonListesi) {
            if (REZ.idRezervasyon.toString() == rezid.toString())
                return REZ
        }
        return null
    }
    private fun updateLable(mycalender: Calendar) {
        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale("tr"))
        binding.giristarihi.text = sdf.format(mycalender.time)
        binding.giristarihi.visibility = View.VISIBLE
    }
    private fun updateLable2(mycalender2: Calendar) {
        val sdf2 = SimpleDateFormat("dd MMMM yyyy", Locale("tr"))
        binding.cikistarihi.text = sdf2.format(mycalender2.time)
        binding.cikistarihi.visibility = View.VISIBLE
    }
    private fun deleterez(
        id: String
    ) {
        GlobalScope.launch {
            val client = OkHttpClient()
            val formBody = FormBody.Builder()
                .add("id", id.toString())
                .build()
            val request = Request.Builder()
                .url(url + "deleteRezervasyon")
                .post(formBody)
                .build()
            val response = client.newCall(request).execute()
            Log.d("response", response.toString())
        }
    }
    private fun updaterez(
        id: String,
        yetiskinsayi: String,
        cocuksayi: String,
        giristarihi: String,
        cikistarihi: String,
    )
    {
        GlobalScope.launch {
            val client = OkHttpClient()
            val formBody = FormBody.Builder()
                .add("id",id )
                .add("yetiskinSayisi",yetiskinsayi)
                .add("cocukSayisi", cocuksayi)
                .add("girisTarihi",giristarihi)
                .add("cikisTarihi",cikistarihi)
                .build()
            val request = Request.Builder()
                .url(url + "updateRezervasyon")
                .post(formBody)
                .build()
            val response = client.newCall(request).execute()
            Log.d("response", response.toString())
        }
    }
}