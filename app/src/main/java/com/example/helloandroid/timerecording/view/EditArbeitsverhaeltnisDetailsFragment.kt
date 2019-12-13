package com.example.helloandroid.timerecording.view


import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.ListPopupWindow
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.helloandroid.R
import com.example.helloandroid.databinding.FragmentEditArbeitsverhaeltnisDetailsBinding
import com.example.helloandroid.timerecording.TeamupEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import org.threeten.bp.LocalDate


class EditArbeitsverhaeltnisDetailsFragment : Fragment() {


    companion object {
        @JvmStatic
        fun newInstance(editable: Boolean) = EditArbeitsverhaeltnisDetailsFragment().apply {
            this.editable = editable
        }
    }

    interface FragmentInteractionListener {
        fun onUpdateArbeitsverhaeltnis()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.fragmentInteractionListener = context as? FragmentInteractionListener
    }

    private lateinit var sharedTeamupEventViewModel: SharedTeamupEventViewModel
    private lateinit var editArbeitsverhaeltnisViewModel: EditArbeitsverhaeltnisViewModel
    private lateinit var appConfigurationViewModel: AppConfigurationViewModel
    private lateinit var leistungserbringerlistPopupWindow: ListPopupWindow
    private lateinit var leistungsnehmerlistPopupWindow: ListPopupWindow
    private lateinit var kategorieListPopupWindow: ListPopupWindow
    private var updateArbeitsverhaeltnisDisposable: Disposable? = null
    var editable: Boolean = false
    private var fragmentInteractionListener: FragmentInteractionListener? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.let { activity ->
            initializeViewModel(activity)
            activity.title = editArbeitsverhaeltnisViewModel.arbeitsverhaeltnisToEdit.arbeitsverhaeltnisDto.kategorie
            appConfigurationViewModel.calendarConfig.observe(this, Observer {
                leistungserbringerlistPopupWindow = createListPopupWindowLeistungserbringer(activity, it.participants)
                leistungsnehmerlistPopupWindow = createListPopupWindowLeistungsnehmer(activity, it.participants)
                kategorieListPopupWindow = createListPopupWindowKategorie(activity, it.categories)

            })
        }
        val binding = FragmentEditArbeitsverhaeltnisDetailsBinding.inflate(inflater, container, false)
        val rootView = binding.root
        binding.dto = editArbeitsverhaeltnisViewModel.arbeitsverhaeltnisToEdit
        binding.editArbeitsverhaeltnisDetailsFragment = this

        return rootView
    }

    override fun onStop() {
        super.onStop()
        updateArbeitsverhaeltnisDisposable?.dispose()
    }

    private fun initializeViewModel(it: FragmentActivity) {
        sharedTeamupEventViewModel = ViewModelProviders.of(it, ZeiterfassungViewModelFactory(it.application))
            .get(SharedTeamupEventViewModel::class.java)
        appConfigurationViewModel = ViewModelProviders.of(it, ZeiterfassungViewModelFactory(it.application))
            .get(AppConfigurationViewModel::class.java)
        editArbeitsverhaeltnisViewModel = ViewModelProviders.of(it, ZeiterfassungViewModelFactory(it.application))
            .get(EditArbeitsverhaeltnisViewModel::class.java)
            .apply { this.initialize(sharedTeamupEventViewModel.currentEvent) }
    }

    private fun createListPopupWindowLeistungserbringer(it: FragmentActivity, entries: List<String>): ListPopupWindow {
        val onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            editArbeitsverhaeltnisViewModel.arbeitsverhaeltnisToEdit.arbeitsverhaeltnisDto.leistungserbringer =
                entries[position]
            leistungserbringerlistPopupWindow.dismiss()
        }
        return createListPopupWindow(it, entries, onItemClickListener)
    }

    private fun createListPopupWindowLeistungsnehmer(it: FragmentActivity, entries: List<String>): ListPopupWindow {
        val onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            editArbeitsverhaeltnisViewModel.arbeitsverhaeltnisToEdit.arbeitsverhaeltnisDto.leistungsnehmer =
                entries[position]
            leistungsnehmerlistPopupWindow.dismiss()
        }
        return createListPopupWindow(it, entries, onItemClickListener)
    }

    private fun createListPopupWindowKategorie(it: FragmentActivity, entries: List<String>): ListPopupWindow {
        val onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            editArbeitsverhaeltnisViewModel.arbeitsverhaeltnisToEdit.arbeitsverhaeltnisDto.kategorie = entries[position]
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


    fun openDatePickerDialog() {
        val crrntDate =
            editArbeitsverhaeltnisViewModel.arbeitsverhaeltnisToEdit.arbeitsverhaeltnisDto.datumZeiterfassung
        val onDateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            editArbeitsverhaeltnisViewModel.arbeitsverhaeltnisToEdit.arbeitsverhaeltnisDto.datumZeiterfassung =
                LocalDate.of(year, month + 1, dayOfMonth)
        }
        activity?.let {
            DatePickerDialog(it, onDateSetListener, crrntDate.year, crrntDate.monthValue, crrntDate.dayOfMonth).show()
        }
    }


    fun openLeistgunsnehmerListPopUp(editTextView: View) {
        leistungsnehmerlistPopupWindow.apply { this.anchorView = editTextView }.show()
    }


    fun openLeistgunserbringerListPopUp(editTextView: View) {
        leistungserbringerlistPopupWindow.apply { this.anchorView = editTextView }.show()
    }


    fun openKategorieListPopUp(editTextView: View) {
        kategorieListPopupWindow.apply { this.anchorView = editTextView }.show()
    }

    fun getConfirmButtonVisibility(): Int {
        return if (editable) View.VISIBLE else View.INVISIBLE
    }

    fun saveChanges() {
        updateArbeitsverhaeltnisDisposable = editArbeitsverhaeltnisViewModel.updateArbeitsverhaeltnis()//
            .observeOn(AndroidSchedulers.mainThread())//
            .subscribe(Consumer<TeamupEvent> {
                fragmentInteractionListener?.onUpdateArbeitsverhaeltnis()
            })
    }

}
