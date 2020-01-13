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
import com.example.helloandroid.databinding.FragmentUpsertStueckArbeitsverhaeltnisBinding
import com.example.helloandroid.timerecording.config.Person
import com.example.helloandroid.timerecording.config.Produkt
import org.threeten.bp.LocalDate


abstract class UpsertStueckArbeitsverhaeltnisFragment : Fragment() {


    protected lateinit var upsertStueckArbeitsverhaeltnisViewModel: UpsertStueckArbeitsverhaeltnisViewModel

    private lateinit var appConfigurationViewModel: AppConfigurationViewModel
    private lateinit var leistungserbringerListPopupWindow: ListPopupWindow
    private lateinit var leistungsnehmerListPopupWindow: ListPopupWindow
    private lateinit var produktListPopupWindow: ListPopupWindow


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as? BaseActivity)?.let { activity ->
            initializeViewModel(activity)
            appConfigurationViewModel.calendarConfig.observe(this, Observer {
                leistungserbringerListPopupWindow = createListPopupWindowLeistungserbringer(activity, it.teilnehmer)
                leistungsnehmerListPopupWindow = createListPopupWindowLeistungsnehmer(activity, it.teilnehmer)
                produktListPopupWindow = createListPopupWindowProdukt(activity, it.produkte)

            })
        }
        val binding = FragmentUpsertStueckArbeitsverhaeltnisBinding.inflate(inflater, container, false)
        val rootView = binding.root
        binding.viewModel = upsertStueckArbeitsverhaeltnisViewModel
        binding.upsertArbeitsverhaeltnisDetailsFragment = this

        return rootView
    }


    private fun initializeViewModel(it: AppCompatActivity) {
        appConfigurationViewModel = ViewModelProviders.of(it, ZeiterfassungViewModelFactory(it.application))
            .get(AppConfigurationViewModel::class.java)
        upsertStueckArbeitsverhaeltnisViewModel = ViewModelProviders.of(it, ZeiterfassungViewModelFactory(it.application))
            .get(UpsertStueckArbeitsverhaeltnisViewModel::class.java)
    }

    private fun createListPopupWindowLeistungserbringer(it: AppCompatActivity, entries: List<Person>): ListPopupWindow {
        val updateModelListener: (Int) -> Unit = {
            upsertStueckArbeitsverhaeltnisViewModel.setLeistungserbringer(entries[it])
            leistungserbringerListPopupWindow.dismiss()
        }
        return createListPopupWindow(it, entries.map { it.name }, updateModelListener)
    }


    private fun createListPopupWindowLeistungsnehmer(it: AppCompatActivity, entries: List<Person>): ListPopupWindow {
        val updateModelListener: (Int) -> Unit = {
            upsertStueckArbeitsverhaeltnisViewModel.setLeistungsnehmer(entries[it])
            leistungsnehmerListPopupWindow.dismiss()
        }
        return createListPopupWindow(it, entries.map { it.name }, updateModelListener)
    }

    private fun createListPopupWindowProdukt(it: AppCompatActivity, entries: List<Produkt>): ListPopupWindow {
        val updateModelListener: (Int) -> Unit = {
            upsertStueckArbeitsverhaeltnisViewModel.setProdukt(entries[it])
            produktListPopupWindow.dismiss()
        }
        return createListPopupWindow(it, entries.map { it.name }, updateModelListener)
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
        val crrntDate = upsertStueckArbeitsverhaeltnisViewModel.stueckArbeitsverhaeltnis.datum
        val dateSetListener: (LocalDate) -> Unit = {
            upsertStueckArbeitsverhaeltnisViewModel.setDatum(it)
        }
        activity?.let {
            HelloDatePickerDialog(it, dateSetListener, crrntDate).show()
        }
    }


    fun openLeistgunsnehmerListPopUp(editTextView: View) {
        leistungsnehmerListPopupWindow.apply { this.anchorView = editTextView }.show()
    }



    fun openLeistgunserbringerListPopUp(editTextView: View) {
        leistungserbringerListPopupWindow.apply { this.anchorView = editTextView }.show()
    }

    fun openProduktListPopUp(editTextView: View) {
        produktListPopupWindow.apply { this.anchorView = editTextView }.show()
    }


    fun addOrUpdate() {
        if (upsertStueckArbeitsverhaeltnisViewModel.isValid()) {
            upsert()
        } else {
            Toast.makeText(this.context, this.resources.getString(R.string.fehlende_daten), Toast.LENGTH_LONG).show()
        }
    }

    abstract fun upsert()

}
