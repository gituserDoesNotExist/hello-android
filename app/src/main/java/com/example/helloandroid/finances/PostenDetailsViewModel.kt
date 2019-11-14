package com.example.helloandroid.finances

import androidx.lifecycle.ViewModel
import com.example.helloandroid.finances.persistence.Ausgabe
import com.example.helloandroid.finances.persistence.FinancesRepository
import com.example.helloandroid.finances.persistence.Posten

class PostenDetailsViewModel constructor(financesRepository: FinancesRepository) : ViewModel() {

    private val financesRepository = financesRepository


    fun findAusgabenForPosten(posten: Posten): List<Ausgabe> {
        return financesRepository.findAusgabenForPosten(posten.id)
    }


}
