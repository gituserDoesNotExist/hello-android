package com.example.helloandroid.finances.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.helloandroid.databinding.AddPostenDialogBinding
import com.example.helloandroid.finances.Posten

class AddPostenDialog : DialogFragment() {

    var postenName: String = ""
    private lateinit var postenUebersichtViewModel: PostenUebersichtViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = AddPostenDialogBinding.inflate(inflater, container, false)
        binding.addPostenDialog = this
        activity?.let {
            postenUebersichtViewModel =
                ViewModelProviders.of(it, FinancesViewModelFactory(it)).get(PostenUebersichtViewModel::class.java)
        }


        return binding.root
    }

    fun savePostenAndCloseDialog() {
        postenUebersichtViewModel.savePosten(Posten(postenName))
        closeDialog()
    }

    fun closeDialog() {
        this.dismiss()
    }

}