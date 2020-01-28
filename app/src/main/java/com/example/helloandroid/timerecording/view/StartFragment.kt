package com.example.helloandroid.timerecording.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.helloandroid.BaseActivity
import com.example.helloandroid.R

class StartFragment : Fragment() {

    private lateinit var viewModel: StartZeiterfassungViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as? BaseActivity)?.let {
            initViewModel(it)
            viewModel.existsConfiguration.observe(this, Observer { existsConfig ->
                if (existsConfig) {
                    ZeiterfassungNavigation.getNavigation(findNavController()).toUebersicht()
                } else {
                    ZeiterfassungNavigation.getNavigation(findNavController()).toConfig()
                }
            })
        }
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    private fun initViewModel(activity: BaseActivity) {
        viewModel = ViewModelProviders.of(activity, ZeiterfassungViewModelFactory(activity.applicationContext))
            .get(StartZeiterfassungViewModel::class.java)
    }


}