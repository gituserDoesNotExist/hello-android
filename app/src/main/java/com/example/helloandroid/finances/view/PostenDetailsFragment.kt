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
import com.example.helloandroid.DialogOpener
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
        ausgabenRecyclerView.adapter =
            AusgabeRecyclerViewAdapter(activity, ausgaben, this::deleteAusgabe, this::editAusgabe)
        ausgabenRecyclerView.layoutManager = LinearLayoutManager(activity)
    }

    private fun deleteAusgabe(ausgabe: Ausgabe) {
        postenDetailsViewModel.deleteAusgabe(ausgabe)
    }

    private fun editAusgabe(ausgabe: Ausgabe) {
        openAusgabeDialog(AusgabeDialog().apply {
            this.ausgabeDTO.beschreibung = ausgabe.beschreibung
            this.ausgabeDTO.datum = ausgabe.datum.toLocalDate()
            this.ausgabeDTO.uhrzeit = ausgabe.datum.toLocalTime()
            this.ausgabeDTO.wert = ausgabe.wert
            this.ausgabeDTO.id = ausgabe.id
        })
    }

    private fun openNewAusgabeDialog() {
        openAusgabeDialog(AusgabeDialog())
    }

    private fun openAusgabeDialog(ausgabeDialog: AusgabeDialog) {
        activity?.let {
            DialogOpener.openDialog(it, ausgabeDialog, "dialog_ausgabe")
        }
    }

    private fun anzeigeTextForPostenname(it: FragmentActivity, postenName: String): String {
        return it.resources.getString(R.string.title_posten_details_fragment, postenName)
    }

}
