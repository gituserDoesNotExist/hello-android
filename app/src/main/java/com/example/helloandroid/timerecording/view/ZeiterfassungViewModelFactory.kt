package com.example.helloandroid.timerecording.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.helloandroid.persistence.AppDatabase
import com.example.helloandroid.timerecording.repository.AppConfigurationRepository
import com.example.helloandroid.timerecording.repository.ArbeitsverhaeltnisRepository
import com.example.helloandroid.timerecording.repository.ZeiterfassungRepository

class ZeiterfassungViewModelFactory(private val context: Context) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val configRepository = AppConfigurationRepository(AppDatabase.getDb(context).calendarConfigurationDao())
        val zeiterfassungRepository = ZeiterfassungRepository(configRepository, ArbeitsverhaeltnisRepository())

        return modelClass.getConstructor(ZeiterfassungRepository::class.java).newInstance(zeiterfassungRepository)
    }


}