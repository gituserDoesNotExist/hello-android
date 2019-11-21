package com.example.helloandroid.finances.view

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.helloandroid.finances.Ausgabe
import com.example.helloandroid.finances.Posten
import com.example.helloandroid.finances.PostenStub
import com.example.helloandroid.finances.persistence.AusgabeEntity
import com.example.helloandroid.finances.persistence.FinancesRepository
import com.example.helloandroid.finances.persistence.PostenEntity
import java.math.BigDecimal
import java.util.function.BinaryOperator

class PostenUebersichtViewModel(financesRepository: FinancesRepository) : ViewModel() {

    private val financesRepository = financesRepository

    lateinit var postenStubs: LiveData<List<PostenStub>>
        private set

    fun calculateGesamtausgaben(): LiveData<BigDecimal> {
        return Transformations.map(postenStubs) { input ->
            input.stream().map(PostenStub::gesamtausgabenForPosten).reduce(BigDecimal::add).orElse(BigDecimal.ZERO)
        }
    }

    fun findPostenStubs() : LiveData<List<PostenStub>> {
        postenStubs = financesRepository.findPostenStubs()
        return postenStubs
    }



}