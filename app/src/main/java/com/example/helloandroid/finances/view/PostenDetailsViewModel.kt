package com.example.helloandroid.finances.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.LiveDataNotInitializedException
import com.example.helloandroid.finances.Ausgabe
import com.example.helloandroid.finances.AusgabeMapper
import com.example.helloandroid.finances.Posten
import com.example.helloandroid.finances.persistence.FinancesRepository

class PostenDetailsViewModel constructor(financesRepository: FinancesRepository) : ViewModel() {

    private val financesRepository = financesRepository
    private val ausgabeMapper: AusgabeMapper = AusgabeMapper()
    lateinit var currentPosten: LiveData<Posten>

    fun initializePosten(postenId: Long) {
        currentPosten = financesRepository.findPostenById(postenId)
    }

    fun saveAusgabeForPosten(ausgabeDto: AusgabeDTO) {
        val p: Posten = currentPosten.value ?: throw LiveDataNotInitializedException("Posten wurde nicht initialisiert")
        val ausgabe = ausgabeMapper.fromDTO(ausgabeDto)
        financesRepository.saveAusgabeForPosten(ausgabe,p.id)
    }

    fun deleteAusgabe(ausgabe: Ausgabe) {
        financesRepository.deleteAusgabe(ausgabe)
    }
}
