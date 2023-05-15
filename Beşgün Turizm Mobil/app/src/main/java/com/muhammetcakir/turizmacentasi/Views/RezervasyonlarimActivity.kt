package com.muhammetcakir.turizmacentasi.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager

import com.muhammetcakir.turizmacentasi.*

import com.muhammetcakir.turizmacentasi.Adapters.RezervasyonlarimAdapter
import com.muhammetcakir.turizmacentasi.ClickListener.Clickrezervasyon
import com.muhammetcakir.turizmacentasi.Database.DbServices
import com.muhammetcakir.turizmacentasi.Database.benimrezervasyonlarimlistesi

import com.muhammetcakir.turizmacentasi.Database.rezervasyonListesi

import com.muhammetcakir.turizmacentasi.Models.Rezervasyon
import com.muhammetcakir.turizmacentasi.databinding.ActivityRezervasyonlarimBinding
import kotlinx.coroutines.*


class RezervasyonlarimActivity : AppCompatActivity(),Clickrezervasyon {
private lateinit var binding:ActivityRezervasyonlarimBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRezervasyonlarimBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        runBlocking {
            rezervasyonListesi.clear()
            val db=DbServices()
            val deferred = async {
                db.getRezervasyon()
            }
            val yenirezervasyonListesi = deferred.await()
            rezervasyonListesi.addAll(yenirezervasyonListesi)
        }
        benimrezervasyonlarimlistesi.clear()
        for (rez in rezervasyonListesi)
        {
           if(rez.Musteri_idMusteri.toString()== kullanankisi[0].idMusteri.toString())
           {
               benimrezervasyonlarimlistesi.add(rez)
           }
        }
        binding.rcrezervasyonlarim.apply {
            layoutManager = LinearLayoutManager(this@RezervasyonlarimActivity, LinearLayoutManager.VERTICAL,false)
            adapter = RezervasyonlarimAdapter(benimrezervasyonlarimlistesi, this@RezervasyonlarimActivity)
            binding.rcrezervasyonlarim.adapter = adapter
            binding.rcrezervasyonlarim.layoutManager=layoutManager
        }

    }

    override fun onclickrezervasyon(rezervasyon: Rezervasyon) {
        val intent = Intent(applicationContext, RezGuncelleActivity::class.java)
        intent.putExtra(REZ_ID_EXRTRA, rezervasyon.idRezervasyon.toString())
        startActivity(intent)
    }

}


