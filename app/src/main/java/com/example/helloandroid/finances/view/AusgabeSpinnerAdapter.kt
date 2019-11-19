package com.example.helloandroid.finances.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.helloandroid.R

class AusgabeSpinnerAdapter(context: Context, dropdownEntries: List<String>) :
    ArrayAdapter<String>(context, 0, dropdownEntries) {

    init {
        setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.ausgabe_spinner, null)
        view.findViewById<TextView>(R.id.ausgabe_spinner_item_text).text = getItem(position)
        return view
    }


}