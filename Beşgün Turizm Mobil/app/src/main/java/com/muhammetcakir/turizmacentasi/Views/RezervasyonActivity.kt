package com.muhammetcakir.turizmacentasi.Views

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import com.muhammetcakir.turizmacentasi.*

import com.muhammetcakir.turizmacentasi.Models.*
import com.muhammetcakir.turizmacentasi.Models.Otel
import com.muhammetcakir.turizmacentasi.databinding.ActivityRezervasyonBinding
import com.squareup.picasso.Picasso
import id.ionbit.ionalert.IonAlert
import kotlinx.coroutines.*
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request



import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class RezervasyonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRezervasyonBinding
    private val url = "http://192.168.1.122/turizmacentasi/v1/?op="
    var yetiskinsayi2 = 1
    var cocuksayi2 = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRezervasyonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        val mycalender = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            mycalender.set(Calendar.YEAR, year)
            mycalender.set(Calendar.MONTH, month)
            mycalender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLable(mycalender)
        }
        binding.giristarihisec.setOnClickListener {
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
        binding.cikistarihisec.setOnClickListener {
            DatePickerDialog(
                this, datePicker2, mycalender2.get(Calendar.YEAR), mycalender2.get(Calendar.MONTH),
                mycalender2.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        var gelendeger = intent.getStringExtra(TP_ID_EXTRA)

        if (gelendeger.toString() == "YurtDisi") {
            binding.turpuanlayout.visibility=View.GONE
            val ydtId = intent.getStringExtra(YDT_ID_EXTRA)
            val ydt = ydtFromID(ydtId.toString())
            if (ydt != null)
            {
                        binding.rezTuradi.text = ydt.adi.toString()
                        Picasso.get().load(ydt.resimUrl).into(binding.rezfoto)
                        binding.rezguzergah.text =
                            ydt.baslangicKonumu.toString() + " -->" + ydt.guzergah.toString()
                val formatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale("tr"))
                val giristarihi = LocalDate.parse(ydt.baslangicTarihi.toString(), formatter)

                val gelecekTarih = giristarihi.plusDays(5)
                val cikistarihiFormatter =
                    DateTimeFormatter.ofPattern("dd LLLL yyyy", Locale("tr"))
                val cikistarihi = gelecekTarih.format(cikistarihiFormatter)

                val dateFormatter =
                    DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("tr"))

                val giristarihi2 =
                    LocalDate.parse(ydt.baslangicTarihi.toString(), formatter)
                val giristring = giristarihi2.format(dateFormatter)

                        binding.reztarih2.text = giristring.toString()+" - "+cikistarihi.toString()
                        binding.rezfiyat.text = "Kişi Başı " + ydt.fiyat.toString() + " TL"
                        binding.rezervasyonolusturbtn.setOnClickListener {
                            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                            val currentDate = dateFormat.parse(ydt.baslangicTarihi.toString())
                            val calendar = Calendar.getInstance()
                            calendar.time = currentDate
                            calendar.add(Calendar.DAY_OF_MONTH, 5)
                            val futureDate = calendar.time
                            val futureDateString = dateFormat.format(futureDate)
                            val dateTime = LocalDateTime.now()
                            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                            val formattedDateTime = dateTime.format(formatter)
                            if(kullanankisi.isEmpty())
                            {
                                IonAlert(this, IonAlert.WARNING_TYPE)
                                    .setTitleText("Rezervasyon Yapmak için Lütfen Giriş Yapın.")
                                    .setConfirmText("Giriş Yap")
                                    .setCancelText("Kapat")
                                    .setConfirmClickListener(object : IonAlert.ClickListener {
                                        override fun onClick(sDialog: IonAlert) {
                                            startActivity(Intent(applicationContext, GirisYapActivity::class.java))
                                            sDialog.dismissWithAnimation()
                                        }
                                    })
                                    .setCancelClickListener(object : IonAlert.ClickListener {
                                        override fun onClick(sDialog: IonAlert) {

                                            sDialog.dismissWithAnimation()
                                        }
                                    })
                                    .show()
                            }
                            else
                            {
                                postrez(
                                    formattedDateTime.toString(),
                                    yetiskinsayi2.toString(),
                                    cocuksayi2.toString(),
                                    ydt.idYurtdisiTur.toString(),
                                    "yurtdisitur",
                                    ydt.baslangicTarihi.toString(),
                                    futureDateString.toString()
                                )
                                IonAlert(this, IonAlert.WARNING_TYPE)
                                    .setTitleText("Rezervasyon Başarılı bir şekilde yapıldı.Rezervasyonunuzu Rezervasyonlarım kısmından görebilirsiniz.")
                                    .setConfirmText("Rezervasyonlarım'a Git")
                                    .setCancelText("Anasayfa'ya Dön")
                                    .setConfirmClickListener(object : IonAlert.ClickListener {
                                        override fun onClick(sDialog: IonAlert) {
                                            startActivity(Intent(applicationContext, ProfileActivity::class.java))
                                            sDialog.dismissWithAnimation()
                                        }
                                    })
                                    .setCancelClickListener(object : IonAlert.ClickListener {
                                        override fun onClick(sDialog: IonAlert) {
                                            startActivity(Intent(applicationContext, MainActivity::class.java))
                                            sDialog.dismissWithAnimation()
                                        }
                                    })
                                    .show()

                            }

                        }

            }
        }
        else if (gelendeger.toString() == "Tekne")
        {
            binding.turpuanlayout.visibility=View.GONE
            val ttId = intent.getStringExtra(TT_ID_EXTRA)
            val teknetur = ttFromID(ttId.toString())
            if (teknetur != null) {
                        binding.rezTuradi.text = teknetur.adi.toString()
                        Picasso.get().load(teknetur.resimUrl).into(binding.rezfoto)
                        binding.rezguzergah.text =
                            teknetur.baslangicKonumu.toString() + " -->" + teknetur.guzergah.toString()

                val formatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale("tr"))
                val giristarihi = LocalDate.parse(teknetur.baslangicTarihi.toString(), formatter)

                val gelecekTarih = giristarihi.plusDays(5)
                val cikistarihiFormatter =
                    DateTimeFormatter.ofPattern("dd LLLL yyyy", Locale("tr"))
                val cikistarihi = gelecekTarih.format(cikistarihiFormatter)

                val dateFormatter =
                    DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("tr"))

                val giristarihi2 =
                    LocalDate.parse(teknetur.baslangicTarihi.toString(), formatter)
                val giristring = giristarihi2.format(dateFormatter)
                        binding.reztarih2.text = giristring.toString()+" - "+cikistarihi.toString()
                        binding.rezfiyat.text = "Kişi Başı " + teknetur.fiyat.toString() + " TL"

                        binding.rezervasyonolusturbtn.setOnClickListener {
                            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                            val currentDate = dateFormat.parse(teknetur.baslangicTarihi.toString())
                            val calendar = Calendar.getInstance()
                            calendar.time = currentDate
                            calendar.add(Calendar.DAY_OF_MONTH, 5)
                            val futureDate = calendar.time
                            val futureDateString = dateFormat.format(futureDate)
                            val dateTime = LocalDateTime.now()
                            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                            val formattedDateTime = dateTime.format(formatter)
                            if(kullanankisi.isEmpty())
                            {
                                IonAlert(this, IonAlert.WARNING_TYPE)
                                    .setTitleText("Rezervasyon Yapmak için Lütfen Giriş Yapın.")
                                    .setConfirmText("Giriş Yap")
                                    .setCancelText("Kapat")
                                    .setConfirmClickListener(object : IonAlert.ClickListener {
                                        override fun onClick(sDialog: IonAlert) {
                                            startActivity(Intent(applicationContext, GirisYapActivity::class.java))
                                            sDialog.dismissWithAnimation()
                                        }
                                    })
                                    .setCancelClickListener(object : IonAlert.ClickListener {
                                        override fun onClick(sDialog: IonAlert) {

                                            sDialog.dismissWithAnimation()
                                        }
                                    })
                                    .show()
                            }
                            else
                            {
                                postrez(
                                    formattedDateTime.toString(),
                                    yetiskinsayi2.toString(),
                                    cocuksayi2.toString(),
                                    teknetur.idTekneTur.toString(),
                                    "teknetur",
                                    teknetur.baslangicTarihi.toString(),
                                    futureDateString.toString()
                                )
                                IonAlert(this, IonAlert.WARNING_TYPE)
                                    .setTitleText("Rezervasyon Başarılı bir şekilde yapıldı.Rezervasyonunuzu Rezervasyonlarım kısmından görebilirsiniz.")
                                    .setConfirmText("Rezervasyonlarım'a Git")
                                    .setCancelText("Anasayfa'ya Dön")
                                    .setConfirmClickListener(object : IonAlert.ClickListener {
                                        override fun onClick(sDialog: IonAlert) {
                                            startActivity(Intent(applicationContext, ProfileActivity::class.java))
                                            sDialog.dismissWithAnimation()
                                        }
                                    })
                                    .setCancelClickListener(object : IonAlert.ClickListener {
                                        override fun onClick(sDialog: IonAlert) {
                                            startActivity(Intent(applicationContext, MainActivity::class.java))
                                            sDialog.dismissWithAnimation()
                                        }
                                    })
                                    .show()
                            }

                        }

            }
        }
        else if (gelendeger.toString() == "Otel")
        {
            binding.turtarihrelative.visibility=View.GONE
            binding.cardcikistarihi.visibility=View.VISIBLE
            binding.cardgiristarihi.visibility=View.VISIBLE
            val otelId = intent.getStringExtra(OTEL_ID_EXTRA)
            val otel = otelID(otelId.toString())
            if (otel != null) {

                        var hangioda = intent.getStringExtra(ODA_EXTRA)
                        binding.rezTuradi.text = otel.adi.toString()
                        Picasso.get().load(otel.resimUrl).into(binding.rezfoto)
                        binding.rezguzergah.text = otel.adres.toString()
                        binding.turtarihtext.text = "Otel Puanı: "
                        binding.reztarih.text = otel.puan.toString()
                        binding.rezfiyat.text = otel.tema.toString()
                        binding.odaturu.text = "Oda Türü: "
                        if (hangioda == "1") {
                            binding.rezturkod.text = "Standart Oda"
                        } else if (hangioda == "2") {
                            binding.rezturkod.text = "Manzaralı Oda"
                        } else if (hangioda == "3") {
                            binding.rezturkod.text = "Suite Oda"
                        }
                        binding.rezervasyonolusturbtn.setOnClickListener {
                            val dateTime = LocalDateTime.now()
                            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                            val formattedDateTime = dateTime.format(formatter)
                            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("tr"))
                            val giristarihi = sdf.format(mycalender.time)
                            val cikstarihi = sdf.format(mycalender2.time)
                            if(kullanankisi.isEmpty())
                            {
                                IonAlert(this, IonAlert.WARNING_TYPE)
                                    .setTitleText("Rezervasyon Yapmak için Lütfen Giriş Yapın.")
                                    .setConfirmText("Giriş Yap")
                                    .setCancelText("Kapat")
                                    .setConfirmClickListener(object : IonAlert.ClickListener {
                                        override fun onClick(sDialog: IonAlert) {
                                            startActivity(Intent(applicationContext, GirisYapActivity::class.java))
                                            sDialog.dismissWithAnimation()
                                        }
                                    })
                                    .setCancelClickListener(object : IonAlert.ClickListener {
                                        override fun onClick(sDialog: IonAlert) {

                                            sDialog.dismissWithAnimation()
                                        }
                                    })
                                    .show()
                            }
                            else
                            {
                                postrez(
                                    formattedDateTime.toString(),
                                    yetiskinsayi2.toString(),
                                    cocuksayi2.toString(),
                                    otel.idOtel.toString(),
                                    "otel",
                                    giristarihi.toString(),
                                    cikstarihi.toString()
                                )
                                IonAlert(this, IonAlert.WARNING_TYPE)
                                    .setTitleText("Rezervasyon Başarılı bir şekilde yapıldı.Rezervasyonunuzu Rezervasyonlarım kısmından görebilirsiniz.")
                                    .setConfirmText("Rezervasyonlarım'a Git")
                                    .setCancelText("Anasayfa'ya Dön")
                                    .setConfirmClickListener(object : IonAlert.ClickListener {
                                        override fun onClick(sDialog: IonAlert) {
                                            startActivity(Intent(applicationContext, ProfileActivity::class.java))
                                            sDialog.dismissWithAnimation()
                                        }
                                    })
                                    .setCancelClickListener(object : IonAlert.ClickListener {
                                        override fun onClick(sDialog: IonAlert) {
                                            startActivity(Intent(applicationContext, MainActivity::class.java))
                                            sDialog.dismissWithAnimation()
                                        }
                                    })
                                    .show()
                            }

                        }
            }
        }
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

                if (yetiskinsayi2 == 2) {
                    binding.birincikisi.visibility = View.VISIBLE
                    binding.ikinciyetiskinkisi.visibility = View.VISIBLE
                    binding.ucuncyetiskinkisi.visibility = View.GONE
                    binding.dorduncuyetiskinkisi.visibility = View.GONE
                } else if (yetiskinsayi2 == 1) {
                    binding.birincikisi.visibility = View.VISIBLE
                    binding.ikinciyetiskinkisi.visibility = View.GONE
                    binding.ucuncyetiskinkisi.visibility = View.GONE
                    binding.dorduncuyetiskinkisi.visibility = View.GONE
                } else if (yetiskinsayi2 == 0) {
                    binding.birincikisi.visibility = View.GONE
                    binding.ikinciyetiskinkisi.visibility = View.GONE
                    binding.ucuncyetiskinkisi.visibility = View.GONE
                    binding.dorduncuyetiskinkisi.visibility = View.GONE
                } else if (yetiskinsayi2 == 3) {
                    binding.birincikisi.visibility = View.VISIBLE
                    binding.ikinciyetiskinkisi.visibility = View.VISIBLE
                    binding.ucuncyetiskinkisi.visibility = View.VISIBLE

                    binding.dorduncuyetiskinkisi.visibility = View.GONE
                } else if (yetiskinsayi2 == 4) {
                    binding.birincikisi.visibility = View.VISIBLE
                    binding.ikinciyetiskinkisi.visibility = View.VISIBLE
                    binding.ucuncyetiskinkisi.visibility = View.VISIBLE
                    binding.dorduncuyetiskinkisi.visibility = View.VISIBLE
                }


                if (cocuksayi2 == 1) {
                    binding.birincicocuk.visibility = View.VISIBLE
                    binding.ikincicocuk.visibility = View.GONE
                    binding.ucuncucocuk.visibility = View.GONE
                    binding.dorduncucocuk.visibility = View.GONE
                } else if (cocuksayi2 == 0) {
                    binding.birincicocuk.visibility = View.GONE
                    binding.ikincicocuk.visibility = View.GONE
                    binding.ucuncucocuk.visibility = View.GONE
                    binding.dorduncucocuk.visibility = View.GONE
                } else if (cocuksayi2 == 2) {
                    binding.birincicocuk.visibility = View.VISIBLE
                    binding.ikincicocuk.visibility = View.VISIBLE

                    binding.ucuncucocuk.visibility = View.GONE
                    binding.dorduncucocuk.visibility = View.GONE

                } else if (cocuksayi2 == 3) {
                    binding.birincicocuk.visibility = View.VISIBLE
                    binding.ikincicocuk.visibility = View.VISIBLE
                    binding.ucuncucocuk.visibility = View.VISIBLE
                    binding.dorduncucocuk.visibility = View.GONE
                } else if (cocuksayi2 == 4) {
                    binding.birincicocuk.visibility = View.VISIBLE
                    binding.ikincicocuk.visibility = View.VISIBLE
                    binding.ucuncucocuk.visibility = View.VISIBLE
                    binding.dorduncucocuk.visibility = View.VISIBLE
                }
            }

        }
        binding.kisibilgileribtn.setOnClickListener {
            if (binding.layoutkisibilgileri.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(
                    binding.kisilergenellayout,
                    AutoTransition()
                )
                binding.layoutkisibilgileri.visibility = View.VISIBLE
            } else {
                TransitionManager.beginDelayedTransition(
                    binding.kisilergenellayout,
                    AutoTransition()
                )
                binding.layoutkisibilgileri.visibility = View.GONE
            }
        }
        binding.bilgilerimikullan.setOnClickListener {
            binding.rezkullaniciisim.setText(kullanankisi[0].adi)
            binding.rezkullanicisoyisim.setText(kullanankisi[0].soyadi)
            binding.rezkullanicitelno.setText(kullanankisi[0].telefonNumarasi)
            binding.rezkullaniciemail.setText(kullanankisi[0].epostaAdresi)
            val formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale("tr"))
            val dtarihi = LocalDate.parse(kullanankisi[0].dogumTarihi.toString(), formatter)
            val cikistarihiFormatter =
                DateTimeFormatter.ofPattern("dd LLLL yyyy", Locale("tr"))
            val cikistarihi = dtarihi.format(cikistarihiFormatter)
            binding.rezkullanidtarih.setText(cikistarihi.toString())
        }
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

    private fun ydtFromID(ydtId: String): YurtDisiTurView? {
        for (ydt in yurtdisiviewListesi) {
            if (ydt.idYurtdisiTur == ydtId)
                return ydt
        }
        return null
    }

    private fun ttFromID(ttId: String): TekneTurView? {
        for (tt in tekneturviewListesi) {
            if (tt.idTekneTur == ttId)
                return tt
        }
        return null
    }

    private fun otelID(otelId: String): OtelView? {
        for (otel in otelviewListesi) {
            if (otel.idOtel.toString() == otelId.toString())
                return otel
        }
        return null
    }

    private fun postrez(
        tarihi: String,
        yetiskinsayisi: String,
        cocuksayisi: String,
        otelid: String,
        hizmettabloadi: String,
        giris:String,
        cikis:String
    ) {
        GlobalScope.launch {
            val client = OkHttpClient()
            val formBody = FormBody.Builder()
                .add("tarihi", tarihi.toString())
                .add("yetiskinSayisi", yetiskinsayisi.toString())
                .add("cocukSayisi", cocuksayisi.toString())
                .add("Musteri_idMusteri", kullanankisi[0].idMusteri.toString())
                .add("hizmetTabloId", otelid.toString())
                .add("hizmetTabloAdi", hizmettabloadi.toString())
                .add("girisTarihi", giris.toString())
                .add("cikisTarihi", cikis.toString())
                .build()
            val request = Request.Builder()
                .url(url + "createRezervasyon")
                .post(formBody)
                .build()
            val response = client.newCall(request).execute()
            Log.d("response", response.toString())
        }
    }
}