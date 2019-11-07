package com.example.helloandroid

import androidx.lifecycle.ViewModel
import com.example.helloandroid.persistence.Verzicht
import com.example.helloandroid.persistence.VerzichtDao

class VerzichtDetailsViewModel(verzichtDao: VerzichtDao) : ViewModel() {

    private val verzichtDao: VerzichtDao = verzichtDao
    var currentVerzichtId: Long = 0

}