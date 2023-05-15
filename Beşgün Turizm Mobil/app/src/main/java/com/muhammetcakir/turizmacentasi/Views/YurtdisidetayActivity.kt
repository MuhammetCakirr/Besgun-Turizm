package com.muhammetcakir.turizmacentasi.Views

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import androidx.annotation.RequiresApi
import com.muhammetcakir.turizmacentasi.*
import com.muhammetcakir.turizmacentasi.Database.DbServices
import com.muhammetcakir.turizmacentasi.Models.YurtDisiTurView
import com.muhammetcakir.turizmacentasi.Models.YurtdisiTur
import com.muhammetcakir.turizmacentasi.databinding.ActivityYurtdisidetayBinding
import com.squareup.picasso.Picasso
import id.ionbit.ionalert.IonAlert
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class YurtdisidetayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityYurtdisidetayBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityYurtdisidetayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        binding.turprogramibtn.setOnClickListener {
            if (binding.layoutturprogrami.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(binding.genelturlayout, AutoTransition())
                binding.layoutturprogrami.visibility = View.VISIBLE
            } else {
                TransitionManager.beginDelayedTransition(binding.genelturlayout, AutoTransition())
                binding.layoutturprogrami.visibility = View.GONE
            }
        }

        val ydtId = intent.getStringExtra(YDT_ID_EXTRA)
        val ydt = ydtFromID(ydtId.toString())

        if (ydt!=null)
        {
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
                    else
                    {
                        val intent = Intent(this, RezervasyonActivity::class.java)
                        intent.putExtra(TP_ID_EXTRA,"YurtDisi")
                        intent.putExtra(YDT_ID_EXTRA, ydt.idYurtdisiTur.toString())
                        startActivity(intent)
                        finish()
                    }

                }
                    binding.turadi.text=ydt.adi.toString()
                    binding.Turbaslangic.text= ydt.baslangicKonumu.toString()+" -->"
                    binding.Turguzergah.text=ydt.guzergah.toString()
                    binding.turfiyat.text="Kişi Başı "+ydt.fiyat.toString()+" TL"

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
                    binding.turtarih.text=giristring.toString()+" - "+cikistarihi.toString()
                    Picasso.get().load(ydt.resimUrl).into(binding.yurtdisifoto)


                    val gunbir =  ydt.gunBir.toString()
                    val birincigunbaslik = gunbir.substring(gunbir.indexOf("(") + 1, gunbir.indexOf(")"))
                    val birincigunparagraf= gunbir.substring(gunbir.indexOf(")") + 2)
                    binding.birincigunparagraf.text= birincigunparagraf.toString()
                    binding.birincigunbaslik.text= birincigunbaslik

                    val guniki =  ydt.gunIki.toString()
                    val ikincigunbaslik = guniki.substring(guniki.indexOf("(") + 1, guniki.indexOf(")"))
                    val ikincigunparagraf= guniki.substring(guniki.indexOf(")") + 2)
                    binding.ikincigunbaslikparagraf.text= ikincigunparagraf
                    binding.ikincigunbaslik.text= ikincigunbaslik

                    val gunuc =  ydt.gunUc.toString()
                    val ucuncugunbaslik = gunuc.substring(gunuc.indexOf("(") + 1, gunuc.indexOf(")"))
                    val ucuncugunparagraf= gunuc.substring(gunuc.indexOf(")") + 2)
                    binding.ucuncugunparagraf.text= ucuncugunparagraf
                    binding.ucuncugunbaslik.text= ucuncugunbaslik

                    val gundort =  ydt.gunDort.toString()
                    val dorduncugunbaslik = gundort.substring(gundort.indexOf("(") + 1, gundort.indexOf(")"))
                    val dorduncugunparagraf= gundort.substring(gundort.indexOf(")") + 2)
                    binding.dorduncugunparagraf.text= dorduncugunparagraf
                    binding.dorduncugunbaslik.text= dorduncugunbaslik

                    val gunbes = ydt.gunBes.toString()
                    val besincigunbaslik = gunbes.substring(gunbes.indexOf("(") + 1, gunbes.indexOf(")"))
                    val besincigunparagraf= gunbes.substring(gunbes.indexOf(")") + 2)
                    binding.besincigunparagraf.text= besincigunparagraf
                    binding.besincigunbaslik.text= besincigunbaslik
        }

    }
    private fun ydtFromID(ydtId: String): YurtDisiTurView? {
        for (ydt in yurtdisiviewListesi) {
            if (ydt.idYurtdisiTur == ydtId)
                return ydt
        }
        return null
    }

}