package com.example.helloandroid.finances.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helloandroid.R
import com.example.helloandroid.finances.Ausgabe

class PostenDetailsFragment : Fragment() {

    private lateinit var postenDetailsViewModel: PostenDetailsViewModel
    private lateinit var sharedPostenViewModel: SharedPostenViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_posten_details, container, false)

        rootView.findViewById<Button>(R.id.btn_new_ausgabe).setOnClickListener { openNewAusgabeDialog() }
        activity?.let {
            initializeViewModels(it)
            val titlePostenDetailsTextView = rootView.findViewById<TextView>(R.id.title_posten_details_fragment)
            postenDetailsViewModel.currentPosten.observe(this, Observer { posten ->
                titlePostenDetailsTextView.text = anzeigeTextForPostenname(it, posten.name)
                createRecyclerViewForAusgaben(rootView, posten.ausgaben, it)
            })
        }
        return rootView
    }

    private fun initializeViewModels(it: FragmentActivity) {
        sharedPostenViewModel = ViewModelProviders.of(it).get(SharedPostenViewModel::class.java)
        postenDetailsViewModel = ViewModelProviders.of(it, FinancesViewModelFactory(it.application))//
            .get(PostenDetailsViewModel::class.java)
            .apply { this.initializePosten(sharedPostenViewModel.currentPostenStub.postenId) }
    }

    private fun createRecyclerViewForAusgaben(rootView: View, ausgaben: List<Ausgabe>, activity: FragmentActivity) {
        val ausgabenRecyclerView = rootView.findViewById<RecyclerView>(R.id.ausgaben_recycler_view)
        val adapter = AusgabeRecyclerViewAdapter(activity, ausgaben)
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

    private fun anzeigeTextForPostenname(it: FragmentActivity, postenName: String): String {
        return it.resources.getString(R.string.title_posten_details_fragment, postenName)
    }

}
