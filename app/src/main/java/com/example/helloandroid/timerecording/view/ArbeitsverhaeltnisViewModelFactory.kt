package com.example.helloandroid.timerecording.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.helloandroid.timerecording.repository.ArbeitsverhaeltnisRepository

class ArbeitsverhaeltnisViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val arbeitsverhaeltnisRepository = ArbeitsverhaeltnisRepository()

        return modelClass.getConstructor(ArbeitsverhaeltnisRepository::class.java)
            .newInstance(arbeitsverhaeltnisRepository)
    }

}