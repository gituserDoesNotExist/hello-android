package com.example.helloandroid.fragmentsht


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.helloandroid.R

class TextFragment : Fragment() {

    private lateinit var androidOsView: TextView
    private lateinit var androidVersionView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_text, container, false)

        androidOsView = view.findViewById<TextView>(R.id.android_os)
        androidVersionView = view.findViewById<TextView>(R.id.android_version)

        return view
    }

    fun change(os: String, version: String) {
        androidOsView.text = os
        androidVersionView.text = version
    }


}
