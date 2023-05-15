package com.muhammetcakir.turizmacentasi.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhammetcakir.turizmacentasi.ClickListener.Clicktema
import com.muhammetcakir.turizmacentasi.Models.PopulerBolgeler
import com.muhammetcakir.turizmacentasi.Models.TatilTemalari
import com.muhammetcakir.turizmacentasi.databinding.PopulerKutucuklariBinding
import com.muhammetcakir.turizmacentasi.databinding.TatiltemalariKutucuklariBinding


class TatilTemalariAdapters(
    var tatiltemalari:ArrayList<TatilTemalari>,
    var clicktema:Clicktema
): RecyclerView.Adapter<TatilTemalariAdapters.CardViewHolder>()
{
    class CardViewHolder(private val cardCellBinding: TatiltemalariKutucuklariBinding,var clicktema:Clicktema): RecyclerView.ViewHolder(cardCellBinding.root)
    {
        fun find(tema: TatilTemalari)
        {
            cardCellBinding.tatiltemasifotosu.setImageResource(tema.temafotosu)
            cardCellBinding.tatiltemasiadi.text=tema.temaadi
            cardCellBinding.cardtema.setOnClickListener {
                clicktema.onclicktema(tema)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = TatiltemalariKutucuklariBinding.inflate(from, parent, false)
        return CardViewHolder(binding,clicktema)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.find(tatiltemalari[position])
    }
    override fun getItemCount(): Int = tatiltemalari.size
}