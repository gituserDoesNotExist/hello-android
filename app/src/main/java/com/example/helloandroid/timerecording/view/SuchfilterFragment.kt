package com.example.helloandroid.timerecording.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.helloandroid.DialogOpener
import com.example.helloandroid.HelloDatePickerDialog
import com.example.helloandroid.R
import com.example.helloandroid.databinding.FragmentSuchfilterBinding
import org.threeten.bp.LocalDate

class SuchfilterFragment : Fragment() {

    private lateinit var filtersViewModel: FiltersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as? AppCompatActivity)?.let {
            it.supportActionBar?.title = resources.getString(R.string.title_fragment_suchfilter)
            initializeViewModel(it)
        }
        val binding = FragmentSuchfilterBinding.inflate(inflater, container, false)


        binding.suchfilterFragment = this
        binding.viewModel = filtersViewModel

        return binding.root
    }

    private fun initializeViewModel(it: FragmentActivity) {
        filtersViewModel = ViewModelProviders.of(it).get(FiltersViewModel::class.java)
    }


    fun openDatePickerStartDate() {
        val dateSetListener: (LocalDate) -> Unit = { date ->
            filtersViewModel.updateStartDate(date)
        }
        activity?.let { HelloDatePickerDialog(it, dateSetListener, filtersViewModel.getStartDate()).show() }
    }

    fun openDatePickerEndDate() {
        val dateSetListener: (LocalDate) -> Unit = { date ->
            filtersViewModel.updateEndDate(date)
        }
        activity?.let { HelloDatePickerDialog(it, dateSetListener, filtersViewModel.getEndDate()).show() }
    }

    fun openSelectLeistungsnehmerDialog() {
        activity?.let {
            DialogOpener.openDialog(it, SelectLeistungsnehmerDialog(), "dialog_select_filter")
        }
    }

    fun openSelectLeistungserbringerDialog() {
        activity?.let {
            DialogOpener.openDialog(it, SelectLeistungserbringerDialog(), "dialog_select_leistungserbringer")
        }
    }

    fun openSelectTaetigkeitDialog() {
        activity?.let {
            DialogOpener.openDialog(it, SelectTaetigkeitDialog(), "dialog_select_teatigkeit")
        }
    }

    fun applyFilters() {
        ZeiterfassungNavigation.getNavigation(findNavController()).fromSuchfilterToUebersicht()
    }



}
