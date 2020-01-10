package com.example.helloandroid.timerecording.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.helloandroid.R
import com.example.helloandroid.timerecording.Arbeitseinsaetze
import com.example.helloandroid.timerecording.Arbeitsverhaeltnis
import com.example.helloandroid.view.BigDecimalConverter
import org.threeten.bp.format.DateTimeFormatter

class ArbeitseinsaetzeRecyclerViewAdapter(private val arbeitseinsaetze: Arbeitseinsaetze) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var onItemClickListener: View.OnClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = inflate(parent)
        return ItemViewHolder(itemView, onItemClickListener)
    }

    private fun inflate(parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(R.layout.item_arbeitsverhaeltnis, parent, false)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val arbeitseinsatz = arbeitseinsaetze.einsaetze[position]
        val arbeitsverhaeltnis = arbeitseinsatz.arbeitsverhaeltnis

        if (holder is ItemViewHolder) {
            holder.arbeitsverhaeltnisRemoteId = arbeitseinsatz.eventInfo.remoteCalenderId
            holder.beteiligte.text = beteiligte(holder, arbeitsverhaeltnis)
            holder.title.text = arbeitsverhaeltnis.createTitle()
            holder.date.text = arbeitsverhaeltnis.datum.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
            holder.duration.text = arbeitsverhaeltnis.getQuantity()
            holder.kosten.text = kostenForArbeitsverhaeltnis(holder, arbeitsverhaeltnis)
            holder.fahrzeug.text = arbeitsverhaeltnis.createDescription(holder.itemView.resources)
        }

    }

    private fun beteiligte(holder: RecyclerView.ViewHolder, arbeitsverhaeltnis: Arbeitsverhaeltnis) =
        holder.itemView.resources.getString(R.string.beteiligte, arbeitsverhaeltnis.leistungserbringer?.name ?: "",
            arbeitsverhaeltnis.leistungsnehmer.name)

    private fun kostenForArbeitsverhaeltnis(holder: RecyclerView.ViewHolder, verhaeltnis: Arbeitsverhaeltnis): String {
        val kosten = BigDecimalConverter.bigDecimalToString(verhaeltnis.calculateKostenForArbeitsverhaeltnis())
        return holder.itemView.resources.getString(R.string.kosten_in_euro, kosten)

    }

    override fun getItemCount(): Int {
        return arbeitseinsaetze.einsaetze.size
    }


    class ItemViewHolder(itemView: View, onClickListener: View.OnClickListener) : RecyclerView.ViewHolder(itemView) {
        lateinit var arbeitsverhaeltnisRemoteId: String
        val title: TextView = itemView.findViewById(R.id.item_arbeitsverhaeltnis_title)
        val beteiligte: TextView = itemView.findViewById(R.id.item_arbeitsverhaeltnis_beteiligte)
        val date: TextView = itemView.findViewById(R.id.item_arbeitsverhaeltnis_date)
        val duration: TextView = itemView.findViewById(R.id.item_arbeitsverhaeltnis_quantity)
        val kosten: TextView = itemView.findViewById(R.id.item_arbeitsverhaeltnis_kosten)
        val fahrzeug: TextView = itemView.findViewById(R.id.item_arbeitsverhaeltnis_description)

        init {
            itemView.setOnClickListener(onClickListener)
            itemView.tag = this
        }
    }

}