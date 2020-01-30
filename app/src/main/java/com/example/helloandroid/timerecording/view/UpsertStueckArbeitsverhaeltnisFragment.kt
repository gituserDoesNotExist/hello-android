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
import com.example.helloandroid.databinding.FragmentUpsertStueckArbeitsverhaeltnisBinding
import com.example.helloandroid.timerecording.KeineAuswahl
import com.example.helloandroid.timerecording.config.Person
import com.example.helloandroid.timerecording.config.Produkt
import org.threeten.bp.LocalDate


abstract class UpsertStueckArbeitsverhaeltnisFragment : UpsertArbeitsverhaeltnisFragment() {


    protected lateinit var upsertStueckArbeitsverhaeltnisViewModel: UpsertStueckArbeitsverhaeltnisViewModel

    private lateinit var produktListPopupWindow: ListPopupWindow


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as? BaseActivity)?.let { activity ->
            initializeArbeitsverhaeltnis()
            appConfigurationViewModel.calendarConfig.observe(this, Observer {
                produktListPopupWindow = createListPopupWindowProdukt(activity, it.produkte)
            })
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentUpsertStueckArbeitsverhaeltnisBinding.inflate(inflater, container, false)
        binding.viewModel = upsertStueckArbeitsverhaeltnisViewModel
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
        upsertStueckArbeitsverhaeltnisViewModel =
            ViewModelProviders.of(activity, ZeiterfassungViewModelFactory(activity.application))
                .get(UpsertStueckArbeitsverhaeltnisViewModel::class.java)
    }

    override fun updateLeistungserbringerListener(entries: List<Person>): (Int) -> Unit {
        return {
            if (it == 0) upsertStueckArbeitsverhaeltnisViewModel.setLeistungserbringer(Person())
            else upsertStueckArbeitsverhaeltnisViewModel.setLeistungserbringer(entries[it - 1])
            leistungserbringerListPopupWindow.dismiss()
        }
    }

    override fun updateLeistungsnehmerListener(entries: List<Person>): (Int) -> Unit {
        return {
            if (it == 0) upsertStueckArbeitsverhaeltnisViewModel.setLeistungsnehmer(Person())
            else upsertStueckArbeitsverhaeltnisViewModel.setLeistungsnehmer(entries[it - 1])
            leistungsnehmerListPopupWindow.dismiss()
        }
    }

    override fun validate(): Boolean {
        return upsertStueckArbeitsverhaeltnisViewModel.validate()
    }


    private fun createListPopupWindowProdukt(it: AppCompatActivity, entries: List<Produkt>): ListPopupWindow {
        val updateModelListener: (Int) -> Unit = {
            if (it == 0) upsertStueckArbeitsverhaeltnisViewModel.setProdukt(Produkt())
            else upsertStueckArbeitsverhaeltnisViewModel.setProdukt(entries[it - 1])
            produktListPopupWindow.dismiss()
        }
        val dropdownEntries = mutableListOf(KeineAuswahl.value).apply { addAll(entries.map { it.name }) }
        return createListPopupWindow(it, dropdownEntries, updateModelListener)
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


    fun openProduktListPopUp(editTextView: View) {
        produktListPopupWindow.apply { this.anchorView = editTextView }.show()
    }


}
