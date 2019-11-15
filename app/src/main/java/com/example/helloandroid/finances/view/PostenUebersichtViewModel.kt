package com.example.helloandroid.finances.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.finances.Ausgabe
import com.example.helloandroid.finances.Posten
import com.example.helloandroid.finances.persistence.AusgabeEntity
import com.example.helloandroid.finances.persistence.FinancesRepository
import com.example.helloandroid.finances.persistence.PostenEntity
import java.math.BigDecimal

class PostenUebersichtViewModel(financesRepository: FinancesRepository) : ViewModel() {

    private val financesRepository = financesRepository
    lateinit var allPosten: LiveData<List<Posten>>
        private set

    fun calculateGesamtausgaben(): BigDecimal {
        val ausgaben = financesRepository.findAllAusgaben()
        return ausgaben.stream().map(Ausgabe::wert).reduce(BigDecimal::add).orElse(BigDecimal.ZERO)
    }

    fun initializeByFindingAllPosten() {
        allPosten = financesRepository.findAllPosten()
    }


}