package com.example.helloandroid.finances.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.finances.Ausgabe
import com.example.helloandroid.finances.AusgabeMapper
import com.example.helloandroid.finances.Posten
import com.example.helloandroid.finances.persistence.AusgabeEntity
import com.example.helloandroid.finances.persistence.FinancesRepository
import com.example.helloandroid.finances.persistence.PostenEntity

class PostenDetailsViewModel constructor(financesRepository: FinancesRepository) : ViewModel() {

    private val financesRepository = financesRepository
    lateinit var currentPosten: Posten
    private val ausgabeMapper: AusgabeMapper = AusgabeMapper()


    fun saveAusgabeForPosten(ausgabeDto: AusgabeDTO) {
        val ausgabe = ausgabeMapper.fromDTO(ausgabeDto)
        financesRepository.saveAusgabeForPosten(ausgabe,currentPosten.id)
    }

    fun findAusgabenForPosten(posten: Posten): LiveData<List<Ausgabe>> {
        return financesRepository.findAusgabenForPosten(posten.id)
    }


}
