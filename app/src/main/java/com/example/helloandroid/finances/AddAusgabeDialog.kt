package com.example.helloandroid.finances

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.DialogFragment
import com.example.helloandroid.R
import com.example.helloandroid.finances.persistence.Posten
import com.example.helloandroid.finances.persistence.PostenService
import com.example.helloandroid.persistence.AppDatabase

class AddAusgabeDialog : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.add_ausgabe_dialog, container, false)

        return rootView
    }

}