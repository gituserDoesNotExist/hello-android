package com.example.helloandroid.verzicht.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.helloandroid.databinding.FragmentVerzichtDetailsBinding
import com.example.helloandroid.verzicht.persistence.Verzicht

class VerzichtDetailsFragment : Fragment() {

    private lateinit var verzichtDetailsViewModel: VerzichtDetailsViewModel
    var verzichtDTO = VerzichtDTO()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentVerzichtDetailsBinding.inflate(inflater, container, false)
        binding.verzichtDetailsFragment = this

        activity?.let { fragmentActivity ->
            initViewModel(fragmentActivity)
            verzichtDetailsViewModel.verzichtLiveData.observe(this, Observer(this::updateVerzichtDto))
        }

        return binding.root
    }

    private fun initViewModel(fragmentActivity: FragmentActivity) {
        val currentVerzichtId = extractCurrentVerzichtIdFromSharedViewModel(fragmentActivity)
        verzichtDetailsViewModel =
            ViewModelProviders.of(fragmentActivity, VerzichtViewModelFactory(fragmentActivity.application))
                .get(VerzichtDetailsViewModel::class.java).apply { this.initialize(currentVerzichtId) }
    }

    private fun extractCurrentVerzichtIdFromSharedViewModel(it: FragmentActivity): Long {
        return ViewModelProviders.of(it).get(SharedVerzichtViewModel::class.java).currentVerzicht.id
    }

    private fun updateVerzichtDto(verzicht: Verzicht) {
        verzichtDTO.name = verzicht.verzichtName
        verzichtDTO.days = verzicht.days.toString()
        verzichtDTO.daysIncreasedToday = verzichtDetailsViewModel.wereDaysIncreasedToday(verzicht)
    }

    fun increaseVerzichtDurationByOneDay() {
        verzichtDetailsViewModel.increaseVerzichtDurationByOneDay()
    }


}
