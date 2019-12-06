package com.example.helloandroid.timerecording.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.helloandroid.persistence.AppDatabase
import com.example.helloandroid.timerecording.repository.AppConfigurationRepository
import com.example.helloandroid.timerecording.repository.ArbeitsverhaeltnisRepository

class AddArbeitsverhaeltnisViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val arbeitsverhaeltnisRepository = ArbeitsverhaeltnisRepository()
        val configRepository = AppConfigurationRepository(AppDatabase.getDb(context).calendarConfigurationDao())

        return modelClass.getConstructor(ArbeitsverhaeltnisRepository::class.java, AppConfigurationRepository::class.java)
            .newInstance(arbeitsverhaeltnisRepository,configRepository)
    }

}