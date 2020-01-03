package com.example.helloandroid.finances.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.helloandroid.finances.Posten
import com.example.helloandroid.finances.PostenStub
import com.example.helloandroid.finances.persistence.FinancesRepository
import java.math.BigDecimal

class PostenUebersichtViewModel(private val financesRepository: FinancesRepository) : ViewModel() {

    val postenStubs: LiveData<List<PostenStub>> = financesRepository.findPostenStubs()

    var gesamtausgaben: LiveData<BigDecimal>? = Transformations.switchMap(postenStubs) {
        val total = it.stream().map(PostenStub::gesamtausgabenForPosten).reduce(BigDecimal::add).orElse(BigDecimal.ZERO)
        MutableLiveData<BigDecimal>().apply { this.value = total }
    }
        get() {
            if (field?.value == null) {
                return MutableLiveData<BigDecimal>().apply { this.value = BigDecimal.ZERO }
            }
            return field
        }


    fun savePosten(posten: Posten) {
        financesRepository.savePosten(posten)
    }


}