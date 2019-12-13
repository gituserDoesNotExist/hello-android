package com.example.helloandroid.timerecording.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.endOfMonth
import com.example.helloandroid.startOfMonth
import com.example.helloandroid.timerecording.TeamupEvents
import com.example.helloandroid.timerecording.repository.ZeiterfassungRepository
import io.reactivex.disposables.Disposable
import org.threeten.bp.LocalDate.now

class ArbeitsverhaeltnisUebersichtViewModel(private val zeiterfassungRepository: ZeiterfassungRepository)//
    : ViewModel() {

    val teamupEvents = MutableLiveData<TeamupEvents>()
    private var fetchArbeitsverhaeltnisseDisposable: Disposable? = null
    val arbeitsverhaeltnisDTO = ArbeitsverhaeltnisUebersichtDTO(now().startOfMonth(), now().endOfMonth())


    fun loadArbeitsverhaeltnisse() {
        val startDate = arbeitsverhaeltnisDTO.startDate
        val endDate = arbeitsverhaeltnisDTO.endDate
        fetchArbeitsverhaeltnisseDisposable =
            zeiterfassungRepository.fetchArbeitsverhaeltnisseFromRemote(startDate, endDate)//
                .subscribe { verhaeltnisse -> teamupEvents.postValue(verhaeltnisse) }
    }


    override fun onCleared() {
        super.onCleared()
        fetchArbeitsverhaeltnisseDisposable?.dispose()
    }
}