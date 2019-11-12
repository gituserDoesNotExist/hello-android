package com.example.helloandroid.verzicht

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.verzicht.persistence.Verzicht
import com.example.helloandroid.verzicht.persistence.VerzichtRepository

class VerzichtUebersichtViewModel(verzichtRepository: VerzichtRepository) : ViewModel() {

    private val verzichtRepository = verzichtRepository
    lateinit var verzichteLiveData: LiveData<List<Verzicht>>
        private set

    fun initializeByLoadingAllVerzichte() {
        verzichteLiveData = verzichtRepository.findAllVerzichte()
    }

    fun findAllVerzichte() : List<Verzicht> {
        return verzichtRepository.findAllVerzichteTest()
    }

    fun saveVerzicht(verzicht: Verzicht) {
        verzichtRepository.saveVerzicht(verzicht)
    }

    fun deleteVerzicht(verzicht: Verzicht) {
        verzichtRepository.deleteVerzicht(verzicht)
    }


}