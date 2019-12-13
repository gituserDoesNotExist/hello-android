package com.example.helloandroid


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.helloandroid.databinding.FragmentWelcomeZeiterfassungBinding

class WelcomeZeiterfassungFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentWelcomeZeiterfassungBinding.inflate(inflater, container, false)
        binding.welcomeZeiterfassungFragment = this

        activity?.let {
            it.title = "Welcome"
        }


        return binding.root
    }

    fun openConfiguration(view: View) {
        val action =
            WelcomeZeiterfassungFragmentDirections.actionWelcomeZeiterfassungFragmentToAppConfigurationFragment()
        view.findNavController().navigate(action)
    }


}
