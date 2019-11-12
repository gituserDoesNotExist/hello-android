package com.example.helloandroid.fragmentsht

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FunWithFragmentsSharedViewModel : ViewModel() {

    var someValue = MutableLiveData<String>()

}