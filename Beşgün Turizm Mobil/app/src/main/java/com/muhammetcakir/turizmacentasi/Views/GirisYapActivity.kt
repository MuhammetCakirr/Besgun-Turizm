package com.muhammetcakir.turizmacentasi.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.muhammetcakir.turizmacentasi.Database.musteriListesi
import com.muhammetcakir.turizmacentasi.MainActivity
import com.muhammetcakir.turizmacentasi.databinding.ActivityGirisYapBinding
import com.muhammetcakir.turizmacentasi.suankullanicivarmi

class GirisYapActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGirisYapBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGirisYapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        binding.btnkayitol.setOnClickListener {
            startActivity(Intent(this,KayitOlActivity::class.java))
        }

        binding.btngirisyap.setOnClickListener {

            for (musteri in musteriListesi) {
                if (musteri.epostaAdresi.toString() == binding.girisyapeposta.text.toString() && musteri.sifre.toString() == binding.girisyapsifre.text.toString()) {
                    suankullanicivarmi = true
                    suankikullanicieposta = binding.girisyapeposta.text.toString()
                    break
                }
            }

            if (suankullanicivarmi) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                binding.idgirisdurum.visibility = View.VISIBLE
            }
        }
    }
}