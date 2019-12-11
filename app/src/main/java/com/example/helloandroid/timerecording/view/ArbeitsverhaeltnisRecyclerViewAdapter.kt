package com.example.helloandroid.timerecording.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.helloandroid.R
import com.example.helloandroid.timerecording.TeamupEvents
import org.threeten.bp.format.DateTimeFormatter

class ArbeitsverhaeltnisRecyclerViewAdapter(private val teamupEvents: TeamupEvents) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var onItemClickListener: View.OnClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = inflate(R.layout.item_arbeitsverhaeltnis, parent)
        return ItemViewHolder(itemView, onItemClickListener)
    }

    private fun inflate(layoutId: Int, parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val teamupEvent = teamupEvents.events[position]
        val arbeitsverhaeltnis = teamupEvent.arbeitsverhaeltnis
        if (holder is ItemViewHolder) {
            holder.arbeitsverhaeltnisRemoteId = teamupEvent.remoteCalenderId
            holder.titleTextView.text = arbeitsverhaeltnis.createTitleForArbeitsverhaeltnis()
            holder.dateTextView.text = arbeitsverhaeltnis.datum.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
            holder.durationTextView.text = arbeitsverhaeltnis.arbeitszeit.toString()
            holder.commentTextView.text = arbeitsverhaeltnis.kommentar
        }

    }

    override fun getItemCount(): Int {
        return teamupEvents.events.size
    }


    class ItemViewHolder(itemView: View, onClickListener: View.OnClickListener) : RecyclerView.ViewHolder(itemView) {
        lateinit var arbeitsverhaeltnisRemoteId : String
        val titleTextView: TextView = itemView.findViewById(R.id.item_arbeitsverhaeltnis_title)
        val dateTextView: TextView = itemView.findViewById(R.id.item_arbeitsverhaeltnis_date)
        val durationTextView: TextView = itemView.findViewById(R.id.item_arbeitsverhaeltnis_duration)
        val commentTextView: TextView = itemView.findViewById(R.id.item_arbeitsverhaeltnis_comment)

        init {
            itemView.setOnClickListener(onClickListener)
            itemView.tag = this
        }
    }


}