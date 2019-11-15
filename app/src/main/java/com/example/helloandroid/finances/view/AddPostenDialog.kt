package com.example.helloandroid.finances.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.DialogFragment
import com.example.helloandroid.R
import com.example.helloandroid.finances.persistence.PostenEntity
import com.example.helloandroid.finances.persistence.PostenService
import com.example.helloandroid.persistence.AppDatabase

class AddPostenDialog : DialogFragment() {

    private lateinit var postenName: EditText
    private lateinit var btnNewPosten: ImageButton
    private lateinit var postenService: PostenService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.add_posten_dialog, container, false)

        activity?.let {
            postenService = PostenService(AppDatabase.getDb(it.application).postenDao())
        }

        postenName = rootView.findViewById(R.id.input_new_posten_name)
        btnNewPosten = rootView.findViewById(R.id.btn_new_posten)
        btnNewPosten.setOnClickListener {
            postenService.savePosten(PostenEntity(postenName.text.toString()))
            dismiss()
        }

        return rootView
    }

}