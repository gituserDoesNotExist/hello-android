package com.example.helloandroid.timerecording.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.helloandroid.DatabaseAsyncTask
import com.example.helloandroid.R
import com.example.helloandroid.databinding.FragmentAppConfigurationBinding
import com.example.helloandroid.view.HelloSpinnerAdapter
import io.reactivex.android.schedulers.AndroidSchedulers


class AppConfigurationFragment : Fragment() {

    private lateinit var appConfigurationViewModel: AppConfigurationViewModel
    private var fragmentInterfactionListener: OnFragmentInteractionListener? = null
    val configDto = ConfigDTO()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentAppConfigurationBinding.inflate(inflater, container, false)
        binding.configDto = configDto
        binding.fragmentInteractionListener = fragmentInterfactionListener
        fragmentInterfactionListener?.setFragmentTitle("Konfiguration")
        initializeViewModel()

        val spinner = binding.root.findViewById<Spinner>(R.id.spinner_app_user)
        activity?.let { fragmentActivity ->
            appConfigurationViewModel.downloadRemoteConfig()//
                .observeOn(AndroidSchedulers.mainThread())//
                .subscribe { config ->
                    createSpinnerAdapter(spinner, fragmentActivity, config)
                }
            appConfigurationViewModel.calendarConfig.observe(this, Observer { config ->
                configDto.appUser = config.appUser
            })
        }

        return binding.root
    }

    private fun createSpinnerAdapter(spinner: Spinner, fragmentActivity: FragmentActivity,
                                     config: CalendarConfiguration) {
        spinner.adapter = HelloSpinnerAdapter(fragmentActivity, config.participants)
        spinner.onItemSelectedListener = itemSelectedListener()
    }

    private fun itemSelectedListener(): AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val participant = appConfigurationViewModel.findParticipantByPosition(position)
                DatabaseAsyncTask(backgroundOperationFunc = appConfigurationViewModel::saveAppUser).execute(participant)
            }

        }
    }

    private fun initializeViewModel() {
        activity?.let {
            appConfigurationViewModel = ViewModelProviders.of(it, ConfigViewModelFactory(it.application))
                .get(AppConfigurationViewModel::class.java)
        }
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
