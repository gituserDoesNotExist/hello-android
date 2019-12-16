package com.example.helloandroid.timerecording.view

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
import androidx.navigation.fragment.findNavController
import com.example.helloandroid.DatabaseAsyncTask
import com.example.helloandroid.R
import com.example.helloandroid.databinding.FragmentAppConfigurationBinding
import io.reactivex.android.schedulers.AndroidSchedulers


class AppConfigurationFragment : Fragment() {

    private lateinit var appConfigurationViewModel: AppConfigurationViewModel
    private lateinit var participantsListPopupWindow: ListPopupWindow

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentAppConfigurationBinding.inflate(inflater, container, false)
        initializeViewModel()

        activity?.let { frgmntActivity ->
            frgmntActivity.title = resources.getString(R.string.title_fragment_configuration)
            appConfigurationViewModel.downloadRemoteConfig()//
                .observeOn(AndroidSchedulers.mainThread())//
                .subscribe { config ->
                    participantsListPopupWindow = createParticipantsListPopUpWindow(frgmntActivity, config.participants)
                    appConfigurationViewModel.configDto.savedAppUser = config.participants[0]
                }
            appConfigurationViewModel.calendarConfig.observe(this, Observer { config ->
                appConfigurationViewModel.configDto.savedAppUser = config.appUser
            })
        }

        binding.configDto = appConfigurationViewModel.configDto
        binding.appConfigurationFragment = this

        return binding.root
    }

    private fun createParticipantsListPopUpWindow(it: FragmentActivity, entries: List<String>): ListPopupWindow {
        val listener = AdapterView.OnItemClickListener { _, _, position, _ ->
            appConfigurationViewModel.configDto.selectedAppUser = entries[position]
            DatabaseAsyncTask(appConfigurationViewModel::saveAppUser).execute(
                appConfigurationViewModel.configDto.selectedAppUser)
            participantsListPopupWindow.dismiss()
        }

        return ListPopupWindow(it).apply {
            this.setAdapter(ArrayAdapter(it, R.layout.item_list_popup_window, entries))
            this.width = 400
            this.height = ViewGroup.LayoutParams.WRAP_CONTENT
            this.isModal = true
            this.setOnItemClickListener(listener)
        }
    }


    private fun initializeViewModel() {
        activity?.let {
            appConfigurationViewModel = ViewModelProviders.of(it, ZeiterfassungViewModelFactory(it.application))
                .get(AppConfigurationViewModel::class.java)
        }
    }

    fun openParticipantsListPopupWindow(editTextView: View) {
        participantsListPopupWindow.apply { this.anchorView = editTextView }.show()
    }

    fun exitAppConfigFragment() {
        val action =
            AppConfigurationFragmentDirections.actionAppConfigurationFragmentToArbeitsverhaltnisUebersichtFragment()
        findNavController().navigate(action)
    }

}
