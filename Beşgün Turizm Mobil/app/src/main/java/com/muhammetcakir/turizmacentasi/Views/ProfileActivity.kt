package com.muhammetcakir.turizmacentasi.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.muhammetcakir.turizmacentasi.*
import com.muhammetcakir.turizmacentasi.databinding.ActivityProfileBinding
import id.ionbit.ionalert.IonAlert
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding:ActivityProfileBinding
    private val url = "http://192.168.1.122/turizmacentasi/v1/?op="
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        if (kullanankisi.isEmpty())
        {
            binding.kullaniciadsoyad.visibility= View.GONE
            binding.kullanicimail.visibility= View.GONE
        }
        else
        {
            binding.kullaniciadsoyad.text= kullanankisi[0].adi.toString()
            binding.kullanicimail.text= kullanankisi[0].epostaAdresi.toString()
        }

        binding.btnhesabim.setOnClickListener {
            if(kullanankisi.isEmpty())
            {
                IonAlert(this, IonAlert.WARNING_TYPE)
                    .setTitleText("Lütfen Giriş Yapın.")
                    .setConfirmText("Ok")
                    .setConfirmClickListener(object : IonAlert.ClickListener {
                        override fun onClick(sDialog: IonAlert) {
                            startActivity(Intent(applicationContext, GirisYapActivity::class.java))
                            sDialog.dismissWithAnimation()
                        }
                    })
                    .show()
            }
            else
            {
                startActivity(Intent(applicationContext, MyAccountActivity::class.java))
            }

        }
        binding.btnrezervasyonlarim.setOnClickListener {
            if(kullanankisi.isEmpty())
            {
                IonAlert(this, IonAlert.WARNING_TYPE)
                    .setTitleText("Lütfen Giriş Yapın.")
                    .setConfirmText("Ok")
                    .setConfirmClickListener(object : IonAlert.ClickListener {
                        override fun onClick(sDialog: IonAlert) {
                            startActivity(Intent(applicationContext, GirisYapActivity::class.java))
                            sDialog.dismissWithAnimation()
                        }
                    })
                    .show()
            }
            else
            {
                startActivity(Intent(applicationContext, RezervasyonlarimActivity::class.java))
            }
        }
        binding.cikisyapbtn.setOnClickListener {
            IonAlert(this, IonAlert.ERROR_TYPE)
                .setTitleText("Çıkış yapmak istediğine emin misin?")
                .setConfirmText("Çıkış")
                .setCancelText("İptal")
                .setCancelClickListener(object : IonAlert.ClickListener {
                    override fun onClick(sDialog: IonAlert) {
                        sDialog.dismissWithAnimation()
                    }
                })
                .setConfirmClickListener(object : IonAlert.ClickListener {
                    override fun onClick(sDialog: IonAlert) {
                        kullanankisi.clear()
                        suankullanicivarmi=false
                        startActivity(Intent(applicationContext,MainActivity::class.java))
                        sDialog.dismissWithAnimation()
                    }
                })
                .show()
        }

        binding.hesabimisilbtn.setOnClickListener {
            IonAlert(this, IonAlert.ERROR_TYPE)
                .setTitleText("Hesabını Silmek istediğine emin misin?")
                .setConfirmText("Hesabımı Sil")
                .setCancelText("Kapat")
                .setCancelClickListener(object : IonAlert.ClickListener {
                    override fun onClick(sDialog: IonAlert) {
                        sDialog.dismissWithAnimation()
                    }
                })
                .setConfirmClickListener(object : IonAlert.ClickListener {
                    override fun onClick(sDialog: IonAlert) {
                        deletemy(kullanankisi[0].idMusteri)
                        suankullanicivarmi=false
                        kullanankisi.clear()
                        sDialog.dismissWithAnimation()
                        startActivity(Intent(applicationContext,MainActivity::class.java))
                    }
                })
                .show()
        }
        binding.bottomNavigation.setSelectedItemId(R.id.profil)
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.getItemId()) {

                R.id.anasayfa -> {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    return@setOnItemSelectedListener true
                }
                R.id.reserve -> {
                    startActivity(Intent(applicationContext, OtellerActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    return@setOnItemSelectedListener true
                }
                R.id.profil -> {
                     true
                }
            }
            false
        }
    }
    private fun deletemy(
        id: String
    ) {
        GlobalScope.launch {
            val client = OkHttpClient()
            val formBody = FormBody.Builder()
                .add("id", id.toString())
                .build()
            val request = Request.Builder()
                .url(url + "deleteMusteri")
                .post(formBody)
                .build()
            val response = client.newCall(request).execute()
            Log.d("response", response.toString())
        }
    }
}