package com.example.helloandroid.timerecording.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.helloandroid.R

class FiltersRecyclerViewAdapter(private val suchkriterien: Suchkriterien,
                                 private val removeFilterConsumer: (FilterKeys) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_filter, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val suchfilter = suchkriterien.getFiltersForAnzeige()[position]
        if (holder is ItemViewHolder) {
            if (suchfilter.value.isEmpty()) {
                holder.hide()
                return
            }
            holder.filterName.text = suchfilter.key.filterName
            holder.filterText.text = suchfilter.value
            holder.btnDeleteFilter.setOnClickListener {
                removeFilterConsumer(suchfilter.key)
                notifyItemChanged(position)
            }
        }

    }

    override fun getItemCount(): Int {
        return suchkriterien.getFiltersForAnzeige().size
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val filterName: TextView = itemView.findViewById(R.id.filter_name)
        val filterText: TextView = itemView.findViewById(R.id.filter_text)
        val btnDeleteFilter: ImageButton = itemView.findViewById(R.id.btn_remove_filter)

        fun hide() {
            itemView.findViewById<LinearLayout>(R.id.linear_layout_filter_container).setVisibleOrGone(false)
        }
    }


}