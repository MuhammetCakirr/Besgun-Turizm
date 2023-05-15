package com.muhammetcakir.turizmacentasi.Views

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.muhammetcakir.turizmacentasi.*

import com.muhammetcakir.turizmacentasi.Models.TekneTurView
import com.muhammetcakir.turizmacentasi.databinding.ActivityTekneTurlariBinding

import com.squareup.picasso.Picasso
import id.ionbit.ionalert.IonAlert
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class TekneTurlariActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTekneTurlariBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTekneTurlariBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()


        val ttId = intent.getStringExtra(TT_ID_EXTRA)
        val teknetur = ttFromID(ttId.toString())

        if (teknetur != null) {


            binding.rezislemleri.setOnClickListener {
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
                else{
                    val intent = Intent(this, RezervasyonActivity::class.java)
                    intent.putExtra(TP_ID_EXTRA, "Tekne")
                    intent.putExtra(TT_ID_EXTRA, teknetur.idTekneTur.toString())
                    startActivity(intent)
                    finish()
                }
            }
                binding.turadi.text = teknetur.adi.toString()
                binding.guzergah.text =
                    teknetur.baslangicKonumu.toString() + " -->" + teknetur.guzergah.toString()
                binding.fiyat.text = "Kişi Başı " + teknetur.fiyat.toString() + " TL"
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
                binding.tarih.text=giristring.toString()+" - "+cikistarihi.toString()
                Picasso.get().load(teknetur.resimUrl).into(binding.cover)


                val gunbir = teknetur.gunBir.toString()
                val birincigunbaslik =
                    gunbir.substring(gunbir.indexOf("(") + 1, gunbir.indexOf(")"))
                val birincigunparagraf = gunbir.substring(gunbir.indexOf(")") + 2)
                binding.birincigunparagraf.text = birincigunparagraf.toString()
                binding.birincigunbaslik.text = birincigunbaslik

                val guniki = teknetur.gunIki.toString()
                val ikincigunbaslik = guniki.substring(guniki.indexOf("(") + 1, guniki.indexOf(")"))
                val ikincigunparagraf = guniki.substring(guniki.indexOf(")") + 2)
                binding.ikincigunbaslikparagraf.text = ikincigunparagraf
                binding.ikincigunbaslik.text = ikincigunbaslik

                val gunuc = teknetur.gunUc.toString()
                val ucuncugunbaslik = gunuc.substring(gunuc.indexOf("(") + 1, gunuc.indexOf(")"))
                val ucuncugunparagraf = gunuc.substring(gunuc.indexOf(")") + 2)
                binding.ucuncugunparagraf.text = ucuncugunparagraf
                binding.ucuncugunbaslik.text = ucuncugunbaslik

                val gundort = teknetur.gunDort.toString()
                val dorduncugunbaslik =
                    gundort.substring(gundort.indexOf("(") + 1, gundort.indexOf(")"))
                val dorduncugunparagraf = gundort.substring(gundort.indexOf(")") + 2)
                binding.dorduncugunparagraf.text = dorduncugunparagraf
                binding.dorduncugunbaslik.text = dorduncugunbaslik

                val gunbes = teknetur.gunBes.toString()
                val besincigunbaslik =
                    gunbes.substring(gunbes.indexOf("(") + 1, gunbes.indexOf(")"))
                val besincigunparagraf = gunbes.substring(gunbes.indexOf(")") + 2)
                binding.besincigunparagraf.text = besincigunparagraf
                binding.besincigunbaslik.text = besincigunbaslik



        }
    }

    private fun ttFromID(ttId: String): TekneTurView? {
        for (tt in tekneturviewListesi) {
            if (tt.idTekneTur == ttId)
                return tt
        }
        return null
    }
}