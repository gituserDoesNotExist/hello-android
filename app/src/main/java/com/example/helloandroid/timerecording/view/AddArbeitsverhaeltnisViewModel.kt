package com.example.helloandroid.timerecording.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.timerecording.repository.ZeiterfassungRepository
import io.reactivex.Single

class AddArbeitsverhaeltnisViewModel(private val zeiterfassungRepository: ZeiterfassungRepository) : ViewModel() {

    val config: LiveData<CalendarConfiguration> = zeiterfassungRepository.getConfiguration()
    private val arbeitsverhaeltnisMapper = TeamupEventToDTOMapper()

    fun addArbeitsverhaeltnis(arbeitsverhaeltnisDTO: ArbeitsverhaeltnisDTO): Single<Long> {
        val event = arbeitsverhaeltnisMapper.fromDtoToTeamupEvent(arbeitsverhaeltnisDTO)
        return zeiterfassungRepository.addArbeitsverhaeltnisToRemoteCalendar(event)//
    }

}