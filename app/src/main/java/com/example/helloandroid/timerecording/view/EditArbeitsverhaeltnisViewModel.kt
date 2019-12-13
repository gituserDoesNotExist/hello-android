package com.example.helloandroid.timerecording.view

import androidx.lifecycle.ViewModel
import com.example.helloandroid.timerecording.TeamupEvent
import com.example.helloandroid.timerecording.repository.ZeiterfassungRepository
import io.reactivex.Single

class EditArbeitsverhaeltnisViewModel(private val zeiterfassungRepository: ZeiterfassungRepository) : ViewModel() {

    private val arbeitsverhaeltnisMapper = TeamupEventToDTOMapper()

    lateinit var arbeitsverhaeltnisToEdit: ArbeitsverhaeltnisDetailsDTO


    fun initialize(event: TeamupEvent) {
        arbeitsverhaeltnisToEdit = arbeitsverhaeltnisMapper.fromTeamupEventToArbeitsverhaeltnisDetailsDto(event)
    }


    fun updateArbeitsverhaeltnis(): Single<TeamupEvent> {
        val event = arbeitsverhaeltnisMapper.fromArbeitsverhaeltnisDetailsDtoToTeamupEvent(arbeitsverhaeltnisToEdit)
        return zeiterfassungRepository.updateArbeitsverhaeltnisInRemoteCalender(event)//
    }

}