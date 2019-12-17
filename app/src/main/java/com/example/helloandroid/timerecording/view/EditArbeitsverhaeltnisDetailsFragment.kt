package com.example.helloandroid.timerecording.view


import android.app.DatePickerDialog
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ListPopupWindow
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.helloandroid.R
import com.example.helloandroid.databinding.FragmentEditArbeitsverhaeltnisDetailsBinding
import com.example.helloandroid.timerecording.TeamupEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import org.threeten.bp.LocalDate


class EditArbeitsverhaeltnisDetailsFragment : Fragment() {



    private lateinit var sharedTeamupEventViewModel: SharedTeamupEventViewModel
    private lateinit var editArbeitsverhaeltnisViewModel: EditArbeitsverhaeltnisViewModel
    private lateinit var appConfigurationViewModel: AppConfigurationViewModel
    private lateinit var leistungserbringerlistPopupWindow: ListPopupWindow
    private lateinit var leistungsnehmerlistPopupWindow: ListPopupWindow
    private lateinit var kategorieListPopupWindow: ListPopupWindow
    private var updateArbeitsverhaeltnisDisposable: Disposable? = null
    var editable: ObservableBoolean = ObservableBoolean(false)
    var confirmButtonVisibility = ObservableInt(View.INVISIBLE)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        (activity as? AppCompatActivity)?.let { activity ->
            activity.supportActionBar?.title = "Details"
            activity.supportActionBar?.invalidateOptionsMenu()
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


    private fun initializeViewModel(it: AppCompatActivity) {
        sharedTeamupEventViewModel = ViewModelProviders.of(it, ZeiterfassungViewModelFactory(it.application))
            .get(SharedTeamupEventViewModel::class.java)
        appConfigurationViewModel = ViewModelProviders.of(it, ZeiterfassungViewModelFactory(it.application))
            .get(AppConfigurationViewModel::class.java)
        editArbeitsverhaeltnisViewModel = ViewModelProviders.of(it, ZeiterfassungViewModelFactory(it.application))
            .get(EditArbeitsverhaeltnisViewModel::class.java)
            .apply { this.initialize(sharedTeamupEventViewModel.currentEvent) }
    }

    private fun createListPopupWindowLeistungserbringer(it: AppCompatActivity, entries: List<String>): ListPopupWindow {
        val onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            editArbeitsverhaeltnisViewModel.arbeitsverhaeltnisToEdit.arbeitsverhaeltnisDto.leistungserbringer =
                entries[position]
            leistungserbringerlistPopupWindow.dismiss()
        }
        return createListPopupWindow(it, entries, onItemClickListener)
    }

    private fun createListPopupWindowLeistungsnehmer(it: AppCompatActivity, entries: List<String>): ListPopupWindow {
        val onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            editArbeitsverhaeltnisViewModel.arbeitsverhaeltnisToEdit.arbeitsverhaeltnisDto.leistungsnehmer =
                entries[position]
            leistungsnehmerlistPopupWindow.dismiss()
        }
        return createListPopupWindow(it, entries, onItemClickListener)
    }

    private fun createListPopupWindowKategorie(it: AppCompatActivity, entries: List<String>): ListPopupWindow {
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


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_arbeitsverhaeltnis_details, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete_arbeitsverhaeltnis -> {
                sharedTeamupEventViewModel.deleteArbeitsverhaeltnis()
                ZeiterfassungNavigation.getNavigation(findNavController()).fromDetailsTouebersicht()
            }
            R.id.action_edit_arbeitsverhaeltnis -> {
                editable.set(true)
                confirmButtonVisibility.set(View.VISIBLE)
            }
        }
        return true
    }



    override fun onStop() {
        super.onStop()
        updateArbeitsverhaeltnisDisposable?.dispose()
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

    fun saveChanges() {
        updateArbeitsverhaeltnisDisposable = editArbeitsverhaeltnisViewModel.updateArbeitsverhaeltnis()//
            .observeOn(AndroidSchedulers.mainThread())//
            .subscribe(Consumer<TeamupEvent> {
                ZeiterfassungNavigation.getNavigation(findNavController()).fromDetailsTouebersicht()
            })
    }

}
