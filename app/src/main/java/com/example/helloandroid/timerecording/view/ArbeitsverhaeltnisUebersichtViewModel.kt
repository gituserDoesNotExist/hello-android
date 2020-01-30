package com.example.helloandroid.timerecording.view

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.timerecording.Arbeitseinsaetze
import com.example.helloandroid.timerecording.repository.ZeiterfassungRepository
import io.reactivex.disposables.Disposable
import java.math.BigDecimal

class ArbeitsverhaeltnisUebersichtViewModel(private val zeiterfassungRepository: ZeiterfassungRepository)//
    : ViewModel() {

    val arbeitseinsaetze = MutableLiveData<Arbeitseinsaetze>()
    private var fetchArbeitsverhaeltnisseDisposable: Disposable? = null
    val showProgressbar = ObservableBoolean(true)
    val gesamtkosten = ObservableField<BigDecimal>().apply { this.set(BigDecimal.ZERO) }

    fun loadArbeitsverhaeltnisse(suchkriterien: Suchkriterien, showProgessIndicator: Boolean) {
        showProgressbar.set(showProgessIndicator)
        fetchArbeitsverhaeltnisseDisposable = zeiterfassungRepository.fetchArbeitseinsaetzeFromRemote(suchkriterien)//
            .subscribe { arbeitseinsaetze ->
                gesamtkosten.set(arbeitseinsaetze.gesamtkosten())
                this.arbeitseinsaetze.postValue(arbeitseinsaetze)
                showProgressbar.set(false)
            }
    }


    override fun onCleared() {
        super.onCleared()
        fetchArbeitsverhaeltnisseDisposable?.dispose()
    }
}