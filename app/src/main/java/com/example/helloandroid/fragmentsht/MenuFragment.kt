package com.example.helloandroid.fragmentsht


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.helloandroid.R


class MenuFragment : ListFragment() {

    var androidOS = arrayOf("Cupcake","Donut","Eclair","Froyo","Gingerbread","Honeycomb","Ice Cream SandWich","Jelly Bean","KitKat")
    var versions = arrayOf("1.5", "1.6", "2.0-2.1", "2.2", "2.3", "3.0-3.2", "4.0", "4.1-4.3", "4.4")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        val adapter = ArrayAdapter(activity!!,android.R.layout.simple_list_item_1, androidOS)
        listAdapter = adapter

        return view

    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        val txt = fragmentManager!!.findFragmentById(R.id.text_fragment) as TextFragment?
        txt!!.change(androidOS[position], "versions : " + versions[position])
        listView.setSelector(android.R.color.holo_blue_dark)
    }


}
