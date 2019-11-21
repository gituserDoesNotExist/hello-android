package com.example.helloandroid.databinding

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DatabindingViewModel : ViewModel() {

    val nameObservableField = ObservableField<String>("I am a value from an ObservableField")
//    val nameLiveData = MutableLiveData<String>()

//    init {
//        nameLiveData.postValue("I am a value from LiveData")
//    }

}