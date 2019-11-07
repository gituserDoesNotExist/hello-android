package com.example.helloandroid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.helloandroid.persistence.VerzichtDao

class VerzichtDetailsViewModelFactory(verzichtDao: VerzichtDao) : ViewModelProvider.Factory {

    private val verzichtDao: VerzichtDao = verzichtDao

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val newInstance: T = modelClass.getConstructor(VerzichtDao::class.java).newInstance()
        return newInstance
    }

}