package com.muhammetcakir.turizmacentasi.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.muhammetcakir.turizmacentasi.*

import com.muhammetcakir.turizmacentasi.Models.OtelView

import com.muhammetcakir.turizmacentasi.databinding.ActivityOteldetayBinding
import com.squareup.picasso.Picasso
import id.ionbit.ionalert.IonAlert

class OteldetayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOteldetayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOteldetayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        val otelId = intent.getStringExtra(OTEL_ID_EXTRA)
        val otel = otelID(otelId.toString())
        if (otel != null) {

                    binding.standartbtn.setOnClickListener {
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
                            intent.putExtra(TP_ID_EXTRA, "Otel")
                            intent.putExtra(OTEL_ID_EXTRA, otel.idOtel.toString())
                            intent.putExtra(ODA_EXTRA, "1")
                            startActivity(intent)
                            finish()
                        }

                    }
                    binding.denizbtn.setOnClickListener {
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
                                intent.putExtra(TP_ID_EXTRA, "Otel")
                                intent.putExtra(OTEL_ID_EXTRA, otel.idOtel.toString())
                                intent.putExtra(ODA_EXTRA, "2")
                                startActivity(intent)
                                finish()
                            }

                        }

                    }
                    binding.suiteodabtn.setOnClickListener {
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
                            intent.putExtra(TP_ID_EXTRA, "Otel")
                            intent.putExtra(OTEL_ID_EXTRA, otel.idOtel.toString())
                            intent.putExtra(ODA_EXTRA, "3")
                            startActivity(intent)
                            finish()
                        }
                    }
                    Picasso.get().load(otel.resimUrl).into(binding.cover)
                    binding.otelad.text = otel.adi.toString()
                    binding.otelkonum.text = otel.adres.toString()
                    binding.oteltema.text = otel.tema.toString()
                    binding.otelpuan.text = otel.puan.toString()
                    binding.standartodafiyat.text = otel.fiyat.toString() + " TL"
                    binding.suiteodafiyat.text = ((otel.fiyat) * 2).toString() + " TL"
                    binding.ManzaralOdafiyat.text = ((otel.fiyat) * 1.5).toString() + " TL"
        }
    }
    private fun otelID(otelId: String): OtelView? {
        for (otel in otelviewListesi) {
            if (otel.idOtel.toString() == otelId.toString())
                return otel
        }
        return null
    }
}