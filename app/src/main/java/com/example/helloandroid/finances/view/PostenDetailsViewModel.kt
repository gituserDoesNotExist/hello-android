package com.example.helloandroid.finances.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.finances.Ausgabe
import com.example.helloandroid.finances.AusgabeMapper
import com.example.helloandroid.finances.Posten
import com.example.helloandroid.finances.PostenStub
import com.example.helloandroid.finances.persistence.FinancesRepository
import com.example.helloandroid.view.LiveDataNotInitializedException

class PostenDetailsViewModel constructor(private val financesRepository: FinancesRepository) : ViewModel() {

    private val ausgabeMapper: AusgabeMapper = AusgabeMapper()
    lateinit var selectedPosten: LiveData<Posten>

    var ausgabeDto: AusgabeDTO = AusgabeDTO()

    fun initializePosten(postenId: Long) {
        selectedPosten = financesRepository.findPostenById(postenId)
    }

    fun saveAusgabeForPosten() {
        val p: Posten = selectedPosten.value ?: throw LiveDataNotInitializedException(
            "Posten wurde nicht initialisiert")
        val ausgabe = ausgabeMapper.fromDTO(ausgabeDto)
        financesRepository.saveAusgabeForPosten(ausgabe,p.id)
    }

    fun deletePosten(posten: PostenStub) {
        financesRepository.deletePosten(posten.postenId)
    }

    fun deleteAusgabe(ausgabe: Ausgabe) {
        financesRepository.deleteAusgabe(ausgabe)
    }

    fun resetAusgabeDto() {
        this.ausgabeDto = AusgabeDTO()
    }
}
