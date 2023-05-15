package com.muhammetcakir.turizmacentasi.Adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.muhammetcakir.turizmacentasi.ClickListener.ClicktekneTur
import com.muhammetcakir.turizmacentasi.Models.TekneTurView
import com.muhammetcakir.turizmacentasi.R
import com.muhammetcakir.turizmacentasi.databinding.YurtdisiKutucuklariBinding
import com.squareup.picasso.Picasso
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


class TekneTurAdapter(
    var teknetur: ArrayList<TekneTurView>,
    var context: Context,
    var clicktektur: ClicktekneTur
) : RecyclerView.Adapter<TekneTurAdapter.CardViewHolder>() {
    class CardViewHolder(
        private val cardCellBinding: YurtdisiKutucuklariBinding,
        var teknetur: ArrayList<TekneTurView>,
        var context: Context,
        var clicktektur: ClicktekneTur
    ) : RecyclerView.ViewHolder(cardCellBinding.root) {
        @RequiresApi(Build.VERSION_CODES.O)

        fun find(ttur: TekneTurView) {

            cardCellBinding.yurtdisituradi.text = ttur.adi.toString()
            cardCellBinding.yurtdisifiyat.text = ttur.fiyat.toString() + " TL"
            Picasso.get().load(ttur.resimUrl).into(cardCellBinding.yurtdisiulkefoto)
            cardCellBinding.yurtdisibaslangic.text = ttur.baslangicKonumu.toString() + "-->"
            cardCellBinding.yurtdisiguzergah.text = ttur.guzergah.toString()

            val formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale("tr"))
            val giristarihi = LocalDate.parse(ttur.baslangicTarihi.toString(), formatter)

            val gelecekTarih = giristarihi.plusDays(5)
            val cikistarihiFormatter =
                DateTimeFormatter.ofPattern("dd LLLL yyyy", Locale("tr"))
            val cikistarihi = gelecekTarih.format(cikistarihiFormatter)

            val dateFormatter =
                DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("tr"))

            val giristarihi2 =
                LocalDate.parse(ttur.baslangicTarihi.toString(), formatter)
            val giristring = giristarihi2.format(dateFormatter)

            cardCellBinding.yurtdisitarihler.text = giristring.toString()+" - "+cikistarihi.toString()
            if (ttur.vizeDurumu == "Evet") {
                cardCellBinding.yurtdisivizedurumu.text = "Vizeli"
                cardCellBinding.cardvize.setCardBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.Teal_800
                    )
                )
            }

            cardCellBinding.btndetay.setOnClickListener {
                clicktektur.onclickteknetur(ttur)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = YurtdisiKutucuklariBinding.inflate(from, parent, false)
        return CardViewHolder(binding, teknetur, context, clicktektur)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.find(teknetur[position])
    }

    override fun getItemCount(): Int = teknetur.size
}