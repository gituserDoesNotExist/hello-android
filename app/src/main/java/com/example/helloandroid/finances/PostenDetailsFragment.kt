package com.example.helloandroid.finances


import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TableLayout
import android.widget.TableLayout.*
import android.widget.TableRow
import android.widget.TableRow.LayoutParams.*
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.helloandroid.R

class PostenDetailsFragment : Fragment() {

    private lateinit var postenDetailsViewModel: PostenDetailsViewModel
    private lateinit var sharedPostenViewModel: SharedPostenViewModel
    private lateinit var dataTable: TableLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_posten_details, container, false)

        activity?.let {
            sharedPostenViewModel = ViewModelProviders.of(it).get(SharedPostenViewModel::class.java)
            postenDetailsViewModel = ViewModelProviders.of(it, FinancesViewModelFactory(it.application))
                .get(PostenDetailsViewModel::class.java)
            rootView.findViewById<TextView>(R.id.title_posten_details_fragment).text = anzeigeTextForPostenname(it)

            val ausgaben = postenDetailsViewModel.findAusgabenForPosten(sharedPostenViewModel.currentPosten)
            val ausgabenRecyclerView = rootView.findViewById<RecyclerView>(R.id.ausgaben_recycler_view)
            val adapter = AusgabeRecyclerViewAdapter(ausgaben)
            ausgabenRecyclerView.adapter = adapter
            ausgabenRecyclerView.layoutManager = LinearLayoutManager(activity)

        }

        return rootView
    }

    private fun anzeigeTextForPostenname(it: FragmentActivity): String {
        return it.resources.getString(R.string.title_posten_details_fragment, sharedPostenViewModel.currentPosten.name)
    }

}
