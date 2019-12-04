package com.example.helloandroid.timerecording.view

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
import com.example.helloandroid.DatabaseAsyncTask
import com.example.helloandroid.R
import com.example.helloandroid.databinding.FragmentAppConfigurationBinding
import io.reactivex.android.schedulers.AndroidSchedulers


class AppConfigurationFragment : Fragment() {

    private lateinit var appConfigurationViewModel: AppConfigurationViewModel
    private var fragmentInterfactionListener: OnFragmentInteractionListener? = null
    private val configDto = ConfigDTO()
    private lateinit var participantsListPopupWindow: ListPopupWindow

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentAppConfigurationBinding.inflate(inflater, container, false)
        binding.configDto = configDto
        binding.fragmentInteractionListener = fragmentInterfactionListener
        binding.appConfigurationFragment = this
        fragmentInterfactionListener?.setFragmentTitle("Konfiguration")
        initializeViewModel()

        activity?.let { frgmntActivity ->
            appConfigurationViewModel.downloadRemoteConfig()//
                .observeOn(AndroidSchedulers.mainThread())//
                .subscribe { config ->
                    participantsListPopupWindow = createParticipantsListPopUpWindow(frgmntActivity, config.participants)
                    configDto.savedAppUser = config.participants[0]
                }
            appConfigurationViewModel.calendarConfig.observe(this, Observer { config ->
                configDto.savedAppUser = config.appUser
            })
        }

        return binding.root
    }

    private fun createParticipantsListPopUpWindow(it: FragmentActivity, entries: List<String>): ListPopupWindow {
        val listener = AdapterView.OnItemClickListener { _, _, position, _ ->
            configDto.selectedAppUser = entries[position]
            DatabaseAsyncTask(appConfigurationViewModel::saveAppUser).execute(configDto.selectedAppUser)
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
            appConfigurationViewModel = ViewModelProviders.of(it, ConfigViewModelFactory(it.application))
                .get(AppConfigurationViewModel::class.java)
        }
    }

    fun openParticipantsListPopupWindow(editTextView: View) {
        participantsListPopupWindow.apply { this.anchorView = editTextView}.show()
    }

    interface OnFragmentInteractionListener {
        fun setFragmentTitle(value: String)
        fun exitAppConfigFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            fragmentInterfactionListener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentInterfactionListener = null
    }

}
