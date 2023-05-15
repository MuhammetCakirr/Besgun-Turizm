package com.muhammetcakir.turizmacentasi


import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

import androidx.recyclerview.widget.LinearLayoutManager

import com.muhammetcakir.turizmacentasi.Adapters.PopulerBolgeAdapters
import com.muhammetcakir.turizmacentasi.Adapters.TatilTemalariAdapters
import com.muhammetcakir.turizmacentasi.ClickListener.Clickpopulerbolgeler
import com.muhammetcakir.turizmacentasi.ClickListener.Clicktema
import com.muhammetcakir.turizmacentasi.Database.DbServices
import com.muhammetcakir.turizmacentasi.Database.musteriListesi

import com.muhammetcakir.turizmacentasi.Models.*
import com.muhammetcakir.turizmacentasi.Views.*
import com.muhammetcakir.turizmacentasi.databinding.ActivityMainBinding
import id.ionbit.ionalert.IonAlert
import kotlinx.coroutines.*

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


var otelviewListesi=ArrayList<OtelView>()

var popotelviewListesi=ArrayList<OtelView>()
var yurtdisiviewListesi=ArrayList<YurtDisiTurView>()
var tekneturviewListesi=ArrayList<TekneTurView>()
var kullanankisi=ArrayList<Musteri>()
val populerBolgelerArrayList: ArrayList<PopulerBolgeler> = ArrayList()
val tatiltemalariArrayList: ArrayList<TatilTemalari> = ArrayList()


val YDT_ID_EXTRA = "ydtextra"
val TT_ID_EXTRA = "ttextra"
val TP_ID_EXTRA = "turprogramiextra"
val OTEL_ID_EXTRA = "otelextra"
val ODA_EXTRA = "odaextra"
val REZ_ID_EXRTRA="rezextra"
val POP_ID_EXRTRA="popextra"
val TEMA_ID_EXRTRA="temaextra"

var suankullanicivarmi = false

class MainActivity : AppCompatActivity(),Clickpopulerbolgeler,Clicktema {

    private lateinit var binding: ActivityMainBinding
    var yetiskinsayi2 = 1
    var cocuksayi2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        runBlocking {

            val db=DbServices()

            yurtdisiviewListesi.clear()
            val deferred4 = async {
                db.getYurtdisiturView()
            }
            val yeniyurtdisiviewListesi = deferred4.await()
            yurtdisiviewListesi.addAll(yeniyurtdisiviewListesi)

            tekneturviewListesi.clear()
            val deferred5 = async {
                db.getTekneturView()
            }
            val yenitekneturviewListesi = deferred5.await()
            tekneturviewListesi.addAll(yenitekneturviewListesi)

            otelviewListesi.clear()
            val deferred6 = async {
                db.getOtelView()
            }
            val yeniotelviewListesi = deferred6.await()
            otelviewListesi.addAll(yeniotelviewListesi)

        }
        if (populerBolgelerArrayList.isEmpty())
        {
            populerbolgeekle()
        }
        if (tatiltemalariArrayList.isEmpty())
        {
            tatiltemasiekle()
        }
        binding.rcpopulerbolgeler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL,false)
            adapter = PopulerBolgeAdapters(populerBolgelerArrayList,this@MainActivity)
            binding.rcpopulerbolgeler.adapter = adapter
            binding.rcpopulerbolgeler.layoutManager=layoutManager
        }

        binding.rctatiltemalari.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL,false)
            adapter = TatilTemalariAdapters(tatiltemalariArrayList,this@MainActivity)
            binding.rctatiltemalari.adapter = adapter
            binding.rctatiltemalari.layoutManager=layoutManager
        }
        runBlocking {
            musteriListesi.clear()
            val db= DbServices()
            val deferred = async {
                db.getMusteri()
            }
            val yenimusteriListesi = deferred.await()
            musteriListesi.addAll(yenimusteriListesi)
        }
        if (suankullanicivarmi==true)
        {
            if(suankikullanicieposta=="1")
            {

            }
            else{
                kullanankisi.clear()
                for(musteri in musteriListesi)
                {
                    if (musteri.epostaAdresi.toString()== suankikullanicieposta.toString())
                    {
                        kullanankisi.add(musteri)
                    }
                }

            }
        }

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
        binding.hizmetlerimizbtn.setOnClickListener {
            if (binding.layout.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(binding.cardview, AutoTransition())
                binding.layout.visibility = View.VISIBLE
            } else {
                TransitionManager.beginDelayedTransition(binding.cardview, AutoTransition())
                binding.layout.visibility = View.GONE
            }
        }
        binding.populerbolgelerbtn.setOnClickListener {
            if (binding.layout2.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(binding.cardview2, AutoTransition())
                binding.layout2.visibility = View.VISIBLE
            } else {
                TransitionManager.beginDelayedTransition(binding.cardview2, AutoTransition())
                binding.layout2.visibility = View.GONE
            }
        }
        binding.tatiltemalaribtn.setOnClickListener {
            if (binding.layout3.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(binding.cardview3, AutoTransition())
                binding.layout3.visibility = View.VISIBLE
            } else {
                TransitionManager.beginDelayedTransition(binding.cardview3, AutoTransition())
                binding.layout3.visibility = View.GONE
            }
        }

        binding.otelarabtn.setOnClickListener {
            val intent = Intent(this, OtellerActivity::class.java)
            intent.putExtra("Name","Otel")
            startActivity(intent)
            finish()
        }

        binding.yurtdisilayout.setOnClickListener {
            val intent = Intent(this, OtellerActivity::class.java)
            intent.putExtra("Name","YurtDisi")
            startActivity(intent)
            finish()
        }
        binding.gemilayout.setOnClickListener {
            val intent = Intent(this, OtellerActivity::class.java)
            intent.putExtra("Name","Tekne")
            startActivity(intent)
            finish()
        }
        binding.otellayout.setOnClickListener {
            val intent = Intent(this, OtellerActivity::class.java)
            intent.putExtra("Name","Otel")
            startActivity(intent)
            finish()
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
                binding.kisisayisi.text=(yetiskinsayi.text.toString() +" Yetişkin,"+cocuksayi.text.toString()+" Çocuk")
                binding.kisisayisi.visibility=View.VISIBLE
            }

        }
        binding.bottomNavigation.setSelectedItemId(R.id.anasayfa)
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.getItemId()) {
                R.id.anasayfa-> return@setOnItemSelectedListener true
                R.id.reserve -> {
                    startActivity(Intent(applicationContext, OtellerActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
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
    }

    fun populerbolgeekle()
    {
        val alanya=PopulerBolgeler(
            "Alanya",
            R.drawable.alanya
        )
        populerBolgelerArrayList.add(alanya)
        val alacati=PopulerBolgeler(
            "Alaçatı",
            R.drawable.alacati
        )
        populerBolgelerArrayList.add(alacati)
        val bodrum=PopulerBolgeler(
            "Bodrum",
            R.drawable.bodrum
        )
        populerBolgelerArrayList.add(bodrum)
        val canakkale=PopulerBolgeler(
            "Çanakkale",
            R.drawable.canakkale
        )
        populerBolgelerArrayList.add(canakkale)
        val didim=PopulerBolgeler(
            "Didim",
            R.drawable.didim
        )
        populerBolgelerArrayList.add(didim)
        val fethiye=PopulerBolgeler(
            "Fethiye",
            R.drawable.fethiye
        )
        populerBolgelerArrayList.add(fethiye)
        val kapadokya=PopulerBolgeler(
            "Kapadokya",
            R.drawable.kapodokya
        )
        populerBolgelerArrayList.add(kapadokya)
        val kusadasi=PopulerBolgeler(
            "Kuşadası",
            R.drawable.kusadasi
        )
        populerBolgelerArrayList.add(kusadasi)
        val pamukkale=PopulerBolgeler(
            "Pamukkale",
            R.drawable.pamukkale
        )
        populerBolgelerArrayList.add(pamukkale)
    }

    fun tatiltemasiekle()
    {
        var besyildizlioteller=TatilTemalari(
            "Termal Otel",
            R.drawable.besyildizlioteller
        )
        tatiltemalariArrayList.add(besyildizlioteller)

        var balayi=TatilTemalari(
            "Balayı Otel",
            R.drawable.balayiotelleri
        )
        tatiltemalariArrayList.add(balayi)

        var denizli=TatilTemalari(
            "Butik Otel",
            R.drawable.denizli
        )
        tatiltemalariArrayList.add(denizli)

        var tarihi=TatilTemalari(
            "Şehir Otel",
            R.drawable.tarihi
        )
        tatiltemalariArrayList.add(tarihi)

        var tekne=TatilTemalari(
            "Tatil Köyü",
            R.drawable.tekneturu
        )
        tatiltemalariArrayList.add(tekne)

    }
    private fun updateLable(mycalender: Calendar) {
        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale("tr"))
        binding.giristarihi.text = sdf.format(mycalender.time)
        binding.giristarihi.visibility=View.VISIBLE
    }
    private fun updateLable2(mycalender2: Calendar) {
        val sdf2 = SimpleDateFormat("dd MMMM yyyy", Locale("tr"))
        binding.cikistarihi.text = sdf2.format(mycalender2.time)
        binding.cikistarihi.visibility=View.VISIBLE
    }

    override fun onclickpopuler(populerBolgeler: PopulerBolgeler) {
        val intent = Intent(applicationContext, OtellerActivity::class.java)
        intent.putExtra(POP_ID_EXRTRA, populerBolgeler.sehiradi)
        startActivity(intent)
    }

    override fun onclicktema(tatilTemalari: TatilTemalari) {
        val intent = Intent(applicationContext, OtellerActivity::class.java)
        intent.putExtra(TEMA_ID_EXRTRA, tatilTemalari.temaadi)
        startActivity(intent)
    }
}
data class ApiResponse(
    val error: Boolean,
    val otel: List<Otel>
)

data class Otel(
    val idOtel: Int,
    val adi: String,
    val adres: String,
    val resim: String
)





