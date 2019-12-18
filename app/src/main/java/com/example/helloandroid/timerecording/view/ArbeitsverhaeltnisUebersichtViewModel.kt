package com.example.helloandroid.timerecording.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.endOfMonth
import com.example.helloandroid.startOfMonth
import com.example.helloandroid.timerecording.TeamupEvents
import com.example.helloandroid.timerecording.repository.ZeiterfassungRepository
import io.reactivex.disposables.Disposable
import org.threeten.bp.LocalDate.now
import ru.gildor.databinding.observables.NonNullObservable

class ArbeitsverhaeltnisUebersichtViewModel(private val zeiterfassungRepository: ZeiterfassungRepository)//
    : ViewModel() {

    val teamupEvents = MutableLiveData<TeamupEvents>()
    private var fetchArbeitsverhaeltnisseDisposable: Disposable? = null

    val startDate = NonNullObservable(now().startOfMonth())
    val endDate = NonNullObservable(now().endOfMonth())


    fun loadArbeitsverhaeltnisse() {
        fetchArbeitsverhaeltnisseDisposable =
            zeiterfassungRepository.fetchArbeitsverhaeltnisseFromRemote(startDate.get(), endDate.get())//
                .subscribe { verhaeltnisse -> teamupEvents.postValue(verhaeltnisse) }
    }


    override fun onCleared() {
        super.onCleared()
        fetchArbeitsverhaeltnisseDisposable?.dispose()
    }
}