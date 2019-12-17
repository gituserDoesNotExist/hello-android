package com.example.helloandroid


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.helloandroid.databinding.FragmentWelcomeZeiterfassungBinding
import com.example.helloandroid.timerecording.view.ZeiterfassungNavigation

class WelcomeZeiterfassungFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentWelcomeZeiterfassungBinding.inflate(inflater, container, false)
        binding.welcomeZeiterfassungFragment = this

        activity?.let {
            it.title = "Welcome"
        }


        return binding.root
    }

    fun openConfiguration() {
        ZeiterfassungNavigation.getNavigation(findNavController()).fromWelcomeToConfig()
    }

    fun openUebersicht() {
        ZeiterfassungNavigation.getNavigation(findNavController()).fromWelcomeToUebersicht()
    }


}
