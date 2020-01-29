package com.example.helloandroid.timerecording.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helloandroid.R
import com.example.helloandroid.databinding.DialogSelectFilterBinding
import ru.gildor.databinding.observables.ObservableString

abstract class SelectFilterDialog<T> : DialogFragment() {


    protected lateinit var filterViewModel: FiltersViewModel

    var dialogTitle = ObservableString()

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DialogSelectFilterBinding.inflate(inflater, container, false)
        binding.selectFilterDialog = this
        val rootView = binding.root
        activity?.let { fragment ->
            initializeViewModel(fragment)
            dialogTitle.set(dialogTitle())
            filterViewModel.calendarConfig.observe(this, Observer {
                addFilterValuesToRecyclerView(rootView, it)
            })

        }

        return rootView
    }

    abstract fun dialogTitle() : String

    private fun initializeViewModel(it: FragmentActivity) {
        filterViewModel =
            ViewModelProviders.of(it, ZeiterfassungViewModelFactory(it.application)).get(FiltersViewModel::class.java)
        initSelectedFilterValues()
    }


    private fun addFilterValuesToRecyclerView(rootView: View, config: CalendarConfiguration) {
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recycler_view_leistungsnehmer)
        recyclerView.adapter = createRecyclerViewAdapter(config)
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    abstract fun createRecyclerViewAdapter(config: CalendarConfiguration) : SelectFiltersRecyclerViewAdapter<T>

    fun closeDialog() {
        this.dismiss()
    }

    fun saveSelectedValuesAndCloseDialog() {
        saveSelectedFilterValues()
        closeDialog()
    }

    abstract fun saveSelectedFilterValues()

    abstract fun initSelectedFilterValues()

}