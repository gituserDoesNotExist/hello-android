package com.example.helloandroid.databinding


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class DatabindingFragment : Fragment() {

    private val viewModel: DatabindingViewModel = DatabindingViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentDatabindingBindingImpl.inflate(inflater, container, false)
        binding.dataBindingViewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }


}
