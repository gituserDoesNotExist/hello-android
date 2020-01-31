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
import com.example.helloandroid.BaseActivity
import com.example.helloandroid.R
import com.example.helloandroid.timerecording.KeineAuswahl
import com.example.helloandroid.timerecording.config.Person


abstract class UpsertArbeitsverhaeltnisFragment : Fragment() {


    protected lateinit var appConfigurationViewModel: AppConfigurationViewModel

    protected lateinit var leistungserbringerListPopupWindow: ListPopupWindow
    protected lateinit var leistungsnehmerListPopupWindow: ListPopupWindow

    abstract fun fragmentTitle(): String

    abstract fun createRequiredListPopupWindows(activity: BaseActivity, calendarConfig: CalendarConfiguration)

    protected abstract fun initArbeitsverhaeltnisViewModel(activity: BaseActivity)

    protected abstract fun initArbeitsverhaeltnis(activity: BaseActivity)

    protected abstract fun createView(inflater: LayoutInflater, container: ViewGroup?,
                                      titlesAdapter: TitlesArrayAdapter?): View

    protected abstract fun prepareView(rootView: View, config: CalendarConfiguration)

    abstract fun validate(): Boolean

    abstract fun upsert()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as? BaseActivity)?.let { activity ->
            activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            activity.supportActionBar?.title = fragmentTitle()

            appConfigurationViewModel = activity.provideViewModel(AppConfigurationViewModel::class.java).apply {
                loadTitles()
            }
            setupArbeitsverhaeltnis(activity)

            appConfigurationViewModel.calendarConfig.observe(this, Observer {
                leistungserbringerListPopupWindow = createListPopupWindowLeistungserbringer(activity, it.teilnehmer)
                leistungsnehmerListPopupWindow = createListPopupWindowLeistungsnehmer(activity, it.teilnehmer)
                createRequiredListPopupWindows(activity, it)
            })
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var titlesAdapter : TitlesArrayAdapter? = null
        (activity as? BaseActivity)?.let {
            titlesAdapter = TitlesArrayAdapter(it.applicationContext, appConfigurationViewModel.titles)
        }
        val rootView = createView(inflater, container, titlesAdapter)
        appConfigurationViewModel.calendarConfig.observe(this, Observer {
            prepareView(rootView,it)
        })
        return rootView
    }

    private fun setupArbeitsverhaeltnis(activity: BaseActivity) {
        initArbeitsverhaeltnisViewModel(activity)
        initArbeitsverhaeltnis(activity)
    }

    private fun createListPopupWindowLeistungserbringer(it: AppCompatActivity, entries: List<Person>): ListPopupWindow {
        val dropdownEntries = mutableListOf(KeineAuswahl.value).apply { addAll(entries.map { it.name }) }
        return createListPopupWindow(it, dropdownEntries, updateLeistungserbringerListener(entries))
    }

    abstract fun updateLeistungserbringerListener(entries: List<Person>): (Int) -> Unit


    private fun createListPopupWindowLeistungsnehmer(it: AppCompatActivity, entries: List<Person>): ListPopupWindow {
        val dropdownEntries = mutableListOf(KeineAuswahl.value).apply { addAll(entries.map { it.name }) }
        return createListPopupWindow(it, dropdownEntries, updateLeistungsnehmerListener(entries))
    }

    abstract fun updateLeistungsnehmerListener(entries: List<Person>): (Int) -> Unit


    protected fun createListPopupWindow(it: FragmentActivity, entries: List<String>,
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


    fun openLeistungsnehmerListPopUp(editTextView: View) {
        leistungsnehmerListPopupWindow.apply { this.anchorView = editTextView }.show()
    }


    fun openLeistungserbringerListPopUp(editTextView: View) {
        leistungserbringerListPopupWindow.apply { this.anchorView = editTextView }.show()
    }


    fun addOrUpdate() {
        if (validate()) {
            upsert()
        } else {
            Toast.makeText(this.context, this.resources.getString(R.string.fehlende_daten), Toast.LENGTH_LONG).show()
        }
    }


}
