package com.example.helloandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.helloandroid.persistence.Verzicht
import com.example.helloandroid.persistence.VerzichtDao
import com.example.helloandroid.persistence.VerzichtRepository

class VerzichtDetailsViewModel(verzichtRepository: VerzichtRepository) : ViewModel() {

    private val verzichtRepository = verzichtRepository
    private lateinit var verzichtLiveData: LiveData<Verzicht>


    fun findByVerzichtName(name: String) {
        verzichtLiveData = verzichtRepository.findByVerzichtName(name)
    }

    fun increaseVerzichtDurationByOneDay() {
        increaseAndUpdate(verzichtLiveData.value!!)
    }

    fun increaseAndUpdate(verzicht: Verzicht) {
        verzicht.days = verzicht.days.plus(1)
        verzichtRepository.updateVerzicht(verzicht)
    }

    fun getCurrentVerzicht(): LiveData<Verzicht> {
        return verzichtLiveData
    }

}