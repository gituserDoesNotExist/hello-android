package com.example.helloandroid.finances.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.DialogFragment
import com.example.helloandroid.R
import com.example.helloandroid.databinding.AddPostenDialogBinding
import com.example.helloandroid.finances.Posten
import com.example.helloandroid.finances.persistence.PostenEntity
import com.example.helloandroid.finances.persistence.PostenService
import com.example.helloandroid.persistence.AppDatabase

class AddPostenDialog : DialogFragment() {

    private lateinit var btnNewPosten: ImageButton
    var postenName: String = ""
    private lateinit var postenService: PostenService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = AddPostenDialogBinding.inflate(inflater, container, false)
        binding.addPostenDialog = this
        activity?.let {
            postenService = PostenService(AppDatabase.getDb(it.application).postenDao())
        }


        return binding.root
    }

    fun savePostenAndCloseDialog() {
        postenService.savePosten(Posten(postenName))
        closeDialog()
    }

    fun closeDialog() {
        this.dismiss()
    }

}