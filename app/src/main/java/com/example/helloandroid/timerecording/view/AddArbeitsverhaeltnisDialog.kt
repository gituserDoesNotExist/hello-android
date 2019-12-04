package com.example.helloandroid.timerecording.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.ListPopupWindow
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.helloandroid.R
import com.example.helloandroid.databinding.DialogAddArbeitsverhaeltnisBinding
import org.threeten.bp.LocalDate

class AddArbeitsverhaeltnisDialog : DialogFragment() {

    private lateinit var arbeitsverhaeltnisViewModel: ArbeitsverhaeltnisUebersichtViewModel

    private val arbeitsverhaeltnisDTO = ArbeitsverhaeltnisErstellenDTO()
    private lateinit var leistungserbringerlistPopupWindow: ListPopupWindow
    private lateinit var leistungsnehmerlistPopupWindow: ListPopupWindow
    private lateinit var kategorieListPopupWindow: ListPopupWindow

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DialogAddArbeitsverhaeltnisBinding.inflate(inflater, container, false)
        val rootView = binding.root
        binding.arbeitsverhaeltnisErstellenDto = arbeitsverhaeltnisDTO
        binding.addArbeitsverhaeltnisDialog = this

        activity?.let { fragment ->
            initializeViewModel(fragment)
            arbeitsverhaeltnisViewModel.config.observe(this, Observer {
                leistungserbringerlistPopupWindow = createListPopupWindowLeistungserbringer(fragment, it.participants)
                leistungsnehmerlistPopupWindow = createListPopupWindowLeistungsnehmer(fragment, it.participants)
                kategorieListPopupWindow = createListPopupWindowKategorie(fragment, it.categories)
                arbeitsverhaeltnisDTO.leistungserbringer = it.appUser
            })

        }

        return rootView
    }

    private fun initializeViewModel(it: FragmentActivity) {
        arbeitsverhaeltnisViewModel = ViewModelProviders.of(it, ArbeitsverhaeltnisUebersichtViewModelFactory(it.application))
            .get(ArbeitsverhaeltnisUebersichtViewModel::class.java)
    }

    private fun createListPopupWindowLeistungserbringer(it: FragmentActivity, entries: List<String>): ListPopupWindow {
        val onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            arbeitsverhaeltnisDTO.leistungserbringer = entries[position]
            leistungserbringerlistPopupWindow.dismiss()
        }
        return createListPopupWindow(it, entries, onItemClickListener)
    }

    private fun createListPopupWindowLeistungsnehmer(it: FragmentActivity, entries: List<String>): ListPopupWindow {
        val onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            arbeitsverhaeltnisDTO.leistungsnehmer = entries[position]
            leistungsnehmerlistPopupWindow.dismiss()
        }
        return createListPopupWindow(it, entries, onItemClickListener)
    }

    private fun createListPopupWindowKategorie(it: FragmentActivity, entries: List<String>): ListPopupWindow {
        val onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            arbeitsverhaeltnisDTO.kategorie = entries[position]
            kategorieListPopupWindow.dismiss()
        }
        return createListPopupWindow(it, entries, onItemClickListener)
    }

    private fun createListPopupWindow(it: FragmentActivity, entries: List<String>,
                                      onClickListener: AdapterView.OnItemClickListener): ListPopupWindow {
        return ListPopupWindow(it).apply {
            this.setAdapter(ArrayAdapter(it, R.layout.item_list_popup_window, entries))
            this.width = ViewGroup.LayoutParams.WRAP_CONTENT
            this.height = ViewGroup.LayoutParams.WRAP_CONTENT
            this.isModal = true
            this.setOnItemClickListener(onClickListener)
        }
    }

    fun addArbeitsverhaeltnisAndCloseDialog() {
        arbeitsverhaeltnisViewModel.addArbeitsverhaeltnis(arbeitsverhaeltnisDTO)
        closeDialog()
    }

    fun closeDialog() {
        this.dismiss()
    }

    fun openDatePickerDialog() {
        val crrntDate = LocalDate.now()
        val onDateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            arbeitsverhaeltnisDTO.datumZeiterfassung = LocalDate.of(year, month + 1, dayOfMonth)
        }
        activity?.let {
            DatePickerDialog(it, onDateSetListener, crrntDate.year, crrntDate.monthValue, crrntDate.dayOfMonth).show()
        }
    }

    fun openLeistungserbringerPopUp(editTextView: View) {
        leistungserbringerlistPopupWindow.apply { this.anchorView = editTextView }.show()
    }

    fun openLeistungsnehmerPopUp(editTextView: View) {
        leistungsnehmerlistPopupWindow.apply { this.anchorView = editTextView }.show()
    }

    fun openKategoriePopUp(editTextView: View) {
        kategorieListPopupWindow.apply { this.anchorView = editTextView }.show()
    }

}