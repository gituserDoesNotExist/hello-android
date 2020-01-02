package com.example.helloandroid.finances.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helloandroid.DialogOpener
import com.example.helloandroid.R
import com.example.helloandroid.databinding.FragmentPostenDetailsBinding
import com.example.helloandroid.finances.Ausgabe

class PostenDetailsFragment : Fragment() {

    private lateinit var postenDetailsViewModel: PostenDetailsViewModel
    private lateinit var sharedPostenViewModel: SharedPostenViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentPostenDetailsBinding.inflate(inflater, container, false)

        (activity as? AppCompatActivity)?.let {
            initializeViewModels(it)
            postenDetailsViewModel.currentPosten.observe(this, Observer { posten ->
                it.supportActionBar?.title = anzeigeTextForPostenname(it, posten.name)
                createRecyclerViewForAusgaben(binding.root, posten.ausgaben, it)
            })
        }

        binding.postenDetailsFragment = this
        binding.postenDetailsViewModel = postenDetailsViewModel
        return binding.root
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
        openAusgabeDialog(AddAusgabeDialog().apply {
            this.ausgabeDTO.beschreibung = ausgabe.beschreibung
            this.ausgabeDTO.datum = ausgabe.datum.toLocalDate()
            this.ausgabeDTO.uhrzeit = ausgabe.datum.toLocalTime()
            this.ausgabeDTO.wert = ausgabe.wert
            this.ausgabeDTO.id = ausgabe.id
        })
    }

    fun openNewAusgabeDialog() {
        openAusgabeDialog(AddAusgabeDialog())
    }

    private fun openAusgabeDialog(addAusgabeDialog: AddAusgabeDialog) {
        activity?.let {
            DialogOpener.openDialog(it, addAusgabeDialog, "dialog_add_ausgabe")
        }
    }

    private fun anzeigeTextForPostenname(it: FragmentActivity, postenName: String): String {
        return it.resources.getString(R.string.title_posten_details_fragment, postenName)
    }

}
