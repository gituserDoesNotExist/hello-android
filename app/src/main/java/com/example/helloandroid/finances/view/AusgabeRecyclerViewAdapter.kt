package com.example.helloandroid.finances.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.helloandroid.R
import com.example.helloandroid.finances.Ausgabe
import com.example.helloandroid.finances.persistence.AusgabeEntity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AusgabeRecyclerViewAdapter(ausgabe: List<Ausgabe>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_HEADER: Int = 0
        const val VIEW_TYPE_ITEM: Int = 1
    }

    private val ausgaben = ausgabe

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_HEADER) {
            HeaderViewHolder(
                inflate(
                    R.layout.ausgabe_header_row,
                    parent
                )
            )
        } else {
            ItemViewHolder(
                inflate(
                    R.layout.ausgabe_item,
                    parent
                )
            )
        }
    }

    private fun inflate(layoutId: Int, parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder) {
            holder.datumTextView.text = "Datum"
            holder.wertTextView.text = "Wert"
            holder.beschreibungTextView.text = "Beschreibung"
        }
        if (holder is ItemViewHolder) {
            val currentAusgabe = ausgaben[position - 1]
            holder.datumTextView.text = createDatumForAnzeige(currentAusgabe.datum)
            holder.wertTextView.text = currentAusgabe.wert.toString()
            holder.beschreibungTextView.text = currentAusgabe.beschreibung
        }
    }

    private fun createDatumForAnzeige(datum: LocalDateTime): String {
        return datum.format(DateTimeFormatter.ofPattern("dd MMM yyyy - HH:mm"))
    }

    override fun getItemCount(): Int {
        return ausgaben.size.plus(1)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_HEADER else VIEW_TYPE_ITEM
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val datumTextView: TextView = itemView.findViewById(R.id.ausgabe_item_datum)
        val wertTextView: TextView = itemView.findViewById(R.id.ausgabe_item_wert)
        val beschreibungTextView: TextView = itemView.findViewById(R.id.ausgabe_item_beschreibung)

    }

    class HeaderViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val datumTextView: TextView = itemView.findViewById(R.id.ausgabe_header_row_datum)
        val wertTextView: TextView = itemView.findViewById(R.id.ausgabe_header_rowdatum_wert)
        val beschreibungTextView: TextView = itemView.findViewById(R.id.ausgabe_header_rowdatum_beschreibung)
    }
}