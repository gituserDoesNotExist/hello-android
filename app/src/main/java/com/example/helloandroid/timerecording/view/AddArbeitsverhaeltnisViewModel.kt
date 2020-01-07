package com.example.helloandroid.timerecording.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.timerecording.repository.ZeiterfassungRepository
import io.reactivex.Single

class AddArbeitsverhaeltnisViewModel(private val zeiterfassungRepository: ZeiterfassungRepository) : ViewModel() {

    val config: LiveData<CalendarConfiguration> = zeiterfassungRepository.getConfiguration()
    private val arbeitsverhaeltnisMapper = TeamupEventToDTOMapper()
    val arbeitsverhaeltnisDTO = ArbeitsverhaeltnisDTO()

    fun addArbeitsverhaeltnis(): Single<Long> {
        val event = arbeitsverhaeltnisMapper.fromDtoToTeamupEvent(arbeitsverhaeltnisDTO)
        arbeitsverhaeltnisDTO.reset()
        return zeiterfassungRepository.addArbeitsverhaeltnisToRemoteCalendar(event)//
    }

}