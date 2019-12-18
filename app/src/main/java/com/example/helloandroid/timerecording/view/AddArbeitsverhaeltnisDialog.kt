package com.example.helloandroid.timerecording.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.ListPopupWindow
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.helloandroid.HelloDatePickerDialog
import com.example.helloandroid.R
import com.example.helloandroid.databinding.DialogAddArbeitsverhaeltnisBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import org.threeten.bp.LocalDate

class AddArbeitsverhaeltnisDialog(
    private val onUpdateArbeitsverhaeltnisseListener: OnUpdateArbeitsverhaeltnisseListener) : DialogFragment() {

    private lateinit var addArbeitsverhaeltnisViewModel: AddArbeitsverhaeltnisViewModel
    private val arbeitsverhaeltnisDTO = ArbeitsverhaeltnisDTO()
    private lateinit var leistungserbringerlistPopupWindow: ListPopupWindow
    private lateinit var leistungsnehmerlistPopupWindow: ListPopupWindow
    private lateinit var kategorieListPopupWindow: ListPopupWindow
    private var addArbeitsverhaeltnisDisposable: Disposable? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DialogAddArbeitsverhaeltnisBinding.inflate(inflater, container, false)
        val rootView = binding.root
        binding.arbeitsverhaeltnisDto = arbeitsverhaeltnisDTO
        binding.addArbeitsverhaeltnisDialog = this

        activity?.let { fragment ->
            initializeViewModel(fragment)
            addArbeitsverhaeltnisViewModel.config.observe(this, Observer {
                leistungserbringerlistPopupWindow = createListPopupWindowLeistungserbringer(fragment, it.participants)
                leistungsnehmerlistPopupWindow = createListPopupWindowLeistungsnehmer(fragment, it.participants)
                kategorieListPopupWindow = createListPopupWindowKategorie(fragment, it.categories)
                arbeitsverhaeltnisDTO.leistungserbringer = it.appUser
            })

        }

        return rootView
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    private fun initializeViewModel(it: FragmentActivity) {
        addArbeitsverhaeltnisViewModel = ViewModelProviders.of(it, ZeiterfassungViewModelFactory(it.application))
            .get(AddArbeitsverhaeltnisViewModel::class.java)
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
        addArbeitsverhaeltnisDisposable = addArbeitsverhaeltnisViewModel.addArbeitsverhaeltnis(arbeitsverhaeltnisDTO)//
            .subscribeOn(AndroidSchedulers.mainThread())//
            .subscribe(Consumer<Long> { onUpdateArbeitsverhaeltnisseListener.onArbeitsverhaeltnisseUpdated() })
        closeDialog()
    }

    fun closeDialog() {
        this.dismiss()
    }

    fun openDatePickerDialog() {
        val onDateSet: (LocalDate) -> Unit = { date -> arbeitsverhaeltnisDTO.datumZeiterfassung = date }
        activity?.let { HelloDatePickerDialog(it, onDateSet, LocalDate.now()).show() }
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