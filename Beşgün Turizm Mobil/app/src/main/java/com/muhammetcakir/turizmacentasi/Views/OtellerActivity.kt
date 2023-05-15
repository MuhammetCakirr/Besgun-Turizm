package com.muhammetcakir.turizmacentasi.Views

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhammetcakir.turizmacentasi.*
import com.muhammetcakir.turizmacentasi.Adapters.OtellerAdapter
import com.muhammetcakir.turizmacentasi.Adapters.TekneTurAdapter
import com.muhammetcakir.turizmacentasi.Adapters.YurtDisiTurAdapter
import com.muhammetcakir.turizmacentasi.ClickListener.ClicktekneTur
import com.muhammetcakir.turizmacentasi.ClickListener.Clicktotel
import com.muhammetcakir.turizmacentasi.ClickListener.ClickyurtDisiTurView

import com.muhammetcakir.turizmacentasi.Models.*

import com.muhammetcakir.turizmacentasi.databinding.ActivityOtellerBinding

class OtellerActivity: AppCompatActivity(),ClickyurtDisiTurView,ClicktekneTur,Clicktotel{

    private lateinit var binding: ActivityOtellerBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding= ActivityOtellerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        binding.bottomNavigation.setSelectedItemId(R.id.reserve)
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.getItemId()) {
                R.id.anasayfa -> {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    return@setOnItemSelectedListener true
                }
                R.id.reserve -> {

                    return@setOnItemSelectedListener true
                }
                R.id.profil -> {
                    startActivity(Intent(applicationContext, ProfileActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
        val popId = intent.getStringExtra(POP_ID_EXRTRA)
        val pop = POPID(popId.toString())
        val temaId = intent.getStringExtra(TEMA_ID_EXRTRA)
        val tema = temaID(temaId.toString())
        val name = intent.getStringExtra("Name")
        if (name=="Otel")
        {
            binding.turadi.text="Oteller"
            binding.toplamtesis.text="Toplam ${otelviewListesi.count()} Otel Tesisi Bulduk!"

            binding.rchizmetler.apply {
                layoutManager = LinearLayoutManager(this@OtellerActivity, LinearLayoutManager.VERTICAL,false)
                adapter = OtellerAdapter(
                    otelviewListesi,
                    context,this@OtellerActivity)
                binding.rchizmetler.adapter = adapter
                binding.rchizmetler.layoutManager=layoutManager
            }

            var arananotel=ArrayList<OtelView>()
            var genelliste=ArrayList<OtelView>()
            binding.filtrelebtn.setOnClickListener {
                arananotel.clear()
                genelliste.addAll(otelviewListesi)
                val dialogbinding=layoutInflater.inflate(R.layout.filtrele_otel_dialog,null)
                val mydialog= Dialog(this)
                mydialog.setContentView(dialogbinding)
                mydialog.setCancelable(true)
                mydialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                mydialog.show()

                val checkaltiustu=dialogbinding.findViewById<CheckBox>(R.id.altiveustu)
                val checkyediustu=dialogbinding.findViewById<CheckBox>(R.id.yediveustu)
                val checksekizustu=dialogbinding.findViewById<CheckBox>(R.id.sekizveustu)
                val checkdokuzustu=dialogbinding.findViewById<CheckBox>(R.id.dokuzveustu)
                val balayiotelleri=dialogbinding.findViewById<CheckBox>(R.id.balayioteller)
                val butikoteller=dialogbinding.findViewById<CheckBox>(R.id.butikoteller)
                val termaloteller=dialogbinding.findViewById<CheckBox>(R.id.termaloteller)
                val sehiroteller=dialogbinding.findViewById<CheckBox>(R.id.sehirotel)
                val tatilkoyleri=dialogbinding.findViewById<CheckBox>(R.id.tatilkoyleri)
                val minfiyat=dialogbinding.findViewById<EditText>(R.id.minfiyat)
                val maxfiyat=dialogbinding.findViewById<EditText>(R.id.maxfiyat)


                val cikisbtn=dialogbinding.findViewById<ImageView>(R.id.cikisfiltrele)
                val onaylabtn=dialogbinding.findViewById<CardView>(R.id.filtreleonaylabtn)

                val puanrlayoutbtn=dialogbinding.findViewById<RelativeLayout>(R.id.puanagorefiltbtn)
                val fiyatrlayoutbtn=dialogbinding.findViewById<RelativeLayout>(R.id.fiyatagorefiltbtn)
                val tatiltemarlayoutbtn=dialogbinding.findViewById<RelativeLayout>(R.id.tatiltemasinafiltrebtn)


                val layoutpuan=dialogbinding.findViewById<LinearLayout>(R.id.layoutpuanagore)
                val layoutfiyat=dialogbinding.findViewById<LinearLayout>(R.id.layoutfiyataralıgınagore)
                val layouttatiltema=dialogbinding.findViewById<LinearLayout>(R.id.layouttatiltemasi)


                val cardpuan=dialogbinding.findViewById<CardView>(R.id.cardviewpuanagore)
                val cardfiyat=dialogbinding.findViewById<CardView>(R.id.cardviewfiyataralıgı)
                val cardtatiltema=dialogbinding.findViewById<CardView>(R.id.cardviewtatiltemasi)

                onaylabtn.setOnClickListener {
                    var minFiyattext = minfiyat.text.toString().toIntOrNull()
                    var maxFiyattext = maxfiyat.text.toString().toIntOrNull()
                    val otelcheckboxList = listOf(checkaltiustu, checkyediustu, checksekizustu,checkdokuzustu,balayiotelleri,butikoteller,termaloteller,sehiroteller,tatilkoyleri)
                    val seciliCheckboxListesi = otelcheckboxList.filter { checkbox -> checkbox.isChecked }

                    if (seciliCheckboxListesi.isNotEmpty())
                    {
                        genelliste.filter { veri ->

                            if (checkaltiustu.isChecked && veri.puan>6)
                            {
                                 if (balayiotelleri.isChecked && veri.tema=="Balayı Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (termaloteller.isChecked && veri.tema=="Termal Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (butikoteller.isChecked && veri.tema=="Butik Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (sehiroteller.isChecked && veri.tema=="Şehir Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (tatilkoyleri.isChecked && veri.tema=="Tatil Köyü")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }

                                else{
                                    if (seciliCheckboxListesi.contains(checkyediustu) ||seciliCheckboxListesi.contains(checksekizustu)||seciliCheckboxListesi.contains(checkdokuzustu) ||seciliCheckboxListesi.contains(balayiotelleri)
                                        ||seciliCheckboxListesi.contains(tatilkoyleri)||seciliCheckboxListesi.contains(sehiroteller)||seciliCheckboxListesi.contains(butikoteller)||seciliCheckboxListesi.contains(termaloteller)
                                    )
                                    {

                                    }
                                    else{
                                        if (!arananotel.contains(veri))
                                        {
                                            arananotel.add(veri)
                                            true
                                        }
                                    }

                                }

                                true
                            }
                             if (checkyediustu.isChecked && veri.puan>7)
                            {
                                 if (balayiotelleri.isChecked && veri.tema=="Balayı Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (termaloteller.isChecked && veri.tema=="Termal Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }
                                }
                                else if (butikoteller.isChecked && veri.tema=="Butik Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }
                                }
                                else if (sehiroteller.isChecked && veri.tema=="Şehir Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (tatilkoyleri.isChecked && veri.tema=="Tatil Köyü")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else{
                                    if (seciliCheckboxListesi.contains(checkaltiustu) ||seciliCheckboxListesi.contains(checksekizustu)||seciliCheckboxListesi.contains(checkdokuzustu) ||seciliCheckboxListesi.contains(balayiotelleri)
                                        ||seciliCheckboxListesi.contains(tatilkoyleri)||seciliCheckboxListesi.contains(sehiroteller)||seciliCheckboxListesi.contains(butikoteller)||seciliCheckboxListesi.contains(termaloteller)
                                    )
                                    {

                                    }
                                    else{
                                        if (!arananotel.contains(veri))
                                        {
                                            arananotel.add(veri)
                                            true
                                        }
                                    }

                                }
                                true
                            }
                             if (checksekizustu.isChecked && veri.puan>8)
                            {
                                 if(balayiotelleri.isChecked && veri.tema=="Balayı Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (termaloteller.isChecked && veri.tema=="Termal Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }
                                }
                                else if (butikoteller.isChecked && veri.tema=="Butik Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }
                                }
                                else if (sehiroteller.isChecked && veri.tema=="Şehir Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (tatilkoyleri.isChecked && veri.tema=="Tatil Köyü")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else{
                                    if (seciliCheckboxListesi.contains(checkaltiustu) ||seciliCheckboxListesi.contains(checkyediustu)||seciliCheckboxListesi.contains(checkdokuzustu) ||seciliCheckboxListesi.contains(balayiotelleri)
                                        ||seciliCheckboxListesi.contains(tatilkoyleri)||seciliCheckboxListesi.contains(sehiroteller)||seciliCheckboxListesi.contains(butikoteller)||seciliCheckboxListesi.contains(termaloteller)
                                    )
                                    {

                                    }
                                    else{
                                        if (!arananotel.contains(veri))
                                        {
                                            arananotel.add(veri)
                                            true
                                        }
                                    }

                                }
                                true
                            }
                             if (checkdokuzustu.isChecked && veri.puan>9)
                            {
                                if (balayiotelleri.isChecked && veri.tema=="Balayı Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (termaloteller.isChecked && veri.tema=="Termal Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }
                                }
                                else if (butikoteller.isChecked && veri.tema=="Butik Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }
                                }
                                else if (sehiroteller.isChecked && veri.tema=="Şehir Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (tatilkoyleri.isChecked && veri.tema=="Tatil Köyü")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else{
                                    if (seciliCheckboxListesi.contains(checkaltiustu) ||seciliCheckboxListesi.contains(checkyediustu)||seciliCheckboxListesi.contains(checksekizustu) ||seciliCheckboxListesi.contains(balayiotelleri)
                                        ||seciliCheckboxListesi.contains(tatilkoyleri)||seciliCheckboxListesi.contains(sehiroteller)||seciliCheckboxListesi.contains(butikoteller)||seciliCheckboxListesi.contains(termaloteller)
                                    )
                                    {

                                    }
                                    else{
                                        if (!arananotel.contains(veri))
                                        {
                                            arananotel.add(veri)
                                            true
                                        }
                                    }

                                }
                                true
                            }

                            if(balayiotelleri.isChecked && veri.tema == "Balayı Otel")
                            {
                                if (checkyediustu.isChecked && veri.puan>7)
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (checksekizustu.isChecked && veri.puan>8)
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (checkdokuzustu.isChecked && veri.puan>9)
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (checkaltiustu.isChecked && veri.puan>6)
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                 /*  else if (termaloteller.isChecked && veri.tema=="Termal Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (butikoteller.isChecked && veri.tema=="Butik Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (sehiroteller.isChecked && veri.tema=="Şehir Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (tatilkoyleri.isChecked && veri.tema=="Tatil Köyü")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                */
                                else{
                                    if (seciliCheckboxListesi.contains(checkaltiustu) ||seciliCheckboxListesi.contains(checkyediustu)||seciliCheckboxListesi.contains(checksekizustu) ||seciliCheckboxListesi.contains(checkdokuzustu)
                                        ||seciliCheckboxListesi.contains(tatilkoyleri)||seciliCheckboxListesi.contains(sehiroteller)||seciliCheckboxListesi.contains(butikoteller)||seciliCheckboxListesi.contains(termaloteller)
                                    )
                                    {
                                    }
                                    else{
                                        if (!arananotel.contains(veri))
                                        {
                                            arananotel.add(veri)
                                            true
                                        }
                                    }

                                }
                                true
                            }
                            if(termaloteller.isChecked && veri.tema=="Termal Otel")
                            {
                                if (checkyediustu.isChecked && veri.puan>7)
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (checksekizustu.isChecked && veri.puan>8)
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (checkdokuzustu.isChecked && veri.puan>9)
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (checkaltiustu.isChecked && veri.puan>6)
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                              /*  else if (balayiotelleri.isChecked && veri.tema == "Balayı Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (butikoteller.isChecked && veri.tema=="Butik Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (sehiroteller.isChecked && veri.tema=="Şehir Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (tatilkoyleri.isChecked && veri.tema=="Tatil Köyü")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }*/
                                else{
                                    if (seciliCheckboxListesi.contains(checkaltiustu) ||seciliCheckboxListesi.contains(checkyediustu)||seciliCheckboxListesi.contains(checksekizustu) ||seciliCheckboxListesi.contains(checkdokuzustu)
                                        ||seciliCheckboxListesi.contains(tatilkoyleri)||seciliCheckboxListesi.contains(sehiroteller)||seciliCheckboxListesi.contains(butikoteller)||seciliCheckboxListesi.contains(balayiotelleri)
                                    )
                                    {
                                    }
                                    else{
                                        if (!arananotel.contains(veri))
                                        {
                                            arananotel.add(veri)
                                            true
                                        }
                                    }

                                }
                                true
                            }
                            if(butikoteller.isChecked && veri.tema=="Butik Otel")
                            {
                                if (checkyediustu.isChecked && veri.puan>7)
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (checksekizustu.isChecked && veri.puan>8)
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (checkdokuzustu.isChecked && veri.puan>9)
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (checkaltiustu.isChecked && veri.puan>6)
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                               /* else if (balayiotelleri.isChecked && veri.tema == "Balayı Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (termaloteller.isChecked && veri.tema=="Termal Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (sehiroteller.isChecked && veri.tema=="Şehir Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (tatilkoyleri.isChecked && veri.tema=="Tatil Köyü")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }*/
                                else{
                                    if (seciliCheckboxListesi.contains(checkaltiustu) ||seciliCheckboxListesi.contains(checkyediustu)||seciliCheckboxListesi.contains(checksekizustu) ||seciliCheckboxListesi.contains(checkdokuzustu)
                                        ||seciliCheckboxListesi.contains(tatilkoyleri)||seciliCheckboxListesi.contains(sehiroteller)||seciliCheckboxListesi.contains(termaloteller)||seciliCheckboxListesi.contains(balayiotelleri)
                                    )
                                    {
                                    }
                                    else{
                                        if (!arananotel.contains(veri))
                                        {
                                            arananotel.add(veri)
                                            true
                                        }
                                    }

                                }
                                true
                            }
                            if(sehiroteller.isChecked && veri.tema=="Şehir Otel")
                            {
                                if (checkyediustu.isChecked && veri.puan>7)
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (checksekizustu.isChecked && veri.puan>8)
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (checkdokuzustu.isChecked && veri.puan>9)
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (checkaltiustu.isChecked && veri.puan>6)
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                               /* else if (balayiotelleri.isChecked && veri.tema == "Balayı Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (termaloteller.isChecked && veri.tema=="Termal Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (butikoteller.isChecked && veri.tema=="Butik Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (tatilkoyleri.isChecked && veri.tema=="Tatil Köyü")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }*/
                                else{
                                    if (seciliCheckboxListesi.contains(checkaltiustu) ||seciliCheckboxListesi.contains(checkyediustu)||seciliCheckboxListesi.contains(checksekizustu) ||seciliCheckboxListesi.contains(checkdokuzustu)
                                        ||seciliCheckboxListesi.contains(tatilkoyleri)||seciliCheckboxListesi.contains(butikoteller)||seciliCheckboxListesi.contains(termaloteller)||seciliCheckboxListesi.contains(balayiotelleri)
                                    )
                                    {
                                    }
                                    else{
                                        if (!arananotel.contains(veri))
                                        {
                                            arananotel.add(veri)
                                            true
                                        }
                                    }

                                }
                                true
                            }
                            if(tatilkoyleri.isChecked && veri.tema=="Tatil Köyü")
                            {
                                if (checkyediustu.isChecked && veri.puan>7)
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (checksekizustu.isChecked && veri.puan>8)
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (checkdokuzustu.isChecked && veri.puan>9)
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (checkaltiustu.isChecked && veri.puan>6)
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                              /*  else if (balayiotelleri.isChecked && veri.tema == "Balayı Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (termaloteller.isChecked && veri.tema=="Termal Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (butikoteller.isChecked && veri.tema=="Butik Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }
                                else if (butikoteller.isChecked && veri.tema=="Şehir Otel")
                                {
                                    if (!arananotel.contains(veri))
                                    {
                                        arananotel.add(veri)
                                        true
                                    }

                                }*/
                                else{
                                    if (seciliCheckboxListesi.contains(checkaltiustu) ||seciliCheckboxListesi.contains(checkyediustu)||seciliCheckboxListesi.contains(checksekizustu) ||seciliCheckboxListesi.contains(checkdokuzustu)
                                        ||seciliCheckboxListesi.contains(sehiroteller)||seciliCheckboxListesi.contains(butikoteller)||seciliCheckboxListesi.contains(termaloteller)||seciliCheckboxListesi.contains(balayiotelleri)
                                    )
                                    {
                                    }
                                    else{
                                        if (!arananotel.contains(veri))
                                        {
                                            arananotel.add(veri)
                                            true
                                        }
                                    }

                                }
                                true
                            }

                            false
                        }
                    }
                    if(minFiyattext!=null && maxFiyattext!=null)
                    {
                        if (arananotel.isNotEmpty())
                        {
                            val iterator = arananotel.iterator()
                            while (iterator.hasNext()) {
                                val yurtdisiTur = iterator.next()

                                if (minFiyattext != null && maxFiyattext != null) {
                                    if (yurtdisiTur.fiyat >= minFiyattext && yurtdisiTur.fiyat <= maxFiyattext) {
                                        if (!arananotel.contains(yurtdisiTur)) {
                                            arananotel.add(yurtdisiTur)
                                        }
                                    } else {
                                        if (arananotel.contains(yurtdisiTur)) {
                                            iterator.remove()
                                        }
                                    }
                                } else {
                                    println("BOOOOŞŞŞŞŞŞŞŞŞ")
                                }

                            }
                        }
                        else {
                            val iterator = genelliste.iterator()
                            while (iterator.hasNext()) {
                                val yurtdisiTur = iterator.next()

                                if (yurtdisiTur.fiyat >= minFiyattext && yurtdisiTur.fiyat <= maxFiyattext) {
                                    if (!arananotel.contains(yurtdisiTur)) {
                                        arananotel.add(yurtdisiTur)
                                    }
                                }
                                else
                                {
                                    if (arananotel.contains(yurtdisiTur)) {
                                        iterator.remove()

                                    }
                                }

                            }
                        }

                    }
                    else if(minFiyattext!=null || maxFiyattext!=null)
                    {
                        if(minFiyattext!=null)
                        {
                            if (arananotel.isEmpty())
                            {
                                val iterator = genelliste.iterator()

                                while (iterator.hasNext())
                                {
                                    val yurtdisiTur = iterator.next()

                                    if (yurtdisiTur.fiyat <= minFiyattext.toInt()) {
                                        iterator.remove()
                                    }
                                    else {
                                        if (!arananotel.contains(yurtdisiTur)) {
                                            arananotel.add(yurtdisiTur)
                                        }
                                    }
                                }
                            }
                            else{
                                val iterator = arananotel.iterator()
                                while (iterator.hasNext()) {
                                    val yurtdisiTur = iterator.next()

                                    if (yurtdisiTur.fiyat <= minFiyattext.toInt()) {
                                        iterator.remove()

                                    }
                                    else {
                                        if (!arananotel.contains(yurtdisiTur)) {
                                            arananotel.add(yurtdisiTur)
                                        }
                                    }


                                }
                            }
                        }
                        else
                        {
                            if (arananotel.isEmpty())
                            {
                                val iterator = genelliste.iterator()
                                while (iterator.hasNext()) {
                                    val yurtdisiTur = iterator.next()

                                    if (maxFiyattext != null) {
                                        if (yurtdisiTur.fiyat > maxFiyattext) {
                                            iterator.remove()
                                            // İteratörü güncelledikten sonra iç döngüyü sonlandırın.
                                        }
                                        else {
                                            if (!arananotel.contains(yurtdisiTur)) {
                                                arananotel.add(yurtdisiTur)
                                            }
                                        }
                                    }

                                }
                            }
                            else
                            {
                                val iterator = arananotel.iterator()
                                while (iterator.hasNext()) {
                                    val yurtdisiTur = iterator.next()

                                    if (maxFiyattext != null) {
                                        if (yurtdisiTur.fiyat > maxFiyattext) {
                                            iterator.remove()
                                            // İteratörü güncelledikten sonra iç döngüyü sonlandırın.
                                        }
                                        else {
                                            if (!arananotel.contains(yurtdisiTur)) {
                                                arananotel.add(yurtdisiTur)
                                            }
                                        }
                                    }

                                }
                            }
                        }
                    }
                    binding.toplamtesis.text="Toplam ${arananotel.count()} Otel Tesisi Bulduk!"
                    binding.rchizmetler.apply {
                        layoutManager = LinearLayoutManager(this@OtellerActivity, LinearLayoutManager.VERTICAL,false)
                        adapter = OtellerAdapter(
                            arananotel ,context,this@OtellerActivity)
                        binding.rchizmetler.adapter = adapter
                        binding.rchizmetler.layoutManager=layoutManager
                    }
                    mydialog.dismiss()

                }

                puanrlayoutbtn.setOnClickListener {
                    if (layoutpuan.visibility == View.GONE) {
                        TransitionManager.beginDelayedTransition(cardpuan, AutoTransition())
                        layoutpuan.visibility = View.VISIBLE
                    } else {
                        TransitionManager.beginDelayedTransition(cardpuan, AutoTransition())
                        layoutpuan.visibility = View.GONE
                    }
                }
                fiyatrlayoutbtn.setOnClickListener {
                    if (layoutfiyat.visibility == View.GONE) {
                        TransitionManager.beginDelayedTransition(cardfiyat, AutoTransition())
                        layoutfiyat.visibility = View.VISIBLE
                    } else {
                        TransitionManager.beginDelayedTransition(cardfiyat, AutoTransition())
                        layoutfiyat.visibility = View.GONE
                    }
                }
                tatiltemarlayoutbtn.setOnClickListener {
                    if (layouttatiltema.visibility == View.GONE) {
                        TransitionManager.beginDelayedTransition(cardtatiltema, AutoTransition())
                        layouttatiltema.visibility = View.VISIBLE
                    } else {
                        TransitionManager.beginDelayedTransition(cardtatiltema, AutoTransition())
                        layouttatiltema.visibility = View.GONE
                    }
                }


                cikisbtn.setOnClickListener {
                    mydialog.dismiss()

                }

            }
            binding.siralabtn.setOnClickListener {
                val dialogbinding=layoutInflater.inflate(R.layout.sirala_otel_dialog,null)
                val mydialog= Dialog(this)
                mydialog.setContentView(dialogbinding)
                mydialog.setCancelable(true)
                mydialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                mydialog.show()
                val checkboxfiyatazcok=dialogbinding.findViewById<CheckBox>(R.id.fiyatazcok)
                val checkboxfiyatcokaz=dialogbinding.findViewById<CheckBox>(R.id.fiyatcokaz)
                val checkboxpuanazcok=dialogbinding.findViewById<CheckBox>(R.id.puanazcok)
                val checkboxpuancokaz=dialogbinding.findViewById<CheckBox>(R.id.puancokaz)

                val cikisbtn=dialogbinding.findViewById<ImageView>(R.id.cikis)
                val onaylabtn=dialogbinding.findViewById<CardView>(R.id.siralaonaylabtn)
                cikisbtn.setOnClickListener {
                    mydialog.dismiss()

                }
                onaylabtn.setOnClickListener {
                    val siralachechkboxList = listOf(checkboxfiyatazcok, checkboxfiyatcokaz, checkboxpuanazcok,checkboxpuancokaz)
                    val seciliCheckboxListesisirala = siralachechkboxList.filter { checkbox -> checkbox.isChecked }

                    if (arananotel.isEmpty())
                    {
                        if (seciliCheckboxListesisirala.isNotEmpty())
                        {
                            if (checkboxfiyatcokaz.isChecked)
                            {
                                otelviewListesi.sortByDescending { it.fiyat }

                                binding.rchizmetler.apply {
                                    layoutManager = LinearLayoutManager(this@OtellerActivity, LinearLayoutManager.VERTICAL,false)
                                    adapter = OtellerAdapter(
                                        otelviewListesi,
                                        context,this@OtellerActivity)
                                    binding.rchizmetler.adapter = adapter
                                    binding.rchizmetler.layoutManager=layoutManager
                                }
                            }
                            else if(checkboxfiyatazcok.isChecked)
                            {
                                otelviewListesi.sortBy{ it.fiyat}
                                binding.rchizmetler.apply {
                                    layoutManager = LinearLayoutManager(this@OtellerActivity, LinearLayoutManager.VERTICAL,false)
                                    adapter = OtellerAdapter(
                                        otelviewListesi,
                                        context,this@OtellerActivity)
                                    binding.rchizmetler.adapter = adapter
                                    binding.rchizmetler.layoutManager=layoutManager
                                }
                            }
                            else if(checkboxpuanazcok.isChecked)
                            {
                                otelviewListesi.sortBy{ it.puan}
                                binding.rchizmetler.apply {
                                    layoutManager = LinearLayoutManager(this@OtellerActivity, LinearLayoutManager.VERTICAL,false)
                                    adapter = OtellerAdapter(
                                        otelviewListesi,
                                        context,this@OtellerActivity)
                                    binding.rchizmetler.adapter = adapter
                                    binding.rchizmetler.layoutManager=layoutManager
                                }
                            }
                            else if(checkboxpuancokaz.isChecked)
                            {
                                otelviewListesi.sortByDescending{ it.puan}
                                binding.rchizmetler.apply {
                                    layoutManager = LinearLayoutManager(this@OtellerActivity, LinearLayoutManager.VERTICAL,false)
                                    adapter = OtellerAdapter(
                                        otelviewListesi,
                                        context,this@OtellerActivity)
                                    binding.rchizmetler.adapter = adapter
                                    binding.rchizmetler.layoutManager=layoutManager
                                }
                            }
                        }
                    }
                    else{
                        if (seciliCheckboxListesisirala.isNotEmpty())
                        {
                            if (checkboxfiyatcokaz.isChecked)
                            {
                                arananotel.sortByDescending { it.fiyat }

                                binding.rchizmetler.apply {
                                    layoutManager = LinearLayoutManager(this@OtellerActivity, LinearLayoutManager.VERTICAL,false)
                                    adapter = OtellerAdapter(
                                        arananotel,
                                        context,this@OtellerActivity)
                                    binding.rchizmetler.adapter = adapter
                                    binding.rchizmetler.layoutManager=layoutManager
                                }
                            }
                            else if(checkboxfiyatazcok.isChecked)
                            {
                                arananotel.sortBy{ it.fiyat}
                                binding.rchizmetler.apply {
                                    layoutManager = LinearLayoutManager(this@OtellerActivity, LinearLayoutManager.VERTICAL,false)
                                    adapter = OtellerAdapter(
                                        arananotel,
                                        context,this@OtellerActivity)
                                    binding.rchizmetler.adapter = adapter
                                    binding.rchizmetler.layoutManager=layoutManager
                                }
                            }
                            else if(checkboxpuanazcok.isChecked)
                            {
                                arananotel.sortBy{ it.puan}
                                binding.rchizmetler.apply {
                                    layoutManager = LinearLayoutManager(this@OtellerActivity, LinearLayoutManager.VERTICAL,false)
                                    adapter = OtellerAdapter(
                                        arananotel,
                                        context,this@OtellerActivity)
                                    binding.rchizmetler.adapter = adapter
                                    binding.rchizmetler.layoutManager=layoutManager
                                }
                            }
                            else if(checkboxpuancokaz.isChecked)
                            {
                                arananotel.sortByDescending{ it.puan}
                                binding.rchizmetler.apply {
                                    layoutManager = LinearLayoutManager(this@OtellerActivity, LinearLayoutManager.VERTICAL,false)
                                    adapter = OtellerAdapter(
                                        arananotel,
                                        context,this@OtellerActivity)
                                    binding.rchizmetler.adapter = adapter
                                    binding.rchizmetler.layoutManager=layoutManager
                                }
                            }
                        }
                    }

                    mydialog.dismiss()
                }
            }
        }
        else if(name=="Tekne")
        {
            binding.turadi.text="Tekne Turları"
            binding.toplamtesis.text="Toplam ${tekneturviewListesi.count()} Tekne turu Bulduk!"

            binding.rchizmetler.apply {
                layoutManager = LinearLayoutManager(this@OtellerActivity, LinearLayoutManager.VERTICAL,false)
                adapter = TekneTurAdapter(
                    tekneturviewListesi,
                    context,this@OtellerActivity)
                binding.rchizmetler.adapter = adapter
                binding.rchizmetler.layoutManager=layoutManager
            }
            var aranantekne=ArrayList<TekneTurView>()
            var genelliste=ArrayList<TekneTurView>()

            binding.filtrelebtn.setOnClickListener {
                aranantekne.clear()
                genelliste.addAll(tekneturviewListesi)
                val dialogbinding=layoutInflater.inflate(R.layout.filtrele_tekne_turlari,null)
                val mydialog= Dialog(this)
                mydialog.setContentView(dialogbinding)
                mydialog.setCancelable(true)
                mydialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                mydialog.show()
                val checkboxvizeli=dialogbinding.findViewById<CheckBox>(R.id.checkboxVizeli)
                val checkboxvizesiz=dialogbinding.findViewById<CheckBox>(R.id.checkboxVizesiz)
                val kalkisistanbul=dialogbinding.findViewById<CheckBox>(R.id.istanbulkalkis)
                val kalkisankara=dialogbinding.findViewById<CheckBox>(R.id.Ankarakalkis)
                val kalkisizmir=dialogbinding.findViewById<CheckBox>(R.id.izmirkalkis)
                val minfiyat=dialogbinding.findViewById<EditText>(R.id.minfiyat)
                val maxfiyat=dialogbinding.findViewById<EditText>(R.id.maxfiyat)
                val cikisbtn=dialogbinding.findViewById<ImageView>(R.id.cikisfiltrele)
                val onaylabtn=dialogbinding.findViewById<CardView>(R.id.filtreleonaylabtn)

                val fiyatrlayoutbtn=dialogbinding.findViewById<RelativeLayout>(R.id.fiyatagorefiltbtn)
                val bolgerlayoutbtn=dialogbinding.findViewById<RelativeLayout>(R.id.bolgefiltrebtn)


                val layoutfiyat=dialogbinding.findViewById<LinearLayout>(R.id.layoutfiyataralıgınagore)
                val layoutbolge=dialogbinding.findViewById<LinearLayout>(R.id.layoutbolge)


                val cardfiyat=dialogbinding.findViewById<CardView>(R.id.cardviewfiyataralıgı)
                val cardbolge=dialogbinding.findViewById<CardView>(R.id.cardviewbolge)

                onaylabtn.setOnClickListener {

                    var minFiyattext = minfiyat.text.toString().toIntOrNull()
                    var maxFiyattext = maxfiyat.text.toString().toIntOrNull()

                    val yurtdisicheckboxList = listOf(checkboxvizeli, checkboxvizesiz, kalkisistanbul,kalkisankara,kalkisizmir)
                    val seciliCheckboxListesi = yurtdisicheckboxList.filter { checkbox -> checkbox.isChecked }
                    if(seciliCheckboxListesi.isNotEmpty() )
                    {
                        genelliste.filter { veri ->

                            if (checkboxvizeli.isChecked && veri.vizeDurumu == "Evet")
                            {
                                if (kalkisistanbul.isChecked && veri.baslangicKonumu == "İstanbul")
                                {
                                    if (!aranantekne.contains(veri))
                                    {
                                        aranantekne.add(veri)
                                        true
                                    }

                                }
                                else if (kalkisankara.isChecked && veri.baslangicKonumu == "Ankara")
                                {
                                    if (!aranantekne.contains(veri))
                                    {
                                        aranantekne.add(veri)
                                        true
                                    }

                                }
                                else if (kalkisizmir.isChecked && veri.baslangicKonumu == "İzmir")
                                {
                                    if (!aranantekne.contains(veri))
                                    {
                                        aranantekne.add(veri)
                                        true
                                    }

                                }

                                else{
                                    if (seciliCheckboxListesi.contains(kalkisistanbul) ||seciliCheckboxListesi.contains(kalkisankara)||seciliCheckboxListesi.contains(kalkisizmir) ||seciliCheckboxListesi.contains(checkboxvizesiz))
                                    {

                                    }
                                    else{
                                        if (!aranantekne.contains(veri))
                                        {
                                            aranantekne.add(veri)
                                            true
                                        }
                                    }

                                }

                                true
                            }
                            else if (checkboxvizesiz.isChecked && veri.vizeDurumu == "Hayır")
                            {
                                if(kalkisistanbul.isChecked && veri.baslangicKonumu == "İstanbul")
                                {
                                    if (!aranantekne.contains(veri))
                                    {
                                        aranantekne.add(veri)
                                        true
                                    }


                                }
                                else if (kalkisankara.isChecked && veri.baslangicKonumu == "Ankara")
                                {
                                    if (!aranantekne.contains(veri))
                                    {
                                        aranantekne.add(veri)
                                        true
                                    }


                                }
                                else if (kalkisizmir.isChecked && veri.baslangicKonumu == "İzmir") {
                                    if (!aranantekne.contains(veri))
                                    {
                                        aranantekne.add(veri)
                                        true
                                    }

                                }
                                else{
                                    if (seciliCheckboxListesi.contains(kalkisistanbul) ||seciliCheckboxListesi.contains(kalkisankara)||seciliCheckboxListesi.contains(kalkisizmir) ||seciliCheckboxListesi.contains(checkboxvizeli))
                                    {

                                    }
                                    else{
                                        if (!aranantekne.contains(veri))
                                        {
                                            aranantekne.add(veri)
                                            true
                                        }
                                    }

                                }
                                true
                            }
                            else {
                                if (kalkisistanbul.isChecked && veri.baslangicKonumu == "İstanbul")
                                {
                                    if(checkboxvizeli.isChecked && veri.vizeDurumu == "Evet")
                                    {
                                        if (!aranantekne.contains(veri))
                                        {
                                            aranantekne.add(veri)
                                            true
                                        }
                                    }
                                    else if (checkboxvizesiz.isChecked && veri.vizeDurumu == "Hayır")
                                    {
                                        if (!aranantekne.contains(veri))
                                        {
                                            aranantekne.add(veri)
                                            true
                                        }

                                    }
                                    else if (kalkisankara.isChecked && veri.baslangicKonumu == "Ankara")
                                    {
                                        if (!aranantekne.contains(veri))
                                        {
                                            aranantekne.add(veri)
                                            true
                                        }

                                    }
                                    else if (kalkisizmir.isChecked && veri.baslangicKonumu == "İzmir") {
                                        if (!aranantekne.contains(veri))
                                        {
                                            aranantekne.add(veri)
                                            true
                                        }

                                    }

                                    else{
                                        if (seciliCheckboxListesi.contains(checkboxvizeli) ||seciliCheckboxListesi.contains(checkboxvizesiz) ||seciliCheckboxListesi.contains(kalkisankara)||seciliCheckboxListesi.contains(kalkisizmir))
                                        {

                                        }
                                        else{
                                            if (!aranantekne.contains(veri))
                                            {
                                                aranantekne.add(veri)
                                                true
                                            }
                                        }

                                    }
                                    true
                                }
                                else if (kalkisankara.isChecked && veri.baslangicKonumu == "Ankara")
                                {
                                    if(checkboxvizeli.isChecked && veri.vizeDurumu == "Evet")
                                    {
                                        if (!aranantekne.contains(veri))
                                        {
                                            aranantekne.add(veri)
                                            true
                                        }
                                    }
                                    else if (checkboxvizesiz.isChecked && veri.vizeDurumu == "Hayır")
                                    {
                                        if (!aranantekne.contains(veri))
                                        {
                                            aranantekne.add(veri)
                                            true
                                        }

                                    }
                                    else if (kalkisankara.isChecked && veri.baslangicKonumu == "İstanbul")
                                    {
                                        if (!aranantekne.contains(veri))
                                        {
                                            aranantekne.add(veri)
                                            true
                                        }

                                    }
                                    else if (kalkisizmir.isChecked && veri.baslangicKonumu == "İzmir") {
                                        if (!aranantekne.contains(veri))
                                        {
                                            aranantekne.add(veri)
                                            true
                                        }

                                    }
                                    else{
                                        if (seciliCheckboxListesi.contains(checkboxvizeli) ||seciliCheckboxListesi.contains(checkboxvizesiz) ||seciliCheckboxListesi.contains(kalkisistanbul)||seciliCheckboxListesi.contains(kalkisizmir))
                                        {

                                        }
                                        else{
                                            if (!aranantekne.contains(veri))
                                            {
                                                aranantekne.add(veri)
                                                true
                                            }
                                        }

                                    }
                                    true
                                }
                                else if (kalkisizmir.isChecked && veri.baslangicKonumu == "İzmir")
                                {
                                    if(checkboxvizeli.isChecked && veri.vizeDurumu == "Evet")
                                    {
                                        if (!aranantekne.contains(veri))
                                        {
                                            aranantekne.add(veri)
                                            true
                                        }
                                    }
                                    else if (checkboxvizesiz.isChecked && veri.vizeDurumu == "Hayır")
                                    {
                                        if (!aranantekne.contains(veri))
                                        {
                                            aranantekne.add(veri)
                                            true
                                        }

                                    }
                                    else if (kalkisankara.isChecked && veri.baslangicKonumu == "İstanbul")
                                    {
                                        if (!aranantekne.contains(veri))
                                        {
                                            aranantekne.add(veri)
                                            true
                                        }

                                    }
                                    else if (kalkisizmir.isChecked && veri.baslangicKonumu == "Ankara") {
                                        if (!aranantekne.contains(veri))
                                        {
                                            aranantekne.add(veri)
                                            true
                                        }

                                    }
                                    else{
                                        if (seciliCheckboxListesi.contains(checkboxvizeli) ||seciliCheckboxListesi.contains(checkboxvizesiz) ||seciliCheckboxListesi.contains(kalkisistanbul)||seciliCheckboxListesi.contains(kalkisankara))
                                        {

                                        }
                                        else{
                                            if (!aranantekne.contains(veri))
                                            {
                                                aranantekne.add(veri)
                                                true
                                            }
                                        }

                                    }
                                    true
                                }
                                false
                            }
                        }
                    }

                    if(minFiyattext!=null && maxFiyattext!=null)
                    {
                        if (aranantekne.isNotEmpty())
                        {
                            val iterator = aranantekne.iterator()
                            while (iterator.hasNext()) {
                                val yurtdisiTur = iterator.next()

                                if (minFiyattext != null && maxFiyattext != null) {
                                    if (yurtdisiTur.fiyat >= minFiyattext && yurtdisiTur.fiyat <= maxFiyattext) {
                                        if (!aranantekne.contains(yurtdisiTur)) {
                                            aranantekne.add(yurtdisiTur)
                                        }
                                    } else {
                                        if (aranantekne.contains(yurtdisiTur)) {
                                            iterator.remove()
                                        }
                                    }
                                } else {
                                    println("BOOOOŞŞŞŞŞŞŞŞŞ")
                                }

                            }
                        }
                        else {
                            val iterator = genelliste.iterator()
                            while (iterator.hasNext()) {
                                val yurtdisiTur = iterator.next()

                                    if (yurtdisiTur.fiyat >= minFiyattext && yurtdisiTur.fiyat <= maxFiyattext) {
                                        if (!aranantekne.contains(yurtdisiTur)) {
                                            aranantekne.add(yurtdisiTur)
                                        }
                                    }
                                    else
                                    {
                                        if (aranantekne.contains(yurtdisiTur)) {
                                            iterator.remove()

                                        }
                                    }

                            }
                        }

                    }
                    else if(minFiyattext!=null || maxFiyattext!=null)
                    {
                        if(minFiyattext!=null)
                        {
                            if (aranantekne.isEmpty())
                            {
                                val iterator = genelliste.iterator()

                                while (iterator.hasNext())
                                {
                                    val yurtdisiTur = iterator.next()

                                        if (yurtdisiTur.fiyat <= minFiyattext.toInt()) {
                                            iterator.remove()
                                        }
                                        else {
                                            if (!aranantekne.contains(yurtdisiTur)) {
                                                aranantekne.add(yurtdisiTur)
                                            }
                                        }
                                }
                            }
                            else{
                                val iterator = aranantekne.iterator()
                                while (iterator.hasNext()) {
                                    val yurtdisiTur = iterator.next()

                                        if (yurtdisiTur.fiyat <= minFiyattext.toInt()) {
                                            iterator.remove()

                                        }
                                        else {
                                            if (!aranantekne.contains(yurtdisiTur)) {
                                                aranantekne.add(yurtdisiTur)
                                            }
                                        }


                                }
                            }
                        }
                        else
                        {
                            if (aranantekne.isEmpty())
                            {
                                val iterator = genelliste.iterator()
                                while (iterator.hasNext()) {
                                    val yurtdisiTur = iterator.next()

                                    if (maxFiyattext != null) {
                                        if (yurtdisiTur.fiyat > maxFiyattext) {
                                            iterator.remove()
                                            break // İteratörü güncelledikten sonra iç döngüyü sonlandırın.
                                        }
                                        else {
                                            if (!aranantekne.contains(yurtdisiTur)) {
                                                aranantekne.add(yurtdisiTur)
                                            }
                                        }
                                    }

                                }
                            }
                            else
                            {
                                val iterator = aranantekne.iterator()
                                while (iterator.hasNext()) {
                                    val yurtdisiTur = iterator.next()

                                    if (maxFiyattext != null) {
                                        if (yurtdisiTur.fiyat > maxFiyattext) {
                                            iterator.remove()
                                            // İteratörü güncelledikten sonra iç döngüyü sonlandırın.
                                        }
                                        else {
                                            if (!aranantekne.contains(yurtdisiTur)) {
                                                aranantekne.add(yurtdisiTur)
                                            }
                                        }
                                    }

                                }
                            }
                        }
                    }
                    binding.toplamtesis.text="Toplam ${aranantekne.count()} Tekne turu Bulduk!"
                    binding.rchizmetler.apply {
                        layoutManager = LinearLayoutManager(this@OtellerActivity, LinearLayoutManager.VERTICAL,false)
                        adapter = TekneTurAdapter(
                            aranantekne ,context,this@OtellerActivity)
                        binding.rchizmetler.adapter = adapter
                        binding.rchizmetler.layoutManager=layoutManager
                    }
                    mydialog.dismiss()

                }

                fiyatrlayoutbtn.setOnClickListener {
                    if (layoutfiyat.visibility == View.GONE) {
                        TransitionManager.beginDelayedTransition(cardfiyat, AutoTransition())
                        layoutfiyat.visibility = View.VISIBLE
                    } else {
                        TransitionManager.beginDelayedTransition(cardfiyat, AutoTransition())
                        layoutfiyat.visibility = View.GONE
                    }
                }
                bolgerlayoutbtn.setOnClickListener {
                    if (layoutbolge.visibility == View.GONE) {
                        TransitionManager.beginDelayedTransition(cardbolge, AutoTransition())
                        layoutbolge.visibility = View.VISIBLE
                    } else {
                        TransitionManager.beginDelayedTransition(cardbolge, AutoTransition())
                        layoutbolge.visibility = View.GONE
                    }
                }

                cikisbtn.setOnClickListener {
                    mydialog.dismiss()

                }

            }
            binding.siralabtn.setOnClickListener {
                val dialogbinding=layoutInflater.inflate(R.layout.sirala_otel_dialog,null)
                val mydialog= Dialog(this)
                mydialog.setContentView(dialogbinding)
                mydialog.setCancelable(true)
                mydialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                mydialog.show()
                val checkboxfiyatazcok=dialogbinding.findViewById<CheckBox>(R.id.fiyatazcok)
                val checkboxfiyatcokaz=dialogbinding.findViewById<CheckBox>(R.id.fiyatcokaz)
                val cardpuancokaz=dialogbinding.findViewById<CardView>(R.id.cardpuancokaz)
                val cardpuanazcok=dialogbinding.findViewById<CardView>(R.id.cardpuanazcok)
                cardpuancokaz.visibility=View.GONE
                cardpuanazcok.visibility=View.GONE
                val cikisbtn=dialogbinding.findViewById<ImageView>(R.id.cikis)
                val onaylabtn=dialogbinding.findViewById<CardView>(R.id.siralaonaylabtn)
                cikisbtn.setOnClickListener {
                    mydialog.dismiss()
                }
                onaylabtn.setOnClickListener {
                    val siralachechkboxList = listOf(checkboxfiyatazcok, checkboxfiyatcokaz)
                    val seciliCheckboxListesisirala = siralachechkboxList.filter { checkbox -> checkbox.isChecked }

                    if (aranantekne.isEmpty())
                    {
                        if (seciliCheckboxListesisirala.isNotEmpty())
                        {
                            if (checkboxfiyatcokaz.isChecked)
                            {

                                tekneturviewListesi.sortByDescending { it.fiyat }

                                binding.rchizmetler.apply {
                                    layoutManager = LinearLayoutManager(this@OtellerActivity, LinearLayoutManager.VERTICAL,false)
                                    adapter = TekneTurAdapter(
                                        tekneturviewListesi,
                                        context,this@OtellerActivity)
                                    binding.rchizmetler.adapter = adapter
                                    binding.rchizmetler.layoutManager=layoutManager
                                }
                            }
                            else if(checkboxfiyatazcok.isChecked)
                            {
                                tekneturviewListesi.sortBy { it.fiyat }

                                binding.rchizmetler.apply {
                                    layoutManager = LinearLayoutManager(this@OtellerActivity, LinearLayoutManager.VERTICAL,false)
                                    adapter = TekneTurAdapter(
                                        tekneturviewListesi,
                                        context,this@OtellerActivity)
                                    binding.rchizmetler.adapter = adapter
                                    binding.rchizmetler.layoutManager=layoutManager
                                }
                            }
                        }
                    }
                    else{
                        if (seciliCheckboxListesisirala.isNotEmpty())
                        {
                            if (checkboxfiyatcokaz.isChecked)
                            {
                                aranantekne.sortByDescending { it.fiyat }

                                binding.rchizmetler.apply {
                                    layoutManager = LinearLayoutManager(this@OtellerActivity, LinearLayoutManager.VERTICAL,false)
                                    adapter = TekneTurAdapter(
                                        aranantekne,
                                        context,this@OtellerActivity)
                                    binding.rchizmetler.adapter = adapter
                                    binding.rchizmetler.layoutManager=layoutManager
                                }
                            }
                            else if(checkboxfiyatazcok.isChecked)
                            {
                                aranantekne.sortBy { it.fiyat }

                                binding.rchizmetler.apply {
                                    layoutManager = LinearLayoutManager(this@OtellerActivity, LinearLayoutManager.VERTICAL,false)
                                    adapter = TekneTurAdapter(
                                        aranantekne,
                                        context,this@OtellerActivity)
                                    binding.rchizmetler.adapter = adapter
                                    binding.rchizmetler.layoutManager=layoutManager
                                }
                            }
                        }
                    }

                    mydialog.dismiss()

                }
            }
        }
        else if(name=="YurtDisi")
        {
            binding.turadi.text="Yurt dışı Turları"
            binding.toplamtesis.text="Toplam ${yurtdisiviewListesi.count()} Yurt dışı turu Bulduk!"
            binding.rchizmetler.apply {
                layoutManager = LinearLayoutManager(this@OtellerActivity, LinearLayoutManager.VERTICAL,false)
                adapter = YurtDisiTurAdapter(yurtdisiviewListesi,context,this@OtellerActivity)
                binding.rchizmetler.adapter = adapter
                binding.rchizmetler.layoutManager=layoutManager
            }
            var arananyurtdisi=ArrayList<YurtDisiTurView>()
            var genelliste=ArrayList<YurtDisiTurView>()
            binding.filtrelebtn.setOnClickListener {
                genelliste.addAll(yurtdisiviewListesi)
                val dialogbinding=layoutInflater.inflate(R.layout.filtrele_yurt_disi_dialog,null)
                val mydialog= Dialog(this)
                mydialog.setContentView(dialogbinding)
                mydialog.setCancelable(true)
                mydialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                mydialog.show()
                val checkboxvizeli=dialogbinding.findViewById<CheckBox>(R.id.checkboxVizeli)
                val checkboxvizesiz=dialogbinding.findViewById<CheckBox>(R.id.checkboxVizesiz)
                val kalkisistanbul=dialogbinding.findViewById<CheckBox>(R.id.istanbulkalkis)
                val kalkisankara=dialogbinding.findViewById<CheckBox>(R.id.Ankarakalkis)
                val kalkisizmir=dialogbinding.findViewById<CheckBox>(R.id.izmirkalkis)
                val minfiyat=dialogbinding.findViewById<EditText>(R.id.minfiyat)
                val maxfiyat=dialogbinding.findViewById<EditText>(R.id.maxfiyat)

                val cikisbtn=dialogbinding.findViewById<ImageView>(R.id.cikisfiltrele)
                val onaylabtn=dialogbinding.findViewById<CardView>(R.id.filtreleonaylabtn)
                val fiyatrlayoutbtn=dialogbinding.findViewById<RelativeLayout>(R.id.fiyatagorefiltbtn)
                val bolgerlayoutbtn=dialogbinding.findViewById<RelativeLayout>(R.id.bolgefiltrebtn)
                val kalkisrlayoutbtn=dialogbinding.findViewById<RelativeLayout>(R.id.kalkisnoktasifiltrebtn)
                val layoutfiyat=dialogbinding.findViewById<LinearLayout>(R.id.layoutfiyataralıgınagore)
                val layoutbolge=dialogbinding.findViewById<LinearLayout>(R.id.layoutbolge)
                val layoutkalkisnoktasi=dialogbinding.findViewById<LinearLayout>(R.id.layoutkalkisnoktasi)
                val cardfiyat=dialogbinding.findViewById<CardView>(R.id.cardviewfiyataralıgı)
                val cardbolge=dialogbinding.findViewById<CardView>(R.id.cardviewbolge)
                val cardkalkisnoktasi=dialogbinding.findViewById<CardView>(R.id.cardviewkalkisnoktasi)


                onaylabtn.setOnClickListener {

                    val minFiyattext = minfiyat.text.toString().toIntOrNull()
                    val maxFiyattext = maxfiyat.text.toString().toIntOrNull()

                    val yurtdisicheckboxList = listOf(checkboxvizeli, checkboxvizesiz, kalkisistanbul,kalkisankara,kalkisizmir)
                    val seciliCheckboxListesi = yurtdisicheckboxList.filter { checkbox -> checkbox.isChecked }
                    if(seciliCheckboxListesi.isNotEmpty() )
                    {
                        genelliste.filter { veri ->

                            if (checkboxvizeli.isChecked && veri.vizeDurumu == "Evet")
                            {
                                if (kalkisistanbul.isChecked && veri.baslangicKonumu == "İstanbul")
                                {
                                    if (!arananyurtdisi.contains(veri))
                                    {
                                        arananyurtdisi.add(veri)
                                        true
                                    }

                                }
                                else if (kalkisankara.isChecked && veri.baslangicKonumu == "Ankara")
                                {
                                    if (!arananyurtdisi.contains(veri))
                                    {
                                        arananyurtdisi.add(veri)
                                        true
                                    }

                                }
                                else if (kalkisizmir.isChecked && veri.baslangicKonumu == "İzmir")
                                {
                                    if (!arananyurtdisi.contains(veri))
                                    {
                                        arananyurtdisi.add(veri)
                                        true
                                    }

                                }

                                else{
                                    if (seciliCheckboxListesi.contains(kalkisistanbul) ||seciliCheckboxListesi.contains(kalkisankara)||seciliCheckboxListesi.contains(kalkisizmir) ||seciliCheckboxListesi.contains(checkboxvizesiz))
                                    {

                                    }
                                    else{
                                        if (!arananyurtdisi.contains(veri))
                                        {
                                            arananyurtdisi.add(veri)
                                            true
                                        }
                                    }

                                }

                                true
                            }
                            else if (checkboxvizesiz.isChecked && veri.vizeDurumu == "Hayır")
                             {
                                if(kalkisistanbul.isChecked && veri.baslangicKonumu == "İstanbul")
                                {
                                    if (!arananyurtdisi.contains(veri))
                                    {
                                        arananyurtdisi.add(veri)
                                        true
                                    }

                                }
                                else if (kalkisankara.isChecked && veri.baslangicKonumu == "Ankara")
                                {
                                    if (!arananyurtdisi.contains(veri))
                                    {
                                        arananyurtdisi.add(veri)
                                        true
                                    }
                                }
                                else if (kalkisizmir.isChecked && veri.baslangicKonumu == "İzmir") {
                                    if (!arananyurtdisi.contains(veri))
                                    {
                                        arananyurtdisi.add(veri)
                                        true
                                    }
                                }
                                else{
                                    if (seciliCheckboxListesi.contains(kalkisistanbul) ||seciliCheckboxListesi.contains(kalkisankara)||seciliCheckboxListesi.contains(kalkisizmir) ||seciliCheckboxListesi.contains(checkboxvizeli))
                                    {

                                    }
                                    else{
                                        if (!arananyurtdisi.contains(veri))
                                        {
                                            arananyurtdisi.add(veri)
                                            true
                                        }
                                    }

                                }
                                true
                            }
                            else {
                                 if (kalkisistanbul.isChecked && veri.baslangicKonumu == "İstanbul")
                                 {
                                    if(checkboxvizeli.isChecked && veri.vizeDurumu == "Evet")
                                    {
                                        if (!arananyurtdisi.contains(veri))
                                        {
                                            arananyurtdisi.add(veri)
                                            true
                                        }
                                    }
                                    else if (checkboxvizesiz.isChecked && veri.vizeDurumu == "Hayır")
                                    {
                                        if (!arananyurtdisi.contains(veri))
                                        {
                                            arananyurtdisi.add(veri)
                                            true
                                        }

                                    }
                                    else if (kalkisankara.isChecked && veri.baslangicKonumu == "Ankara")
                                    {
                                        if (!arananyurtdisi.contains(veri))
                                        {
                                            arananyurtdisi.add(veri)
                                            true
                                        }
                                    }

                                    }
                                    else if (kalkisizmir.isChecked && veri.baslangicKonumu == "İzmir") {
                                        if (!arananyurtdisi.contains(veri))
                                        {
                                        arananyurtdisi.add(veri)
                                        true
                                        }
                                    }
                                 else if (kalkisankara.isChecked && veri.baslangicKonumu == "Ankara")
                                 {
                                     if(checkboxvizeli.isChecked && veri.vizeDurumu == "Evet")
                                     {
                                         if (!arananyurtdisi.contains(veri))
                                         {
                                             arananyurtdisi.add(veri)
                                             true
                                         }
                                     }
                                     else if (checkboxvizesiz.isChecked && veri.vizeDurumu == "Hayır")
                                     {
                                         if (!arananyurtdisi.contains(veri))
                                         {
                                             arananyurtdisi.add(veri)
                                             true
                                         }

                                     }
                                     else if (kalkisankara.isChecked && veri.baslangicKonumu == "İstanbul")
                                     {
                                         if (!arananyurtdisi.contains(veri))
                                         {
                                             arananyurtdisi.add(veri)
                                             true
                                         }

                                     }
                                     else if (kalkisizmir.isChecked && veri.baslangicKonumu == "İzmir") {
                                         if (!arananyurtdisi.contains(veri))
                                         {
                                             arananyurtdisi.add(veri)
                                             true
                                         }

                                     }
                                     else{
                                         if (seciliCheckboxListesi.contains(checkboxvizeli) ||seciliCheckboxListesi.contains(checkboxvizesiz) ||seciliCheckboxListesi.contains(kalkisistanbul)||seciliCheckboxListesi.contains(kalkisizmir))
                                         {

                                         }
                                         else{
                                             if (!arananyurtdisi.contains(veri))
                                             {
                                                 arananyurtdisi.add(veri)
                                                 true
                                             }
                                         }

                                     }
                                     true
                                 }
                                 else if (kalkisizmir.isChecked && veri.baslangicKonumu == "İzmir")
                                 {
                                     if(checkboxvizeli.isChecked && veri.vizeDurumu == "Evet")
                                     {
                                         if (!arananyurtdisi.contains(veri))
                                         {
                                             arananyurtdisi.add(veri)
                                             true
                                         }
                                     }
                                     else if (checkboxvizesiz.isChecked && veri.vizeDurumu == "Hayır")
                                     {
                                         if (!arananyurtdisi.contains(veri))
                                         {
                                             arananyurtdisi.add(veri)
                                             true
                                         }

                                     }
                                     else if (kalkisankara.isChecked && veri.baslangicKonumu == "İstanbul")
                                     {
                                         if (!arananyurtdisi.contains(veri))
                                         {
                                             arananyurtdisi.add(veri)
                                             true
                                         }

                                     }
                                     else if (kalkisizmir.isChecked && veri.baslangicKonumu == "Ankara") {
                                         if (!arananyurtdisi.contains(veri))
                                         {
                                             arananyurtdisi.add(veri)
                                             true
                                         }

                                     }
                                     else{
                                         if (seciliCheckboxListesi.contains(checkboxvizeli) ||seciliCheckboxListesi.contains(checkboxvizesiz) ||seciliCheckboxListesi.contains(kalkisistanbul)||seciliCheckboxListesi.contains(kalkisankara))
                                         {

                                         }
                                         else{
                                             if (!arananyurtdisi.contains(veri))
                                             {
                                                 arananyurtdisi.add(veri)
                                                 true
                                             }
                                         }

                                     }
                                     true
                                 }
                                    else{
                                        if (seciliCheckboxListesi.contains(checkboxvizeli) ||seciliCheckboxListesi.contains(checkboxvizesiz) ||seciliCheckboxListesi.contains(kalkisankara)||seciliCheckboxListesi.contains(kalkisizmir))
                                        {

                                        }
                                        else{
                                            if (!arananyurtdisi.contains(veri))
                                            {
                                                arananyurtdisi.add(veri)
                                                true
                                            }
                                        }

                                    }
                                    true
                                }

                                false
                            }
                        }
                    if(minFiyattext!=null && maxFiyattext!=null)
                    {
                        if (arananyurtdisi.isNotEmpty())
                        {
                            val iterator = arananyurtdisi.iterator()

                            while (iterator.hasNext()) {
                                val yurtdisiTur = iterator.next()

                                if (minFiyattext != null && maxFiyattext != null) {
                                    if (yurtdisiTur.fiyat >= minFiyattext && yurtdisiTur.fiyat <= maxFiyattext) {
                                        if (!arananyurtdisi.contains(yurtdisiTur)) {
                                            arananyurtdisi.add(yurtdisiTur)
                                        }
                                    } else {
                                        if (arananyurtdisi.contains(yurtdisiTur)) {
                                            iterator.remove()
                                        }
                                    }
                                } else {
                                    println("BOOOOŞŞŞŞŞŞŞŞŞ")
                                }

                            }
                        }
                        else {
                            val iterator = genelliste.iterator()
                            while (iterator.hasNext()) {
                                val yurtdisiTur = iterator.next()

                                if (minFiyattext != null && maxFiyattext != null)
                                {
                                    if (yurtdisiTur.fiyat >= minFiyattext && yurtdisiTur.fiyat <= maxFiyattext) {
                                        if (!arananyurtdisi.contains(yurtdisiTur)) {
                                            arananyurtdisi.add(yurtdisiTur)
                                        }
                                    }
                                    else
                                    {
                                        if (arananyurtdisi.contains(yurtdisiTur)) {
                                            iterator.remove()

                                        }
                                    }
                                } else {
                                    println("BOOOOŞŞŞŞŞŞŞŞŞ")
                                }

                            }
                        }

                    }
                    else if(minFiyattext!=null || maxFiyattext!=null)
                    {
                        if(minFiyattext!=null)
                        {
                            if (arananyurtdisi.isEmpty())
                            {
                                val iterator = genelliste.iterator()
                                while (iterator.hasNext()) {
                                    val yurtdisiTur = iterator.next()

                                        if (yurtdisiTur.fiyat <= minFiyattext) {
                                            iterator.remove()

                                        }
                                        else {
                                            if (!arananyurtdisi.contains(yurtdisiTur)) {
                                                arananyurtdisi.add(yurtdisiTur)
                                            }
                                        }

                                }
                            }
                            else{
                                val iterator = arananyurtdisi.iterator()
                                while (iterator.hasNext()) {
                                    val yurtdisiTur = iterator.next()


                                        if (yurtdisiTur.fiyat <= minFiyattext) {
                                            iterator.remove()
                                            // İteratörü güncelledikten sonra iç döngüyü sonlandırın.
                                        }
                                        else {
                                            if (!arananyurtdisi.contains(yurtdisiTur)) {
                                                arananyurtdisi.add(yurtdisiTur)
                                            }
                                        }


                                }
                            }
                        }
                        else
                        {
                            if (arananyurtdisi.isEmpty())
                            {
                                val iterator = genelliste.iterator()
                                while (iterator.hasNext()) {
                                    val yurtdisiTur = iterator.next()

                                    if (maxFiyattext != null) {
                                        if (yurtdisiTur.fiyat > maxFiyattext) {
                                            iterator.remove()

                                        }
                                        else {
                                            if (!arananyurtdisi.contains(yurtdisiTur)) {
                                                arananyurtdisi.add(yurtdisiTur)
                                            }
                                        }
                                    }

                                }
                            }
                            else
                            {
                                val iterator = arananyurtdisi.iterator()
                                while (iterator.hasNext()) {
                                    val yurtdisiTur = iterator.next()

                                    if (maxFiyattext != null) {
                                        if (yurtdisiTur.fiyat > maxFiyattext) {
                                            iterator.remove()

                                        }
                                        else {
                                            if (!arananyurtdisi.contains(yurtdisiTur)) {
                                                arananyurtdisi.add(yurtdisiTur)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    binding.toplamtesis.text="Toplam ${arananyurtdisi.count()} Yurt dışı turu Bulduk!"
                    binding.rchizmetler.apply {
                        layoutManager = LinearLayoutManager(this@OtellerActivity, LinearLayoutManager.VERTICAL,false)
                        adapter = YurtDisiTurAdapter(
                            arananyurtdisi ,context,this@OtellerActivity)
                        binding.rchizmetler.adapter = adapter
                        binding.rchizmetler.layoutManager=layoutManager
                    }
                    mydialog.dismiss()
                    }


                kalkisrlayoutbtn.setOnClickListener {
                    if (layoutkalkisnoktasi.visibility == View.GONE) {
                        TransitionManager.beginDelayedTransition(cardkalkisnoktasi, AutoTransition())
                        layoutkalkisnoktasi.visibility = View.VISIBLE
                    } else {
                        TransitionManager.beginDelayedTransition(cardkalkisnoktasi, AutoTransition())
                        layoutkalkisnoktasi.visibility = View.GONE
                    }
                }

                fiyatrlayoutbtn.setOnClickListener {
                    if (layoutfiyat.visibility == View.GONE) {
                        TransitionManager.beginDelayedTransition(cardfiyat, AutoTransition())
                        layoutfiyat.visibility = View.VISIBLE
                    } else {
                        TransitionManager.beginDelayedTransition(cardfiyat, AutoTransition())
                        layoutfiyat.visibility = View.GONE
                    }
                }
                bolgerlayoutbtn.setOnClickListener {
                    if (layoutbolge.visibility == View.GONE) {
                        TransitionManager.beginDelayedTransition(cardbolge, AutoTransition())
                        layoutbolge.visibility = View.VISIBLE
                    } else {
                        TransitionManager.beginDelayedTransition(cardbolge, AutoTransition())
                        layoutbolge.visibility = View.GONE
                    }
                }
                cikisbtn.setOnClickListener {
                    mydialog.dismiss()
                }
                }
            binding.siralabtn.setOnClickListener {
                val dialogbinding=layoutInflater.inflate(R.layout.sirala_otel_dialog,null)
                val mydialog= Dialog(this)
                mydialog.setContentView(dialogbinding)
                mydialog.setCancelable(true)
                mydialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                mydialog.show()
                val checkboxfiyatazcok=dialogbinding.findViewById<CheckBox>(R.id.fiyatazcok)
                val checkboxfiyatcokaz=dialogbinding.findViewById<CheckBox>(R.id.fiyatcokaz)
                val cardpuancokaz=dialogbinding.findViewById<CardView>(R.id.cardpuancokaz)
                val cardpuanazcok=dialogbinding.findViewById<CardView>(R.id.cardpuanazcok)
                cardpuancokaz.visibility=View.GONE
                cardpuanazcok.visibility=View.GONE
                val cikisbtn=dialogbinding.findViewById<ImageView>(R.id.cikis)
                val onaylabtn=dialogbinding.findViewById<CardView>(R.id.siralaonaylabtn)
                cikisbtn.setOnClickListener {
                    mydialog.dismiss()

                }
                onaylabtn.setOnClickListener {
                    val siralachechkboxList = listOf(checkboxfiyatazcok, checkboxfiyatcokaz)
                    val seciliCheckboxListesisirala = siralachechkboxList.filter { checkbox -> checkbox.isChecked }
                    if (arananyurtdisi.isEmpty())
                    {
                        if (seciliCheckboxListesisirala.isNotEmpty())
                        {
                            if (checkboxfiyatcokaz.isChecked)
                            {

                                yurtdisiviewListesi.sortByDescending { it.fiyat }

                                binding.rchizmetler.apply {
                                    layoutManager = LinearLayoutManager(this@OtellerActivity, LinearLayoutManager.VERTICAL,false)
                                    adapter = YurtDisiTurAdapter(
                                        yurtdisiviewListesi,
                                        context,this@OtellerActivity)
                                    binding.rchizmetler.adapter = adapter
                                    binding.rchizmetler.layoutManager=layoutManager
                                }
                            }
                            else if(checkboxfiyatazcok.isChecked)
                            {

                                yurtdisiviewListesi.sortBy{ it.fiyat}

                                binding.rchizmetler.apply {
                                    layoutManager = LinearLayoutManager(this@OtellerActivity, LinearLayoutManager.VERTICAL,false)
                                    adapter = YurtDisiTurAdapter(
                                        yurtdisiviewListesi,
                                        context,this@OtellerActivity)
                                    binding.rchizmetler.adapter = adapter
                                    binding.rchizmetler.layoutManager=layoutManager
                                }
                            }
                        }
                    }
                    else{
                        if (seciliCheckboxListesisirala.isNotEmpty())
                        {
                            if (checkboxfiyatcokaz.isChecked)
                            {
                                arananyurtdisi.sortByDescending { it.fiyat }
                                binding.rchizmetler.apply {
                                    layoutManager = LinearLayoutManager(this@OtellerActivity, LinearLayoutManager.VERTICAL,false)
                                    adapter = YurtDisiTurAdapter(
                                        arananyurtdisi,
                                        context,this@OtellerActivity)
                                    binding.rchizmetler.adapter = adapter
                                    binding.rchizmetler.layoutManager=layoutManager
                                }
                            }
                            else if(checkboxfiyatazcok.isChecked)
                            {

                                arananyurtdisi.sortBy{ it.fiyat}

                                binding.rchizmetler.apply {
                                    layoutManager = LinearLayoutManager(this@OtellerActivity, LinearLayoutManager.VERTICAL,false)
                                    adapter = YurtDisiTurAdapter(
                                        arananyurtdisi,
                                        context,this@OtellerActivity)
                                    binding.rchizmetler.adapter = adapter
                                    binding.rchizmetler.layoutManager=layoutManager
                                }
                            }
                        }
                    }
                    mydialog.dismiss()
                }
            }


            }

        if (pop!=null)
        {
            popotelviewListesi.clear()
            for (otel in otelviewListesi)
            {
                if(otel.adres.contains(pop.sehiradi))
                {
                    popotelviewListesi.add(otel)
                }
            }
            binding.turadi.text="Oteller"
            binding.toplamtesis.text="Toplam ${popotelviewListesi.count()} Otel Tesisi Bulduk!"

            binding.rchizmetler.apply {
                layoutManager = LinearLayoutManager(this@OtellerActivity, LinearLayoutManager.VERTICAL,false)
                adapter = OtellerAdapter(
                    popotelviewListesi,
                    context,this@OtellerActivity)
                binding.rchizmetler.adapter = adapter
                binding.rchizmetler.layoutManager=layoutManager
            }
        }

        if (tema!=null)
        {
            popotelviewListesi.clear()
            for (otel in otelviewListesi)
            {
                if(otel.tema.contains(tema.temaadi))
                {
                    popotelviewListesi.add(otel)
                }
            }
            binding.turadi.text="Oteller"
            binding.toplamtesis.text="Toplam ${popotelviewListesi.count()} Otel Tesisi Bulduk!"

            binding.rchizmetler.apply {
                layoutManager = LinearLayoutManager(this@OtellerActivity, LinearLayoutManager.VERTICAL,false)
                adapter = OtellerAdapter(
                    popotelviewListesi,
                    context,this@OtellerActivity)
                binding.rchizmetler.adapter = adapter
                binding.rchizmetler.layoutManager=layoutManager
            }
        }

        }


    override fun onclickydt(ydt: YurtDisiTurView) {
        val intent = Intent(applicationContext, YurtdisidetayActivity::class.java)
        intent.putExtra(YDT_ID_EXTRA, ydt.idYurtdisiTur)
        startActivity(intent)
    }

    override fun onclickteknetur(ttur: TekneTurView) {
        val intent = Intent(applicationContext, TekneTurlariActivity::class.java)
        intent.putExtra(TT_ID_EXTRA, ttur.idTekneTur)
        startActivity(intent)
    }

    override fun onclickotel(otel: OtelView) {
        val intent = Intent(applicationContext, OteldetayActivity::class.java)
        intent.putExtra(OTEL_ID_EXTRA, otel.idOtel.toString())
        startActivity(intent)
    }

    private fun POPID(popId: String): PopulerBolgeler? {
        for (pop in populerBolgelerArrayList) {
            if (pop.sehiradi == popId)
                return pop
        }
        return null
    }
    private fun temaID(temaId: String): TatilTemalari? {
        for (tema in tatiltemalariArrayList) {
            if (tema.temaadi == temaId)
                return tema
        }
        return null
    }
}




