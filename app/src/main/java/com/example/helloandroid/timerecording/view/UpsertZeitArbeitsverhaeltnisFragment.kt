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
import com.example.helloandroid.timerecording.config.Maschine
import com.example.helloandroid.timerecording.config.Person
import com.example.helloandroid.timerecording.config.Taetigkeit
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
            appConfigurationViewModel.calendarConfig.observe(this, Observer {
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
            upsertZeitArbeitsverhaeltnisViewModel.setLeistungserbringer(entries[it])
            leistungserbringerListPopupWindow.dismiss()
        }
        return createListPopupWindow(it, entries.map { it.name }, updateModelListener)
    }

    private fun createListPopupWindowFahrzeug(it: AppCompatActivity, entries: List<Maschine>): ListPopupWindow {
        val updateModelListener: (Int) -> Unit = {
            upsertZeitArbeitsverhaeltnisViewModel.setFahrzeug(entries[it])
            fahrzeugListPopupWindow.dismiss()
        }
        return createListPopupWindow(it, entries.map { it.bezeichnung }, updateModelListener)
    }


    private fun createListPopupWindowAnaugeraet(it: AppCompatActivity, entries: List<Maschine>): ListPopupWindow {
        val updateModelListener: (Int) -> Unit = {
            upsertZeitArbeitsverhaeltnisViewModel.setAnbaugaeraet(entries[it])
            anbaugeraetListPopupWindow.dismiss()
        }
        return createListPopupWindow(it, entries.map { it.bezeichnung }, updateModelListener)
    }

    private fun createListPopupWindowLeistungsnehmer(it: AppCompatActivity, entries: List<Person>): ListPopupWindow {
        val updateModelListener: (Int) -> Unit = {
            upsertZeitArbeitsverhaeltnisViewModel.setLeistungsnehmer(entries[it])
            leistungsnehmerListPopupWindow.dismiss()
        }
        return createListPopupWindow(it, entries.map { it.name }, updateModelListener)
    }

    private fun createListPopupWindowTaetigkeit(it: AppCompatActivity, entries: List<Taetigkeit>): ListPopupWindow {
        val updateModelListener: (Int) -> Unit = {
            upsertZeitArbeitsverhaeltnisViewModel.setTaetigkeit(entries[it])
            taetigkeitListPopupWindow.dismiss()
        }
        return createListPopupWindow(it, entries.map { it.bezeichnung }, updateModelListener)
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


    fun openLeistgunsnehmerListPopUp(editTextView: View) {
        leistungsnehmerListPopupWindow.apply { this.anchorView = editTextView }.show()
    }

    fun openFahrzeugListPopUp(editTextView: View) {
        fahrzeugListPopupWindow.apply { this.anchorView = editTextView }.show()
    }

    fun openMaschineListPopUp(editTextView: View) {
        anbaugeraetListPopupWindow.apply { this.anchorView = editTextView }.show()
    }


    fun openLeistgunserbringerListPopUp(editTextView: View) {
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

}
