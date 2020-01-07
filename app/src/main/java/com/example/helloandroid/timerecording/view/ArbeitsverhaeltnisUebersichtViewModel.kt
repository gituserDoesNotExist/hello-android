package com.example.helloandroid.timerecording.view

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.timerecording.TeamupEvents
import com.example.helloandroid.timerecording.repository.ZeiterfassungRepository
import io.reactivex.disposables.Disposable
import java.math.BigDecimal

class ArbeitsverhaeltnisUebersichtViewModel(private val zeiterfassungRepository: ZeiterfassungRepository)//
    : ViewModel() {

    val teamupEvents = MutableLiveData<TeamupEvents>()
    private var fetchArbeitsverhaeltnisseDisposable: Disposable? = null
    val showProgressbar = ObservableBoolean().apply { this.set(true) }
    val gesamtstunden = ObservableField<BigDecimal>().apply { this.set(BigDecimal.ZERO) }


    fun loadArbeitsverhaeltnisse(suchkriterien: Suchkriterien) {
        showProgressbar.set(true)
        fetchArbeitsverhaeltnisseDisposable =
            zeiterfassungRepository.fetchArbeitsverhaeltnisseFromRemote(suchkriterien)//
                .subscribe { events ->
                    gesamtstunden.set(events.gesamtstunden())
                    teamupEvents.postValue(events)
                    showProgressbar.set(false)
                }
    }


    override fun onCleared() {
        super.onCleared()
        fetchArbeitsverhaeltnisseDisposable?.dispose()
    }
}