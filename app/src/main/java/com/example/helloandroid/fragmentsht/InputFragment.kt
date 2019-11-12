package com.example.helloandroid.fragmentsht


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher

import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders

import com.example.helloandroid.R


class InputFragment : Fragment() {

    private lateinit var sharedViewModel: FunWithFragmentsSharedViewModel
    private lateinit var etInput: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_input, container, false)
        etInput = rootView.findViewById(R.id.frgmtsht_input)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.let { sharedViewModel = ViewModelProviders.of(it).get(FunWithFragmentsSharedViewModel::class.java) }

        this.etInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(txt: CharSequence?, start: Int, before: Int, count: Int) {
                txt?.let {
                    sharedViewModel.someValue.postValue(it.toString())
                }
            }
        })
    }
}
