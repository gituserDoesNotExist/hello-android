package com.example.helloandroid.finances.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.helloandroid.R.*
import com.example.helloandroid.SortDirection
import com.example.helloandroid.finances.Ausgabe
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class AusgabeRecyclerViewAdapter(val ausgaben: List<Ausgabe>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_HEADER: Int = 0
        const val VIEW_TYPE_ITEM: Int = 1
        private val DROPDOWN_ENTRIES =
            listOf("Datum aufsteigend", "Datum absteigend", "Wert aufsteigend", "Wert absteigend", "Beschreibung")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_HEADER) {
            val headerView = inflate(layout.ausgabe_header_row, parent)
            headerView.findViewById<Spinner>(id.spinner_ausgabe).also {
                it.onItemSelectedListener = onItemSelectListener()
                it.adapter = AusgabeSpinnerAdapter(parent.context, DROPDOWN_ENTRIES)
            }
            HeaderViewHolder(headerView)
        } else {
            ItemViewHolder(inflate(layout.ausgabe_item, parent))
        }
    }

    private fun inflate(layoutId: Int, parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    }

    private fun onItemSelectListener(): AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                sortAusgaben(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun sortAusgaben(positionOfSortCriteria: Int) {
        when (positionOfSortCriteria) {
            0 -> sortByDatumAufsteigend()
            1 -> sortByDatumAbsteigend()
            2 -> sortByWertAufsteigend()
            3 -> sortByWertAbsteigend()
            4 -> sortAusgabenByBeschreibung()
        }
        notifyDataSetChanged()
    }

    private fun sortByDatumAufsteigend() {
        Collections.sort(ausgaben) { o1, o2 -> sortAusgabenByDate(SortDirection.ASCENDING, o1, o2) }
    }

    private fun sortByDatumAbsteigend() {
        Collections.sort(ausgaben) { o1, o2 -> sortAusgabenByDate(SortDirection.DESCENDING, o1, o2) }
    }

    private fun sortAusgabenByDate(sortDirection: SortDirection, ausgabe: Ausgabe, otherAusgabe: Ausgabe): Int {
        return if (SortDirection.ASCENDING == sortDirection) {
            ausgabe.datum.compareTo(otherAusgabe.datum)
        } else {
            otherAusgabe.datum.compareTo(ausgabe.datum)
        }
    }

    private fun sortByWertAufsteigend() {
        Collections.sort(ausgaben) { o1, o2 -> sortAusgabenByWert(SortDirection.ASCENDING, o1, o2) }
    }

    private fun sortByWertAbsteigend() {
        Collections.sort(ausgaben) { o1, o2 -> sortAusgabenByWert(SortDirection.DESCENDING, o1, o2) }
    }

    private fun sortAusgabenByWert(sortDirection: SortDirection, ausgabe: Ausgabe, otherAusgabe: Ausgabe): Int {
        return if (SortDirection.ASCENDING == sortDirection) {
            ausgabe.wert.compareTo(otherAusgabe.wert)
        } else {
            otherAusgabe.wert.compareTo(ausgabe.wert)
        }
    }

    private fun sortAusgabenByBeschreibung() {
        Collections.sort(ausgaben) { o1, o2 -> o1.beschreibung.compareTo(o2.beschreibung) }
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
        val datumTextView: TextView = itemView.findViewById(id.ausgabe_item_datum)
        val wertTextView: TextView = itemView.findViewById(id.ausgabe_item_wert)
        val beschreibungTextView: TextView = itemView.findViewById(id.ausgabe_item_beschreibung)

    }

    class HeaderViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val datumTextView: TextView = itemView.findViewById(id.ausgabe_header_row_datum)
        val wertTextView: TextView = itemView.findViewById(id.ausgabe_header_rowdatum_wert)
        val beschreibungTextView: TextView = itemView.findViewById(id.ausgabe_header_rowdatum_beschreibung)
    }
}