package com.example.helloandroid.timerecording.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ListPopupWindow
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.helloandroid.BaseActivity
import com.example.helloandroid.HelloDatePickerDialog
import com.example.helloandroid.R
import com.example.helloandroid.databinding.FragmentUpsertZeitArbeitsverhaeltnisBinding
import com.example.helloandroid.timerecording.KeineAuswahl
import com.example.helloandroid.timerecording.config.*
import org.threeten.bp.LocalDate


abstract class UpsertZeitArbeitsverhaeltnisFragment : Fragment() {


    protected lateinit var upsertZeitArbeitsverhaeltnisViewModel: UpsertZeitArbeitsverhaeltnisViewModel

    private lateinit var appConfigurationViewModel: AppConfigurationViewModel
    private lateinit var leistungserbringerListPopupWindow: ListPopupWindow
    private lateinit var fahrzeugListPopupWindow: ListPopupWindow
    private lateinit var anbaugeraetListPopupWindow: ListPopupWindow
    private lateinit var leistungsnehmerListPopupWindow: ListPopupWindow
    private lateinit var taetigkeitListPopupWindow: ListPopupWindow


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as? BaseActivity)?.let { activity ->
            initializeViewModel(activity)
            initializeArbeitsverhaeltnis()
            appConfigurationViewModel.calendarConfig.observe(this, Observer {
                upsertZeitArbeitsverhaeltnisViewModel.setLeistungserbringer(it.appUser)
                leistungserbringerListPopupWindow = createListPopupWindowLeistungserbringer(activity, it.teilnehmer)
                fahrzeugListPopupWindow = createListPopupWindowFahrzeug(activity, it.fahrzeuge)
                anbaugeraetListPopupWindow = createListPopupWindowAnaugeraet(activity, it.anbaugeraete)
                leistungsnehmerListPopupWindow = createListPopupWindowLeistungsnehmer(activity, it.teilnehmer)
                taetigkeitListPopupWindow = createListPopupWindowTaetigkeit(activity, it.teatigkeiten)

            })
        }
        val binding = FragmentUpsertZeitArbeitsverhaeltnisBinding.inflate(inflater, container, false)
        val rootView = binding.root
        binding.viewModel = upsertZeitArbeitsverhaeltnisViewModel
        binding.upsertArbeitsverhaeltnisDetailsFragment = this

        return rootView
    }


    private fun initializeViewModel(it: AppCompatActivity) {
        appConfigurationViewModel = ViewModelProviders.of(it, ZeiterfassungViewModelFactory(it.application))
            .get(AppConfigurationViewModel::class.java)
        upsertZeitArbeitsverhaeltnisViewModel = ViewModelProviders.of(it, ZeiterfassungViewModelFactory(it.application))
            .get(UpsertZeitArbeitsverhaeltnisViewModel::class.java)
    }

    private fun createListPopupWindowLeistungserbringer(it: AppCompatActivity, entries: List<Person>): ListPopupWindow {
        val updateModelListener: (Int) -> Unit = {
            if (it == 0) upsertZeitArbeitsverhaeltnisViewModel.setLeistungserbringer(Person())
            else upsertZeitArbeitsverhaeltnisViewModel.setLeistungserbringer(entries[it - 1])
            leistungserbringerListPopupWindow.dismiss()
        }
        val dropdownEntries = mutableListOf(KeineAuswahl.value).apply { addAll(entries.map { it.name }) }
        return createListPopupWindow(it, dropdownEntries, updateModelListener)
    }

    private fun createListPopupWindowFahrzeug(it: AppCompatActivity, entries: List<Maschine>): ListPopupWindow {
        val updateModelListener: (Int) -> Unit = {
            if (it == 0) upsertZeitArbeitsverhaeltnisViewModel.setFahrzeug(Fahrzeug())
            else upsertZeitArbeitsverhaeltnisViewModel.setFahrzeug(entries[it - 1])
            fahrzeugListPopupWindow.dismiss()
        }
        val dropdownEntries = mutableListOf(KeineAuswahl.value).apply { addAll(entries.map { it.bezeichnung }) }
        return createListPopupWindow(it, dropdownEntries, updateModelListener)
    }


    private fun createListPopupWindowAnaugeraet(it: AppCompatActivity, entries: List<Maschine>): ListPopupWindow {
        val updateModelListener: (Int) -> Unit = {
            if (it == 0) upsertZeitArbeitsverhaeltnisViewModel.setAnbaugaeraet(Anbaugeraet())
            else upsertZeitArbeitsverhaeltnisViewModel.setAnbaugaeraet(entries[it - 1])
            anbaugeraetListPopupWindow.dismiss()
        }
        val dropdownEntries = mutableListOf(KeineAuswahl.value).apply { addAll(entries.map { it.bezeichnung }) }
        return createListPopupWindow(it, dropdownEntries, updateModelListener)
    }

    private fun createListPopupWindowLeistungsnehmer(it: AppCompatActivity, entries: List<Person>): ListPopupWindow {
        val updateModelListener: (Int) -> Unit = {
            if (it == 0) upsertZeitArbeitsverhaeltnisViewModel.setLeistungsnehmer(Person())
            else upsertZeitArbeitsverhaeltnisViewModel.setLeistungsnehmer(entries[it - 1])
            leistungsnehmerListPopupWindow.dismiss()
        }
        val dropdownEntries = mutableListOf(KeineAuswahl.value).apply { addAll(entries.map { it.name }) }
        return createListPopupWindow(it, dropdownEntries, updateModelListener)
    }

    private fun createListPopupWindowTaetigkeit(it: AppCompatActivity, entries: List<Taetigkeit>): ListPopupWindow {
        val updateModelListener: (Int) -> Unit = {
            if (it == 0) upsertZeitArbeitsverhaeltnisViewModel.setTaetigkeit(Taetigkeit())
            else upsertZeitArbeitsverhaeltnisViewModel.setTaetigkeit(entries[it - 1])
            taetigkeitListPopupWindow.dismiss()
        }
        val dropdownEntries = mutableListOf(KeineAuswahl.value).apply { addAll(entries.map { it.bezeichnung }) }
        return createListPopupWindow(it, dropdownEntries, updateModelListener)
    }


    private fun createListPopupWindow(it: FragmentActivity, entries: List<String>,
                                      updateModelListener: (Int) -> Unit): ListPopupWindow {
        return ListPopupWindow(it).apply {
            this.setAdapter(ArrayAdapter(it, R.layout.item_list_popup_window, entries))
            this.width = ViewGroup.LayoutParams.WRAP_CONTENT
            this.height = ViewGroup.LayoutParams.WRAP_CONTENT
            this.isModal = true
            this.setOnItemClickListener { _, _, position, _ ->
                updateModelListener(position)
            }
        }
    }


    fun openDatePickerDialog() {
        val crrntDate = upsertZeitArbeitsverhaeltnisViewModel.zeitArbeitsverhaeltnis.datum
        val dateSetListener: (LocalDate) -> Unit = {
            upsertZeitArbeitsverhaeltnisViewModel.setDatum(it)
        }
        activity?.let {
            HelloDatePickerDialog(it, dateSetListener, crrntDate).show()
        }
    }


    fun openLeistungsnehmerListPopUp(editTextView: View) {
        leistungsnehmerListPopupWindow.apply { this.anchorView = editTextView }.show()
    }

    fun openFahrzeugListPopUp(editTextView: View) {
        fahrzeugListPopupWindow.apply { this.anchorView = editTextView }.show()
    }

    fun openMaschineListPopUp(editTextView: View) {
        anbaugeraetListPopupWindow.apply { this.anchorView = editTextView }.show()
    }


    fun openLeistungserbringerListPopUp(editTextView: View) {
        leistungserbringerListPopupWindow.apply { this.anchorView = editTextView }.show()
    }


    fun openTaetigkeitListPopUp(editTextView: View) {
        taetigkeitListPopupWindow.apply { this.anchorView = editTextView }.show()
    }

    fun addOrUpdate() {
        if (upsertZeitArbeitsverhaeltnisViewModel.isValid()) {
            performUpsertOperation()
        } else {
            Toast.makeText(this.context, this.resources.getString(R.string.fehlende_daten), Toast.LENGTH_LONG).show()
        }
    }


    abstract fun performUpsertOperation()

    abstract fun initializeArbeitsverhaeltnis()

}
