package com.example.helloandroid.timerecording.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.timerecording.Arbeitsverhaeltnisse
import com.example.helloandroid.timerecording.repository.ArbeitsverhaeltnisRepository
import io.reactivex.disposables.Disposable

class ArbeitsverhaeltnisUebersichtViewModel(private val arbeitsverhaeltnisRepository: ArbeitsverhaeltnisRepository)//
    : ViewModel() {

    val arbeitsverhaeltnisse = MutableLiveData<Arbeitsverhaeltnisse>()
    private var fetchArbeitsverhaeltnisseDisposable: Disposable? = null


    fun loadArbeitsverhaeltnisse(arbeitsverhaeltnisDto: ArbeitsverhaeltnisUebersichtDTO) {
        val startDate = arbeitsverhaeltnisDto.startDate
        val endDate = arbeitsverhaeltnisDto.endDate
        fetchArbeitsverhaeltnisseDisposable =
            arbeitsverhaeltnisRepository.fetchArbeitsverhaeltnisseFromRemote(startDate, endDate)//
                .subscribe { verhaeltnisse -> arbeitsverhaeltnisse.postValue(verhaeltnisse) }
    }



    override fun onCleared() {
        super.onCleared()
        fetchArbeitsverhaeltnisseDisposable?.dispose()
    }
}