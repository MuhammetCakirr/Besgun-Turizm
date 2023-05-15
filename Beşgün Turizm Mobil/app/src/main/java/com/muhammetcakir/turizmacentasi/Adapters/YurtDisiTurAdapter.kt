package com.muhammetcakir.turizmacentasi.Adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

import com.muhammetcakir.turizmacentasi.ClickListener.ClickyurtDisiTurView

import com.muhammetcakir.turizmacentasi.Models.YurtDisiTurView

import com.muhammetcakir.turizmacentasi.R
import com.muhammetcakir.turizmacentasi.databinding.YurtdisiKutucuklariBinding

import com.squareup.picasso.Picasso
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


class YurtDisiTurAdapter(
    var yurtdisitur:ArrayList<YurtDisiTurView>,
    var context: Context,
    var clickyurtDisiTur: ClickyurtDisiTurView
): RecyclerView.Adapter<YurtDisiTurAdapter.CardViewHolder>()
{
    class CardViewHolder(private val cardCellBinding: YurtdisiKutucuklariBinding, var yurtdisitur:ArrayList<YurtDisiTurView>,var clickyurtDisiTur: ClickyurtDisiTurView,var context: Context, ): RecyclerView.ViewHolder(cardCellBinding.root)
    {
        @RequiresApi(Build.VERSION_CODES.O)
        fun find(ydt: YurtDisiTurView)
        {
            cardCellBinding.yurtdisituradi.text=ydt.adi.toString()
            cardCellBinding.yurtdisifiyat.text=ydt.fiyat.toString()+ " TL"
            Picasso.get().load(ydt.resimUrl).into(cardCellBinding.yurtdisiulkefoto)
            cardCellBinding.yurtdisibaslangic.text=ydt.baslangicKonumu.toString()+ "-->"
            cardCellBinding.yurtdisiguzergah.text=ydt.guzergah.toString()
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

            cardCellBinding.yurtdisitarihler.text=giristring.toString()+" - "+cikistarihi.toString()
            if (ydt.vizeDurumu=="Hayır")
            {
                cardCellBinding.yurtdisivizedurumu.text="Vizesiz"
                cardCellBinding.cardvize.setCardBackgroundColor(ContextCompat.getColor(context,R.color.Teal_800))
            }
            else
            {
                cardCellBinding.yurtdisivizedurumu.text="Vizeli"
            }
            cardCellBinding.btndetay.setOnClickListener {
                clickyurtDisiTur.onclickydt(ydt)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = YurtdisiKutucuklariBinding.inflate(from, parent, false)
        return CardViewHolder(binding,yurtdisitur,clickyurtDisiTur,context)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.find(yurtdisitur[position])
    }
    override fun getItemCount(): Int = yurtdisitur.size
}