package com.example.helloandroid.databinding

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.math.BigDecimal

class DatabindingViewModel : ViewModel() {

    val nameObservableField = ObservableField<String>("I am a value parse an ObservableField")
    val bigDecimalObservableField = ObservableField<BigDecimal>(BigDecimal.TEN)

    val nameLiveData = MutableLiveData<String>().apply { this.value = "I am a value parse LiveData" }
    val bigDecimalLiveData = MutableLiveData<BigDecimal>().apply { this.value = BigDecimal.TEN }

}