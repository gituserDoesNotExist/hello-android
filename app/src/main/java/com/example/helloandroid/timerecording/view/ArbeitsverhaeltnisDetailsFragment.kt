package com.example.helloandroid.timerecording.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.helloandroid.databinding.FragmentArbeitsverhaeltnisDetailsBinding
import com.example.helloandroid.view.LocalDateConverter


class ArbeitsverhaeltnisDetailsFragment : Fragment() {

    private var arbeitsverhaeltnisDetailsDTO = ArbeitsverhaeltnisDetailsDTO()
    private lateinit var sharedArbeitsverhaeltnisViewModel: SharedArbeitsverhaeltnisViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentArbeitsverhaeltnisDetailsBinding.inflate(inflater, container, false)
        binding.arbeitsverhaeltnisDTO = arbeitsverhaeltnisDetailsDTO

        activity?.let {
            sharedArbeitsverhaeltnisViewModel =
                ViewModelProviders.of(it, ArbeitsverhaeltnisViewModelFactory()).get(SharedArbeitsverhaeltnisViewModel::class.java)
        }
        populateDtoWithDataFromSharedArbeitsverhaeltnisViewModel()

        return binding.root
    }

    private fun populateDtoWithDataFromSharedArbeitsverhaeltnisViewModel() {
        val arbeitsverhaeltnis = sharedArbeitsverhaeltnisViewModel.currentArbeitsverhaeltnis
        arbeitsverhaeltnisDetailsDTO.apply {
            this.datum = LocalDateConverter.dateToString(arbeitsverhaeltnis.datum)
            this.kategorie = arbeitsverhaeltnis.kategorie
            this.kommentar = arbeitsverhaeltnis.kommentar
            this.leistungserbringer = arbeitsverhaeltnis.leistungserbringer.name
            this.leistungsnehmer = arbeitsverhaeltnis.leistungsnehmer.name
        }
    }


}
