package com.example.helloandroid.timerecording.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.helloandroid.R
import com.example.helloandroid.timerecording.config.Produkt
import com.example.helloandroid.view.BigDecimalConverter

class StueckkostenRecyclerViewAdapter(private val produkte: List<Produkt>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_kosten_pro_einheit, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = produkte[position]

        if (holder is ItemViewHolder) {
            holder.bezeichnung.text = item.name
            holder.wert.text = stundensatzForAnzeige(holder, item)
        }

    }

    private fun stundensatzForAnzeige(holder: RecyclerView.ViewHolder, item: Produkt): String {
        val stundensatz = BigDecimalConverter.bigDecimalToString(item.kostenProStueck)
        return holder.itemView.resources.getString(R.string.kosten_pro_stueck, stundensatz)
    }


    override fun getItemCount(): Int {
        return produkte.size
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bezeichnung: TextView = itemView.findViewById(R.id.item_stundensatz_bezeichnung)
        val wert: TextView = itemView.findViewById(R.id.item_stundensatz_wert)
    }


}