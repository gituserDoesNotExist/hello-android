package com.example.helloandroid.timerecording.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.ListPopupWindow
import androidx.databinding.ObservableBoolean
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helloandroid.BaseActivity
import com.example.helloandroid.DatabaseAsyncTask
import com.example.helloandroid.R
import com.example.helloandroid.databinding.FragmentAppConfigurationBinding
import com.example.helloandroid.timerecording.config.Person
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable


class AppConfigurationFragment : Fragment() {

    private lateinit var appConfigurationViewModel: AppConfigurationViewModel
    private lateinit var participantsListPopupWindow: ListPopupWindow

    var showProgressbar = ObservableBoolean().apply { set(true) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentAppConfigurationBinding.inflate(inflater, container, false)

        val rootView = binding.root
        (activity as? BaseActivity)?.let { activity ->
            appConfigurationViewModel = activity.provideViewModel(AppConfigurationViewModel::class.java)
            configureNetworkErrorHandling(activity, rootView)
            activity.supportActionBar?.title = resources.getString(R.string.title_fragment_configuration)
            downloadRemoteConfig(activity, rootView)

        }

        binding.viewModel = appConfigurationViewModel
        binding.appConfigurationFragment = this

        return rootView
    }

    private fun configureNetworkErrorHandling(activity: BaseActivity, rootView: View) {
        activity.hideProgressbarCallback = {
            showProgressbar.set(false)
        }
        activity.reloadCallback = {
            downloadRemoteConfig(activity, rootView)
        }
    }

    private fun downloadRemoteConfig(activity: BaseActivity, view: View): Disposable? {
        return appConfigurationViewModel.downloadRemoteConfig()//
            .observeOn(AndroidSchedulers.mainThread())//
            .subscribe { config ->
                showProgressbar.set(false)
                participantsListPopupWindow = createParticipantsListPopUpWindow(activity, config.teilnehmer)
                appConfigurationViewModel.calendarConfig.observe(this, Observer {
                    appConfigurationViewModel.savedAppUser.set(it.appUser.name)
                    addStundensaetzeRecyclerView(view, activity, config)
                    addKostenProStueckRecyclerView(view, activity, config)
                })
            }
    }


    private fun createParticipantsListPopUpWindow(it: FragmentActivity, entries: List<Person>): ListPopupWindow {
        val names = entries.map { it.name }
        val listener = AdapterView.OnItemClickListener { _, _, position, _ ->
            appConfigurationViewModel.selectedAppUser = entries[position]
            DatabaseAsyncTask(appConfigurationViewModel::saveAppUser).execute(appConfigurationViewModel.selectedAppUser)
            participantsListPopupWindow.dismiss()
        }

        return ListPopupWindow(it).apply {
            this.setAdapter(ArrayAdapter(it, R.layout.item_list_popup_window, names))
            this.width = 400
            this.height = ViewGroup.LayoutParams.WRAP_CONTENT
            this.isModal = true
            this.setOnItemClickListener(listener)
        }
    }


    private fun addStundensaetzeRecyclerView(root: View, activity: BaseActivity, config: CalendarConfiguration) {
        root.findViewById<RecyclerView>(R.id.recycler_view_stundensaetze).apply {
            this.adapter = StundensaetzeRecyclerViewAdapter(config)
            this.layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun addKostenProStueckRecyclerView(root: View, activity: BaseActivity, config: CalendarConfiguration) {
        root.findViewById<RecyclerView>(R.id.recycler_view_kosten_pro_stueck).apply {
            this.adapter = StueckkostenRecyclerViewAdapter(config.produkte)
            this.layoutManager = LinearLayoutManager(activity)
        }
    }

    fun openParticipantsListPopupWindow(editTextView: View) {
        participantsListPopupWindow.apply { this.anchorView = editTextView }.show()
    }

    fun exitAppConfigFragment() {
        ZeiterfassungNavigation.getNavigation(findNavController()).toUebersicht()
    }

}
