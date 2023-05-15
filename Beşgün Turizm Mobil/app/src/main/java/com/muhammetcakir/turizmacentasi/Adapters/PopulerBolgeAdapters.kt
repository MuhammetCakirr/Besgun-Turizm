package com.muhammetcakir.turizmacentasi.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhammetcakir.turizmacentasi.ClickListener.Clickpopulerbolgeler
import com.muhammetcakir.turizmacentasi.Models.PopulerBolgeler
import com.muhammetcakir.turizmacentasi.databinding.PopulerKutucuklariBinding


class PopulerBolgeAdapters(
    var populerbolgelerlist:ArrayList<PopulerBolgeler>,
    var clickpop:Clickpopulerbolgeler
): RecyclerView.Adapter<PopulerBolgeAdapters.CardViewHolder>()
{
    class CardViewHolder(private val cardCellBinding: PopulerKutucuklariBinding,var clickpop:Clickpopulerbolgeler):RecyclerView.ViewHolder(cardCellBinding.root)
    {

        fun find(sehir: PopulerBolgeler)
        {
            cardCellBinding.populerbolgefotosu.setImageResource(sehir.sehirfotosu)
            cardCellBinding.populerbolgeadi.text=sehir.sehiradi
            cardCellBinding.cardpopbolge.setOnClickListener {
                clickpop.onclickpopuler(sehir)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = PopulerKutucuklariBinding.inflate(from, parent, false)
        return CardViewHolder(binding,clickpop)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.find(populerbolgelerlist[position])
    }

    override fun getItemCount(): Int = populerbolgelerlist.size
}