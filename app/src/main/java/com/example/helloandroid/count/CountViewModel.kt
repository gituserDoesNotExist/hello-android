package com.example.helloandroid.count

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CountViewModel : ViewModel() {

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