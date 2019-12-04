package com.example.helloandroid.timerecording.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.helloandroid.R
import com.example.helloandroid.timerecording.Arbeitsverhaeltnis

class ArbeitsverhaeltnisRecyclerViewAdapter(private val arbeitsverhaeltnisse: List<Arbeitsverhaeltnis>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(inflate(R.layout.item_arbeitsverhaeltnis, parent))
    }

    private fun inflate(layoutId: Int, parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val arbeitsverhaeltnis = arbeitsverhaeltnisse[position]
        if (holder is ItemViewHolder) {
//            holder.dateTextView.text = arbeitsverhaeltnis.startDateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
//            holder.durationTextView.text = String.format("%.2f", arbeitsverhaeltnis.computeDuration())
            holder.commentTextView.text = arbeitsverhaeltnis.kommentar
        }

    }

    override fun getItemCount(): Int {
        return arbeitsverhaeltnisse.size
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTextView: TextView = itemView.findViewById(R.id.item_arbeitsverhaeltnis_date)
        val durationTextView: TextView = itemView.findViewById(R.id.item_arbeitsverhaeltnis_duration)
        val commentTextView: TextView = itemView.findViewById(R.id.item_arbeitsverhaeltnis_comment)
    }


}