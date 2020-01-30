package com.example.helloandroid.timerecording.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ListPopupWindow
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.helloandroid.BaseActivity
import com.example.helloandroid.HelloDatePickerDialog
import com.example.helloandroid.databinding.FragmentUpsertZeitArbeitsverhaeltnisBinding
import com.example.helloandroid.timerecording.KeineAuswahl
import com.example.helloandroid.timerecording.config.*
import org.threeten.bp.LocalDate


abstract class UpsertZeitArbeitsverhaeltnisFragment : UpsertArbeitsverhaeltnisFragment() {


    protected lateinit var upsertZeitArbeitsverhaeltnisViewModel: UpsertZeitArbeitsverhaeltnisViewModel

    private lateinit var fahrzeugListPopupWindow: ListPopupWindow
    private lateinit var anbaugeraetListPopupWindow: ListPopupWindow
    private lateinit var taetigkeitListPopupWindow: ListPopupWindow


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as? BaseActivity)?.let { activity ->
            appConfigurationViewModel.calendarConfig.observe(this, Observer {
                upsertZeitArbeitsverhaeltnisViewModel.setLeistungserbringer(it.appUser)
                fahrzeugListPopupWindow = createListPopupWindowFahrzeug(activity, it.fahrzeuge)
                anbaugeraetListPopupWindow = createListPopupWindowAnaugeraet(activity, it.anbaugeraete)
                taetigkeitListPopupWindow = createListPopupWindowTaetigkeit(activity, it.teatigkeiten)
            })
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentUpsertZeitArbeitsverhaeltnisBinding.inflate(inflater, container, false)
        binding.viewModel = upsertZeitArbeitsverhaeltnisViewModel
        binding.upsertArbeitsverhaeltnisDetailsFragment = this

        (activity as? BaseActivity)?.let {
            val adapter = TitlesArrayAdapter(it.applicationContext, appConfigurationViewModel.titles)
            binding.autocompleteTitle.apply {
                setAdapter(adapter)
                threshold = 1
            }
        }

        return binding.root
    }

    override fun initArbeitsverhaeltnisViewModel(activity: AppCompatActivity) {
        upsertZeitArbeitsverhaeltnisViewModel =
            ViewModelProviders.of(activity, ZeiterfassungViewModelFactory(activity.application))
                .get(UpsertZeitArbeitsverhaeltnisViewModel::class.java)
    }

    override fun updateLeistungserbringerListener(entries: List<Person>): (Int) -> Unit {
        return {
            if (it == 0) upsertZeitArbeitsverhaeltnisViewModel.setLeistungserbringer(Person())
            else upsertZeitArbeitsverhaeltnisViewModel.setLeistungserbringer(entries[it - 1])
            leistungserbringerListPopupWindow.dismiss()
        }
    }

    override fun updateLeistungsnehmerListener(entries: List<Person>): (Int) -> Unit {
        return {
            if (it == 0) upsertZeitArbeitsverhaeltnisViewModel.setLeistungsnehmer(Person())
            else upsertZeitArbeitsverhaeltnisViewModel.setLeistungsnehmer(entries[it - 1])
            leistungsnehmerListPopupWindow.dismiss()
        }
    }

    override fun validate(): Boolean {
        return upsertZeitArbeitsverhaeltnisViewModel.validate()
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


    private fun createListPopupWindowTaetigkeit(it: AppCompatActivity, entries: List<Taetigkeit>): ListPopupWindow {
        val updateModelListener: (Int) -> Unit = {
            if (it == 0) upsertZeitArbeitsverhaeltnisViewModel.setTaetigkeit(Taetigkeit())
            else upsertZeitArbeitsverhaeltnisViewModel.setTaetigkeit(entries[it - 1])
            taetigkeitListPopupWindow.dismiss()
        }
        val dropdownEntries = mutableListOf(KeineAuswahl.value).apply { addAll(entries.map { it.bezeichnung }) }
        return createListPopupWindow(it, dropdownEntries, updateModelListener)
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


    fun openFahrzeugListPopUp(editTextView: View) {
        fahrzeugListPopupWindow.apply { this.anchorView = editTextView }.show()
    }

    fun openMaschineListPopUp(editTextView: View) {
        anbaugeraetListPopupWindow.apply { this.anchorView = editTextView }.show()
    }


    fun openTaetigkeitListPopUp(editTextView: View) {
        taetigkeitListPopupWindow.apply { this.anchorView = editTextView }.show()
    }


}
