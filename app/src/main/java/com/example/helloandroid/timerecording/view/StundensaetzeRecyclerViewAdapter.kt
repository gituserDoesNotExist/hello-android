package com.example.helloandroid.timerecording.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.helloandroid.R
import com.example.helloandroid.timerecording.config.Abrechenbar
import com.example.helloandroid.timerecording.config.Maschine
import com.example.helloandroid.timerecording.config.Person
import com.example.helloandroid.view.BigDecimalConverter

class StundensaetzeRecyclerViewAdapter(config: CalendarConfiguration) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val abrechenbareLeistungsbringer = ArrayList<Abrechenbar>()

    init {
        abrechenbareLeistungsbringer.addAll(config.anbaugeraete)
        abrechenbareLeistungsbringer.addAll(config.fahrzeuge)
        abrechenbareLeistungsbringer.addAll(config.teilnehmer)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_kosten_pro_einheit, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = abrechenbareLeistungsbringer[position]

        if (holder is ItemViewHolder) {
            holder.bezeichnung.text =
                if (item is Person) "${item.name}:" else if (item is Maschine) "${item.bezeichnung}:" else ""
            holder.wert.text = stundensatzForAnzeige(holder, item)
        }

    }

    private fun stundensatzForAnzeige(holder: RecyclerView.ViewHolder, item: Abrechenbar): String {
        val stundensatz = BigDecimalConverter.bigDecimalToString(item.stundensatz)
        return holder.itemView.resources.getString(R.string.euro_pro_stunde, stundensatz)
    }


    override fun getItemCount(): Int {
        return abrechenbareLeistungsbringer.size
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bezeichnung: TextView = itemView.findViewById(R.id.item_stundensatz_bezeichnung)
        val wert: TextView = itemView.findViewById(R.id.item_stundensatz_wert)
    }


}