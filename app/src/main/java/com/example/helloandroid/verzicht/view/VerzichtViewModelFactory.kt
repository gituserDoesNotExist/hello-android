package com.example.helloandroid.verzicht.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.helloandroid.persistence.AppDatabase
import com.example.helloandroid.verzicht.persistence.VerzichtRepository

class VerzichtViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val verzichtDao = AppDatabase.getDb(context).verzichtDao()
        val verzichtRepository = VerzichtRepository(verzichtDao)
        return modelClass.getConstructor(VerzichtRepository::class.java).newInstance(verzichtRepository)
    }

}