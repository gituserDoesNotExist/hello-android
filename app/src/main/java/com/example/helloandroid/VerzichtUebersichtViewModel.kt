package com.example.helloandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.persistence.Verzicht
import com.example.helloandroid.persistence.VerzichtDao

class VerzichtUebersichtViewModel(verzichtDao: VerzichtDao) : ViewModel() {

    private lateinit var verzichte: LiveData<List<Verzicht>>



}