package com.example.helloandroid.timerecording.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.helloandroid.R
import com.example.helloandroid.timerecording.Arbeitsverhaeltnis
import com.example.helloandroid.timerecording.TeamupEvents
import org.threeten.bp.format.DateTimeFormatter

class ArbeitsverhaeltnisRecyclerViewAdapter(private val teamupEvents: TeamupEvents) :
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
        val teamupEvent = teamupEvents.events[position]
        val arbeitsverhaeltnis = teamupEvent.arbeitsverhaeltnis

        if (holder is ItemViewHolder) {
            holder.arbeitsverhaeltnisRemoteId = teamupEvent.remoteCalenderId
            holder.titleTextView.text = titleForArbeitsverhaeltnis(holder, arbeitsverhaeltnis)
            holder.dateTextView.text = arbeitsverhaeltnis.datum.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
            holder.durationTextView.text = arbeitsverhaeltnis.arbeitszeit.toString()
            holder.commentTextView.text = arbeitsverhaeltnis.kommentar
            holder.fahrzeugTextView.text = descriptionFahrzeugMaschine(holder, arbeitsverhaeltnis)
        }

    }

    private fun titleForArbeitsverhaeltnis(holder: RecyclerView.ViewHolder, verhaeltnis: Arbeitsverhaeltnis): String {
        return holder.itemView.resources.getString(R.string.title_arbeitsverhaeltnis,
            verhaeltnis.leistungserbringer.name, verhaeltnis.leistungsnehmer.name, verhaeltnis.kategorie)
    }


    private fun descriptionFahrzeugMaschine(holder: RecyclerView.ViewHolder, verhaeltnis: Arbeitsverhaeltnis): String {
        return when {
            (verhaeltnis.fahrzeug.isBlank() && verhaeltnis.maschine.isBlank()) -> ""
            verhaeltnis.maschine.isBlank() -> verhaeltnis.fahrzeug
            else -> {
                holder.itemView.resources.getString(//
                    R.string.description_fahrzeug_mit_maschine, verhaeltnis.fahrzeug, verhaeltnis.maschine)

            }
        }
    }

    override fun getItemCount(): Int {
        return teamupEvents.events.size
    }


    class ItemViewHolder(itemView: View, onClickListener: View.OnClickListener) : RecyclerView.ViewHolder(itemView) {
        lateinit var arbeitsverhaeltnisRemoteId: String
        val titleTextView: TextView = itemView.findViewById(R.id.item_arbeitsverhaeltnis_title)
        val dateTextView: TextView = itemView.findViewById(R.id.item_arbeitsverhaeltnis_date)
        val durationTextView: TextView = itemView.findViewById(R.id.item_arbeitsverhaeltnis_duration)
        val commentTextView: TextView = itemView.findViewById(R.id.item_arbeitsverhaeltnis_comment)
        val fahrzeugTextView: TextView = itemView.findViewById(R.id.item_arbeitsverhaeltnis_fahrzeug_mit_maschine)

        init {
            itemView.setOnClickListener(onClickListener)
            itemView.tag = this
        }
    }


}