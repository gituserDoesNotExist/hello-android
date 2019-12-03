package com.example.helloandroid.timerecording.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.helloandroid.persistence.AppDatabase
import com.example.helloandroid.timerecording.repository.AppConfigurationRepository

class ConfigViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val repository = AppConfigurationRepository(
            AppDatabase.getDb(context).calendarConfigurationDao())

        return modelClass.getConstructor(AppConfigurationRepository::class.java).newInstance(repository)
    }

}