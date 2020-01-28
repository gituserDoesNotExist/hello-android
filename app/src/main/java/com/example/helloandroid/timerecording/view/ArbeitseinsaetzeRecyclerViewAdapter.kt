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
import kotlinx.android.synthetic.main.item_arbeitsverhaeltnis.view.*
import org.apache.commons.lang3.StringUtils
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
        val nameLeistungserbringer = arbeitsverhaeltnis.leistungserbringer?.name

        if (holder is ItemViewHolder) {
            val description = arbeitsverhaeltnis.createDescription(holder.itemView.resources)

            holder.arbeitsverhaeltnisRemoteId = arbeitseinsatz.eventInfo.remoteCalenderId
            holder.title.text = arbeitsverhaeltnis.title
            holder.date.text = arbeitsverhaeltnis.datum.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
            holder.leistungserbringer.text = nameLeistungserbringer ?: ""
            holder.leistungsnehmer.text = arbeitsverhaeltnis.leistungsnehmer.name
            holder.kosten.text = kostenForArbeitsverhaeltnis(holder, arbeitsverhaeltnis)
            holder.description.text = description

            holder.description.setVisibleOrGone(StringUtils.isNotBlank(description))
            holder.tableRowLeistungserbringer.setVisibleOrGone(StringUtils.isNotBlank(nameLeistungserbringer))
        }

    }

    private fun kostenForArbeitsverhaeltnis(holder: RecyclerView.ViewHolder, verhaeltnis: Arbeitsverhaeltnis): String {
        val kosten = BigDecimalConverter.bigDecimalToString(verhaeltnis.calculateKostenForArbeitsverhaeltnis())
        return holder.itemView.resources.getString(R.string.kosten_in_euro, kosten)

    }

    override fun getItemCount(): Int {
        return arbeitseinsaetze.einsaetze.size
    }


    class ItemViewHolder(itemView: View, onClickListener: View.OnClickListener) : RecyclerView.ViewHolder(itemView) {
        lateinit var arbeitsverhaeltnisRemoteId: String
        val title: TextView = itemView.title_arbeitsverhaeltnis
        val tableRowLeistungserbringer = itemView.table_row_leistungserbringer
        val date: TextView = itemView.item_arbeitsverhaeltnis_date
        val leistungserbringer: TextView = itemView.item_arbeitsverhaeltnis_leistungserbringer
        val leistungsnehmer: TextView = itemView.item_arbeitsverhaeltnis_leistungsnehmer
        val kosten: TextView = itemView.findViewById(R.id.item_arbeitsverhaeltnis_kosten)
        val description: TextView = itemView.findViewById(R.id.item_arbeitsverhaeltnis_description)

        init {
            itemView.setOnClickListener(onClickListener)
            itemView.tag = this
        }
    }

}