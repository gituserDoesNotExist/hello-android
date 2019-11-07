package com.example.helloandroid

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    lateinit var counterLiveData: MutableLiveData<Int>

    init {
        counterLiveData = MutableLiveData<Int>()
        counterLiveData.value = 0
    }

    fun increaseCounterBy1() {
        val newCounterValue: Int? = counterLiveData.value?.plus(1)
        counterLiveData.postValue(newCounterValue)
    }
}