package com.example.helloandroid.finances.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.helloandroid.R
import com.example.helloandroid.finances.Ausgabe

class PostenDetailsFragment : Fragment() {

    private lateinit var postenDetailsVM: PostenDetailsViewModel
    private lateinit var sharedPostenViewModel: SharedPostenViewModel
    private lateinit var btnNewAusgabe: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_posten_details, container, false)

        btnNewAusgabe = rootView.findViewById(R.id.btn_new_ausgabe)
        btnNewAusgabe.setOnClickListener { openNewAusgabeDialog() }

        activity?.let {
            initializeViewModels(it)
            rootView.findViewById<TextView>(R.id.title_posten_details_fragment).text = anzeigeTextForPostenname(it)

            postenDetailsVM.findAusgabenForPosten(postenDetailsVM.currentPosten).observe(
                this,
                Observer<List<Ausgabe>> { ausgaben -> createRecyclerViewForAusgaben(rootView, ausgaben) })
        }

        return rootView
    }

    private fun initializeViewModels(it: FragmentActivity) {
        sharedPostenViewModel = of(it).get(SharedPostenViewModel::class.java)
        postenDetailsVM = of(it, FinancesViewModelFactory(it.application)).get(PostenDetailsViewModel::class.java)
        postenDetailsVM.currentPosten = sharedPostenViewModel.currentPosten
    }

    private fun createRecyclerViewForAusgaben(rootView: View, ausgaben: List<Ausgabe>) {
        val ausgabenRecyclerView = rootView.findViewById<RecyclerView>(R.id.ausgaben_recycler_view)
        val adapter = AusgabeRecyclerViewAdapter(ausgaben)
        ausgabenRecyclerView.adapter = adapter
        ausgabenRecyclerView.layoutManager = LinearLayoutManager(activity)
    }

    private fun openNewAusgabeDialog() {
        activity?.let {
            val transaction = it.supportFragmentManager.beginTransaction()
            val prev = it.supportFragmentManager.findFragmentByTag("ausgabe_dialog")
            if (prev != null) {
                transaction.remove(prev)
            }
            transaction.addToBackStack(null)
            val addPostenDialog = AddAusgabeDialog()
            addPostenDialog.show(transaction, "ausgabe_dialog")
        }
    }

    private fun anzeigeTextForPostenname(it: FragmentActivity): String {
        return it.resources.getString(R.string.title_posten_details_fragment, sharedPostenViewModel.currentPosten.name)
    }

}
