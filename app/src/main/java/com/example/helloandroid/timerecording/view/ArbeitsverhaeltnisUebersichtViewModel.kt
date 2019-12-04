package com.example.helloandroid.timerecording.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.timerecording.Arbeitsverhaeltnis
import com.example.helloandroid.timerecording.repository.AppConfigurationRepository
import com.example.helloandroid.timerecording.repository.ArbeitsverhaeltnisRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class ArbeitsverhaeltnisUebersichtViewModel(private val arbeitsverhaeltnisRepository: ArbeitsverhaeltnisRepository,//
                                            appConfigurationRepository: AppConfigurationRepository)//
    : ViewModel() {

    val config: LiveData<CalendarConfiguration> = appConfigurationRepository.getConfiguration()
    val arbeitsverhaeltnisse = MutableLiveData<List<Arbeitsverhaeltnis>>()

    private var fetchArbeitsverhaeltnisseDisposable: Disposable
    private var addArbeitsverhaeltnisDisposable: Disposable? = null
    private val arbeitsverhaeltnisMapper = ArbeitsverhaeltnisMapper()

    init {
        fetchArbeitsverhaeltnisseDisposable =
            findAllArbeitsverhaeltnisse().subscribe { verhaeltnisse -> arbeitsverhaeltnisse.postValue(verhaeltnisse) }
    }


    private fun findAllArbeitsverhaeltnisse(): Single<List<Arbeitsverhaeltnis>> {
        return arbeitsverhaeltnisRepository.fetchAllArbeitsverhaeltnisseFromRemoteCalender()
    }


    fun addArbeitsverhaeltnis(arbeitsverhaeltnisErstellenDTO: ArbeitsverhaeltnisErstellenDTO) {
        val arbeitsverhaeltnis = arbeitsverhaeltnisMapper.fromDto(arbeitsverhaeltnisErstellenDTO)
        addArbeitsverhaeltnisDisposable =
            arbeitsverhaeltnisRepository.addArbeitsverhaeltnisToRemoteCalendar(arbeitsverhaeltnis)//
                .flatMap {
                    findAllArbeitsverhaeltnisse()
                }//
                .observeOn(AndroidSchedulers.mainThread())//
                .subscribe { verhaeltnisse ->
                    arbeitsverhaeltnisse.postValue(verhaeltnisse)
                }
    }

    override fun onCleared() {
        super.onCleared()
        fetchArbeitsverhaeltnisseDisposable.dispose()
        addArbeitsverhaeltnisDisposable?.dispose()
    }
}