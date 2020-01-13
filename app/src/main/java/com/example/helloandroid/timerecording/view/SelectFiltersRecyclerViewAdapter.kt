package com.example.helloandroid.timerecording.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.helloandroid.R

abstract class SelectFiltersRecyclerViewAdapter<T>(private val values: List<CheckableFilterItem<T>>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_checkbox_with_text, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            val currentItem = values[position]
            holder.checkbox.isChecked = currentItem.checked
            holder.textView.text = anzeigeValue(currentItem)
            holder.checkbox.setOnClickListener {
                onCheckboxSelectedListener()(holder.checkbox.isChecked, currentItem.item)
            }
        }
    }

    abstract fun anzeigeValue(currentItem: CheckableFilterItem<T>): String

    abstract fun onCheckboxSelectedListener(): (Boolean, T) -> Unit

    override fun getItemCount(): Int {
        return values.size
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkbox: CheckBox = itemView.findViewById(R.id.my_checkbox)
        val textView: TextView = itemView.findViewById(R.id.my_checkbox_text)
    }


}