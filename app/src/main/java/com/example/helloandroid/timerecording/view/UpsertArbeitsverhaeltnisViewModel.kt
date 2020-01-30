package com.example.helloandroid.timerecording.view

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.timerecording.EventInfo
import com.example.helloandroid.timerecording.repository.ZeiterfassungRepository
import io.reactivex.Single

abstract class UpsertArbeitsverhaeltnisViewModel(protected val zeiterfassungRepository: ZeiterfassungRepository) :
    ViewModel() {

    val isUpdateMode = ObservableBoolean()
    var editable = ObservableBoolean()
    var titleMissing = ObservableBoolean()

    val config: LiveData<CalendarConfiguration> = zeiterfassungRepository.getConfiguration()

    lateinit var eventInfo: EventInfo

    fun initEventInfo(eventInfo: EventInfo) {
        this.eventInfo = eventInfo.copy()
    }

    abstract fun updateArbeitsverhaeltnis(): Single<String>

    abstract fun addArbeitsverhaeltnis(): Single<Long>

    abstract fun validate(): Boolean


}