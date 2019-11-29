package com.example.helloandroid.timerecording.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.helloandroid.timerecording.TeamupServiceGenerator

class ZeiterfassungViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val zeiterfassungRepository = ZeiterfassungRepository()

        return modelClass.getConstructor(ZeiterfassungRepository::class.java).newInstance(zeiterfassungRepository)
    }

}