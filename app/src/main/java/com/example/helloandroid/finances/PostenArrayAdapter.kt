package com.example.helloandroid.finances

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.helloandroid.R
import com.example.helloandroid.finances.persistence.Posten
import java.math.BigDecimal
import java.util.function.Consumer

class PostenArrayAdapter constructor(context: Context, posten: List<Posten>, editConsumer: Consumer<Posten>) :
    ArrayAdapter<Posten>(context, 0, posten) {

    private val editConsumer = editConsumer

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val listItemView = convertView ?: inflateRow(parent)
        val currentPosten = getItem(position)

        findPostenItemNameView(listItemView).text = currentPosten?.name
        findAusgabenForPostenView(listItemView).text = createAnzeigeText(currentPosten?.calculateGesamtausgaben())
        findEditBtn(listItemView).setOnClickListener { currentPosten?.let { editConsumer.accept(it) } }

        return listItemView
    }

    private fun inflateRow(parent: ViewGroup): View {
        return LayoutInflater.from(context).inflate(R.layout.posten_item, parent, false)
    }

    private fun findPostenItemNameView(listItemView: View) =
        listItemView.findViewById<TextView>(R.id.posten_item_name)

    private fun findAusgabenForPostenView(listItemView: View) =
        listItemView.findViewById<TextView>(R.id.ausgaben_for_posten)

    private fun createAnzeigeText(ausgaben: BigDecimal?): String {
        return context.resources.getString(R.string.ausgaben_posten, ausgaben?.toString() ?: "0.0")
    }

    private fun findEditBtn(listItemView: View) = listItemView.findViewById<View>(R.id.btn_edit_posten)


}