package com.example.helloandroid.timerecording.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.helloandroid.timerecording.repository.ZeiterfassungRepository

class ArbeitsverhaeltnisUebersichtViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val zeiterfassungRepository = ZeiterfassungRepository()

        return modelClass.getConstructor(ZeiterfassungRepository::class.java).newInstance(zeiterfassungRepository)
    }

}