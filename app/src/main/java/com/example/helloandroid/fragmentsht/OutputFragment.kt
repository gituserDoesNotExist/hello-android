package com.example.helloandroid.fragmentsht

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.helloandroid.R


class OutputFragment : Fragment() {

    private lateinit var sharedViewModel: FunWithFragmentsSharedViewModel
    private lateinit var outputText: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_output, container, false)
        outputText = rootView.findViewById(R.id.frgmtsht_output)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.let {
            sharedViewModel = ViewModelProviders.of(it).get(FunWithFragmentsSharedViewModel::class.java)
            sharedViewModel.someValue.observe(this, Observer { inputText ->
                outputText.text = inputText
            })
        }
    }
}
