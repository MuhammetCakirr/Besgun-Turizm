package com.muhammetcakir.turizmacentasi.Adapters





import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView

import com.muhammetcakir.turizmacentasi.ClickListener.Clicktotel


import com.muhammetcakir.turizmacentasi.Models.*
import com.muhammetcakir.turizmacentasi.R
import com.muhammetcakir.turizmacentasi.databinding.OtelKutucaklariBinding

import com.squareup.picasso.Picasso




class OtellerAdapter(
    var otellist:ArrayList<OtelView>,
    var context: Context,
    var clickotel: Clicktotel
): RecyclerView.Adapter<OtellerAdapter.CardViewHolder>()
{
    class CardViewHolder(private val cardCellBinding: OtelKutucaklariBinding, var otellist:ArrayList<OtelView>, var context: Context,var clickotel: Clicktotel): RecyclerView.ViewHolder(cardCellBinding.root)
    {
        fun find(otel: OtelView)
        {
                    cardCellBinding.otelad.text=otel.adi.toString()
                    cardCellBinding.otelfiyat.text=otel.fiyat.toString()+ " TL"
                    Picasso.get().load(otel.resimUrl).into(cardCellBinding.otelfoto)
                    cardCellBinding.otelkonum.text= otel.adres.toString()
                    cardCellBinding.otelpuan.text=otel.puan.toString()
                    if (otel.tema.toString()=="Tatil Köyü")
                    {
                        cardCellBinding.tema.text=otel.tema.toString()
                    }
                    else{
                        cardCellBinding.tema.text=otel.tema.toString()+"i"
                    }
                    cardCellBinding.btndetay.setOnClickListener {
                        clickotel.onclickotel(otel)
                    }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = OtelKutucaklariBinding.inflate(from, parent, false)
        return CardViewHolder(binding,otellist,context,clickotel)
    }
    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.find(otellist[position])
    }
    override fun getItemCount(): Int = otellist.size
}