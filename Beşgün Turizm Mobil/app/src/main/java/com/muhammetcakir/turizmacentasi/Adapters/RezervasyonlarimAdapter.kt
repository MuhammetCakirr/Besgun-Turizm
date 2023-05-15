package com.muhammetcakir.turizmacentasi.Adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.muhammetcakir.turizmacentasi.*
import com.muhammetcakir.turizmacentasi.ClickListener.Clickrezervasyon
import com.muhammetcakir.turizmacentasi.ClickListener.Clicktotel
import com.muhammetcakir.turizmacentasi.Database.*
import com.muhammetcakir.turizmacentasi.Models.HizmetBilgisi
import com.muhammetcakir.turizmacentasi.Models.Otel
import com.muhammetcakir.turizmacentasi.Models.Rezervasyon
import com.muhammetcakir.turizmacentasi.databinding.OtelKutucaklariBinding
import com.muhammetcakir.turizmacentasi.databinding.RezervasyonKutucuklariBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.collections.ArrayList


class RezervasyonlarimAdapter(
    var rezervasyonlarimlist: ArrayList<Rezervasyon>,
    var clickrez: Clickrezervasyon
) : RecyclerView.Adapter<RezervasyonlarimAdapter.CardViewHolder>() {
    class CardViewHolder(
        private val cardCellBinding: RezervasyonKutucuklariBinding,
        var rezervasyonlarimlist: ArrayList<Rezervasyon>,
        var clickrez: Clickrezervasyon
    ) : RecyclerView.ViewHolder(cardCellBinding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun find(rezervasyon: Rezervasyon) {
            runBlocking {
                hizmetListesi.clear()
                val db = DbServices()
                val deferred = async {
                    db.getHizmet(rezervasyon.Hizmet_idHizmet.toString())
                }
                val yenihizmetListesi = deferred.await()
                hizmetListesi.addAll(yenihizmetListesi)
            }
            if (hizmetListesi[0].hizmetTabloAdi == "yurtdisitur")
            {
                for (ydt in yurtdisiviewListesi) {
                    if (hizmetListesi[0].hizmetTabloId.toString() == ydt.idYurtdisiTur.toString()) {
                        cardCellBinding.duzenlebtn.setOnClickListener {
                            clickrez.onclickrezervasyon(rezervasyon)
                        }
                        cardCellBinding.turadi.text = ydt.adi.toString()
                        cardCellBinding.rezkisisayisi.text =
                            "${rezervasyon.yetiskinSayisi.toString()} Yetişkin,${rezervasyon.cocukSayisi.toString()} Çocuk"
                        cardCellBinding.musteriadsoyad.text =
                            kullanankisi[0].adi.toString() + " " + kullanankisi[0].soyadi.toString()
                        cardCellBinding.telno.text = kullanankisi[0].telefonNumarasi.toString()
                        cardCellBinding.guzergah.text = "${ydt.baslangicKonumu}-->${ydt.guzergah}"
                        if (ydt.vizeDurumu == "Evet")
                            cardCellBinding.vize.text = "Vizeli"
                        else
                            cardCellBinding.vize.text = "Vizesiz"

                        val formatter =
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale("tr"))
                        val giristarihi = LocalDate.parse(ydt.baslangicTarihi.toString(), formatter)

                        val gelecekTarih = giristarihi.plusDays(5)
                        val cikistarihiFormatter =
                            DateTimeFormatter.ofPattern("dd LLLL yyyy", Locale("tr"))
                        val cikistarihi = gelecekTarih.format(cikistarihiFormatter)

                        val reztarihi = LocalDate.parse(rezervasyon.tarihi.toString(), formatter)
                        val dateFormatter =
                            DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("tr"))
                        val tarihString = reztarihi.format(dateFormatter)

                        val giristarihi2 =
                            LocalDate.parse(ydt.baslangicTarihi.toString(), formatter)
                        val giristring = giristarihi2.format(dateFormatter)
                        cardCellBinding.reztarihi.text = tarihString.toString()
                        cardCellBinding.giristarih.text = giristring.toString()
                        cardCellBinding.cikistarih.text = cikistarihi.toString()
                        Picasso.get().load(ydt.resimUrl).into(cardCellBinding.turfoto)

                        var yetiskinSayisi = rezervasyon.yetiskinSayisi
                        var cocukSayisi = rezervasyon.cocukSayisi
                        val fiyat = ydt.fiyat
                        val yetiskinFiyat = yetiskinSayisi * fiyat
                        var cocukFiyat = (cocukSayisi / 2) * fiyat

                        if (cocukSayisi == 0) {
                            val toplamFiyat = yetiskinFiyat
                            cardCellBinding.rezfiyat.text = toplamFiyat.toString() + " TL"
                        } else if (cocukSayisi == 1) {
                            cocukFiyat = (0.5 * fiyat).toInt()
                            val toplamFiyat = yetiskinFiyat + cocukFiyat
                            cardCellBinding.rezfiyat.text = toplamFiyat.toString() + " TL"
                        } else if (cocukSayisi == 2) {
                            cocukFiyat = fiyat
                            val toplamFiyat = yetiskinFiyat + cocukFiyat
                            cardCellBinding.rezfiyat.text = toplamFiyat.toString() + " TL"
                        } else if (cocukSayisi == 3) {
                            cocukFiyat = (1.5 * fiyat).toInt()
                            val toplamFiyat = yetiskinFiyat + cocukFiyat
                            cardCellBinding.rezfiyat.text = toplamFiyat.toString() + " TL"
                        } else if (cocukSayisi == 4) {
                            cocukFiyat = (2 * fiyat).toInt()
                            val toplamFiyat = yetiskinFiyat + cocukFiyat
                            cardCellBinding.rezfiyat.text = toplamFiyat.toString() + " TL"
                        }
                    }
                }
            }
            else if (hizmetListesi[0].hizmetTabloAdi == "teknetur")
            {
                for (teknetur in tekneturviewListesi) {
                    if (hizmetListesi[0].hizmetTabloId.toString() == teknetur.idTekneTur.toString()) {
                        cardCellBinding.duzenlebtn.setOnClickListener {
                            clickrez.onclickrezervasyon(rezervasyon)
                        }
                        cardCellBinding.turadi.text = teknetur.adi.toString()
                        cardCellBinding.reztarihi.text = rezervasyon.tarihi.toString()
                        cardCellBinding.rezkisisayisi.text =
                            "${rezervasyon.yetiskinSayisi.toString()} Yetişkin,${rezervasyon.cocukSayisi.toString()} Çocuk"

                        cardCellBinding.musteriadsoyad.text =
                            kullanankisi[0].adi.toString() + " " + kullanankisi[0].soyadi.toString()
                        cardCellBinding.telno.text = kullanankisi[0].telefonNumarasi.toString()
                        cardCellBinding.guzergah.text =
                            "${teknetur.baslangicKonumu}-->${teknetur.guzergah}"
                        if (teknetur.vizeDurumu == "Evet")
                            cardCellBinding.vize.text = "Vizeli"
                        else
                            cardCellBinding.vize.text = "Vizesiz"

                        val formatter =
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale("tr"))
                        val giristarihi =
                            LocalDate.parse(teknetur.baslangicTarihi.toString(), formatter)

                        val gelecekTarih = giristarihi.plusDays(5)
                        val cikistarihiFormatter =
                            DateTimeFormatter.ofPattern("dd LLLL yyyy", Locale("tr"))
                        val cikistarihi = gelecekTarih.format(cikistarihiFormatter)

                        val reztarihi = LocalDate.parse(rezervasyon.tarihi.toString(), formatter)
                        val dateFormatter =
                            DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("tr"))
                        val tarihString = reztarihi.format(dateFormatter)

                        val giristarihi2 =
                            LocalDate.parse(teknetur.baslangicTarihi.toString(), formatter)
                        val giristring = giristarihi2.format(dateFormatter)
                        cardCellBinding.reztarihi.text = tarihString.toString()
                        cardCellBinding.giristarih.text = giristring.toString()
                        cardCellBinding.cikistarih.text = cikistarihi.toString()

                        Picasso.get().load(teknetur.resimUrl).into(cardCellBinding.turfoto)
                        val yetiskinSayisi = rezervasyon.yetiskinSayisi.toInt()
                        val cocukSayisi = rezervasyon.cocukSayisi.toInt()
                        val fiyat = teknetur.fiyat
                        val yetiskinFiyat = yetiskinSayisi * fiyat
                        var cocukFiyat = (cocukSayisi / 2) * fiyat

                        if (cocukSayisi == 0) {
                            val toplamFiyat = yetiskinFiyat
                            cardCellBinding.rezfiyat.text = toplamFiyat.toString() + " TL"
                        } else if (cocukSayisi == 1) {
                            cocukFiyat = (0.5 * fiyat).toInt()
                            val toplamFiyat = yetiskinFiyat + cocukFiyat
                            cardCellBinding.rezfiyat.text = toplamFiyat.toString() + " TL"
                        } else if (cocukSayisi == 2) {
                            cocukFiyat = fiyat
                            val toplamFiyat = yetiskinFiyat + cocukFiyat
                            cardCellBinding.rezfiyat.text = toplamFiyat.toString() + " TL"
                        } else if (cocukSayisi == 3) {
                            cocukFiyat = (1.5 * fiyat).toInt()
                            val toplamFiyat = yetiskinFiyat + cocukFiyat
                            cardCellBinding.rezfiyat.text = toplamFiyat.toString() + " TL"
                        } else if (cocukSayisi == 4) {
                            cocukFiyat = (2 * fiyat).toInt()
                            val toplamFiyat = yetiskinFiyat + cocukFiyat
                            cardCellBinding.rezfiyat.text = toplamFiyat.toString() + " TL"
                        }

                    }
                }
            }
            else if (hizmetListesi[0].hizmetTabloAdi == "otel")
            {
                for (otel in otelviewListesi) {
                    if (hizmetListesi[0].hizmetTabloId.toString() == otel.idOtel.toString()) {
                        cardCellBinding.duzenlebtn.setOnClickListener {
                            clickrez.onclickrezervasyon(rezervasyon)
                        }
                        cardCellBinding.turadi.text = otel.adi.toString() + " Otel"
                        cardCellBinding.reztarihi.text = rezervasyon.tarihi.toString()

                        cardCellBinding.rezkisisayisi.text =
                            "${rezervasyon.yetiskinSayisi.toString()} Yetişkin,${rezervasyon.cocukSayisi.toString()} Çocuk"

                        cardCellBinding.musteriadsoyad.text =
                            kullanankisi[0].adi.toString() + " " + kullanankisi[0].soyadi.toString()
                        cardCellBinding.telno.text = kullanankisi[0].telefonNumarasi.toString()

                        cardCellBinding.guzergahtext.text = "Adres : "
                        cardCellBinding.guzergah.text = otel.adres.toString()

                        cardCellBinding.vizetext.text = "Otel Teması : "
                        cardCellBinding.vize.text = otel.tema.toString()

                        cardCellBinding.turgiristarihtext.text = "Otel Giriş Tarihi : "

                        cardCellBinding.turcikistarihtext.text = "Otel Çıkış Tarihi : "

                        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

                        val dateTime = LocalDateTime.parse(rezervasyon.tarihi.toString(), formatter)
                        val dateTime2 =
                            LocalDateTime.parse(rezervasyon.girisTarihi.toString(), formatter)
                        val dateTime3 = LocalDateTime.parse(rezervasyon.cikisTarihi, formatter)

                        val reztarihi = dateTime.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
                        val otelgiristarihi =
                            dateTime2.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
                        val otelcikistarihi =
                            dateTime3.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))

                        cardCellBinding.reztarihi.text = reztarihi.toString()
                        cardCellBinding.giristarih.text = otelgiristarihi.toString()
                        cardCellBinding.cikistarih.text = otelcikistarihi.toString()

                        Picasso.get().load(otel.resimUrl).into(cardCellBinding.turfoto)

                        val formatter2 = DateTimeFormatter.ofPattern("dd MMMM yyyy")


                        val girisTarihi = LocalDate.parse(otelgiristarihi, formatter2)
                        val cikisTarihi = LocalDate.parse(otelcikistarihi, formatter2)

                        val gunSayisi = ChronoUnit.DAYS.between(girisTarihi, cikisTarihi)
                        val yetiskinSayisi = rezervasyon.yetiskinSayisi.toInt()
                        val cocukSayisi = rezervasyon.cocukSayisi.toInt()
                        val fiyat = otel.fiyat
                        val yetiskinFiyat = yetiskinSayisi * fiyat
                        var cocukFiyat = (cocukSayisi / 2) * fiyat

                        if (cocukSayisi == 0) {
                            val toplamFiyat = yetiskinFiyat * gunSayisi
                            cardCellBinding.rezfiyat.text = toplamFiyat.toString() + " TL"
                        } else if (cocukSayisi == 1) {
                            cocukFiyat = (0.5 * fiyat).toInt()
                            val toplamFiyat = (yetiskinFiyat + cocukFiyat) * gunSayisi
                            cardCellBinding.rezfiyat.text = toplamFiyat.toString() + " TL"
                        } else if (cocukSayisi == 2) {
                            cocukFiyat = fiyat
                            val toplamFiyat = (yetiskinFiyat + cocukFiyat) * gunSayisi
                            cardCellBinding.rezfiyat.text = toplamFiyat.toString() + " TL"
                        } else if (cocukSayisi == 3) {
                            cocukFiyat = (1.5 * fiyat).toInt()
                            val toplamFiyat = (yetiskinFiyat + cocukFiyat) * gunSayisi
                            cardCellBinding.rezfiyat.text = toplamFiyat.toString() + " TL"
                        } else if (cocukSayisi == 4) {
                            cocukFiyat = (2 * fiyat).toInt()
                            val toplamFiyat = (yetiskinFiyat + cocukFiyat) * gunSayisi
                            cardCellBinding.rezfiyat.text = toplamFiyat.toString() + " TL"
                        }

                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = RezervasyonKutucuklariBinding.inflate(from, parent, false)
        return CardViewHolder(binding, rezervasyonlarimlist, clickrez)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.find(rezervasyonlarimlist[position])
    }

    override fun getItemCount(): Int = rezervasyonlarimlist.size
}