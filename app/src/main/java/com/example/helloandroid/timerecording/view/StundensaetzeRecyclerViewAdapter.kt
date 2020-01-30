package com.example.helloandroid.timerecording.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.helloandroid.R
import com.example.helloandroid.view.BigDecimalConverter
import java.math.BigDecimal

class StundensaetzeRecyclerViewAdapter(config: CalendarConfiguration) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val bezeichnungenUndStundensaetze = ArrayList<Pair<String, BigDecimal>>()

    init {
        bezeichnungenUndStundensaetze.addAll(config.anbaugeraete.map { Pair(it.bezeichnung, it.stundensatz) })
        bezeichnungenUndStundensaetze.addAll(config.fahrzeuge.map { Pair(it.bezeichnung, it.stundensatz) })
        bezeichnungenUndStundensaetze.addAll(config.teilnehmer.map { Pair(it.name, it.stundensatz) })
        bezeichnungenUndStundensaetze.sortBy {
            it.first
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_kosten_pro_einheit, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val bezeichnung = bezeichnungenUndStundensaetze[position].first
        val stundensatz = bezeichnungenUndStundensaetze[position].second

        if (holder is ItemViewHolder) {
            holder.bezeichnung.text = bezeichnung
            holder.wert.text = stundensatzForAnzeige(holder, stundensatz)
        }

    }

    private fun stundensatzForAnzeige(holder: RecyclerView.ViewHolder, stundensatz: BigDecimal): String {
        val satz = BigDecimalConverter.bigDecimalToString(stundensatz)
        return holder.itemView.resources.getString(R.string.euro_pro_stunde, satz)
    }


    override fun getItemCount(): Int {
        return bezeichnungenUndStundensaetze.size
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bezeichnung: TextView = itemView.findViewById(R.id.item_stundensatz_bezeichnung)
        val wert: TextView = itemView.findViewById(R.id.item_stundensatz_wert)
    }


}