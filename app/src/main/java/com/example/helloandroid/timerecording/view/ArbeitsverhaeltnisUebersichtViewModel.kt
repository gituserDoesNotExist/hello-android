package com.example.helloandroid.timerecording.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.timerecording.TeamupEvents
import com.example.helloandroid.timerecording.repository.ZeiterfassungRepository
import io.reactivex.disposables.Disposable

class ArbeitsverhaeltnisUebersichtViewModel(private val zeiterfassungRepository: ZeiterfassungRepository)//
    : ViewModel() {

    val teamupEvents = MutableLiveData<TeamupEvents>()
    private var fetchArbeitsverhaeltnisseDisposable: Disposable? = null


    fun loadArbeitsverhaeltnisse(arbeitsverhaeltnisDto: ArbeitsverhaeltnisUebersichtDTO) {
        val startDate = arbeitsverhaeltnisDto.startDate
        val endDate = arbeitsverhaeltnisDto.endDate
        fetchArbeitsverhaeltnisseDisposable =
            zeiterfassungRepository.fetchArbeitsverhaeltnisseFromRemote(startDate, endDate)//
                .subscribe { verhaeltnisse -> teamupEvents.postValue(verhaeltnisse) }
    }



    override fun onCleared() {
        super.onCleared()
        fetchArbeitsverhaeltnisseDisposable?.dispose()
    }
}