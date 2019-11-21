package com.example.helloandroid.finances.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.finances.PostenStub
import com.example.helloandroid.finances.persistence.FinancesRepository
import java.math.BigDecimal
import java.util.*

class PostenUebersichtViewModel(private val financesRepository: FinancesRepository) : ViewModel() {

    val postenStubs: LiveData<List<PostenStub>> = financesRepository.findPostenStubs()

    fun calculateGesamtausgaben(): BigDecimal {
        val stubs = postenStubs.value ?: Collections.emptyList()
        return stubs.stream().map(PostenStub::gesamtausgabenForPosten).reduce(BigDecimal::add).orElse(BigDecimal.ZERO)
    }

    fun deletePosten(posten: PostenStub) {
        financesRepository.deletePosten(posten.postenId)
    }


}