package com.example.helloandroid.presentation

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.helloandroid.IntentKeys
import com.example.helloandroid.R
import com.example.helloandroid.VerzichtDetailsActivity
import com.example.helloandroid.persistence.Verzicht
import java.util.function.Consumer

class VerzichtArrayAdapter constructor(context: Context, verzichte: List<Verzicht>, deleteConsumer: Consumer<Verzicht>) :
    ArrayAdapter<Verzicht>(context, 0, verzichte) {

    private val deleteConsumer = deleteConsumer

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val listItemView = convertView ?: inflateRow(parent)
        val currentVerzicht = getItem(position)

        listItemView.findViewById<TextView>(R.id.verzicht_item_name).text = currentVerzicht?.verzichtName
        listItemView.findViewById<View>(R.id.btn_open_verzicht).setOnClickListener {
            println("clicking ${currentVerzicht?.verzichtName}")
            context.startActivity(createIntentForStartingVerzichtDetailsActivity(currentVerzicht))
        }
        listItemView.findViewById<View>(R.id.btn_delete_verzicht).setOnClickListener {
            println("deleting ${currentVerzicht?.verzichtName}")
            currentVerzicht?.let { verzicht -> deleteConsumer.accept(verzicht) }
        }

        return listItemView
    }

    private fun inflateRow(parent: ViewGroup): View {
        return LayoutInflater.from(context).inflate(R.layout.verzicht_item, parent, false)
    }

    private fun createIntentForStartingVerzichtDetailsActivity(currentVerzicht: Verzicht?): Intent {
        val intent = Intent(context, VerzichtDetailsActivity::class.java)
        intent.putExtra(IntentKeys.VERZICHT_NAME.name, currentVerzicht?.verzichtName)
        return intent
    }




}