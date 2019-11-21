package com.example.helloandroid.databinding


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.helloandroid.R

class DatabindingFragment : Fragment() {

    private val viewModel: DatabindingViewModel = DatabindingViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentDatabindingBindingImpl.inflate(inflater, container, false)
        binding.dataBindingViewModel = viewModel

        return binding.root
    }


}
