package com.example.helloandroid.verzicht.view

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

    fun saveVerzicht(verzichtDto: VerzichtDTO): Long {
        return verzichtRepository.saveVerzicht(Verzicht(verzichtDto.name))
    }

    fun deleteVerzicht(verzicht: Verzicht) {
        verzichtRepository.deleteVerzicht(verzicht)
    }


}