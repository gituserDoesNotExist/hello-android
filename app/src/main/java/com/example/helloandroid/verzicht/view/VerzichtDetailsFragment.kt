package com.example.helloandroid.verzicht.view


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
import com.example.helloandroid.R

class VerzichtDetailsFragment : Fragment() {

    private lateinit var verzichtDetailsViewModel: VerzichtDetailsViewModel
    private lateinit var verzichtDuration: TextView
    private lateinit var verzichtName: TextView
    private lateinit var btnPlusOneDay: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_verzicht_details, container, false)

        verzichtDuration = rootView.findViewById(R.id.txt_edit_verzicht_number_of_days)
        verzichtName = rootView.findViewById(R.id.txt_edit_verzicht_verzicht_name)
        btnPlusOneDay = rootView.findViewById(R.id.btn_plus_one_day)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.let { fragmentActivity ->
            val currentVerzichtId = extractCurrentVerzichtIdFromSharedViewModel(fragmentActivity)
            initializeVerzichtDetailsViewModel(fragmentActivity)
            verzichtDetailsViewModel.initialize(currentVerzichtId)

            verzichtDetailsViewModel.verzichtLiveData.observe(this, Observer {verzicht ->
                verzichtName.text = verzicht.verzichtName
                verzichtDuration.text = verzicht.days.toString()
                btnPlusOneDay.isEnabled = !verzichtDetailsViewModel.wereDaysIncreasedToday(verzicht)

            })

            btnPlusOneDay.setOnClickListener { verzichtDetailsViewModel.increaseVerzichtDurationByOneDay() }
        }
    }

    private fun extractCurrentVerzichtIdFromSharedViewModel(it: FragmentActivity): Long {
        return ViewModelProviders.of(it).get(SharedVerzichtViewModel::class.java).currentVerzicht.id
    }

    private fun initializeVerzichtDetailsViewModel(it: FragmentActivity) {
        verzichtDetailsViewModel = ViewModelProviders.of(it,
            VerzichtViewModelFactory(it.application))
            .get(VerzichtDetailsViewModel::class.java)
    }

}
