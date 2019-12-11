package com.example.helloandroid.timerecording.view

import androidx.lifecycle.ViewModel
import com.example.helloandroid.timerecording.TeamupEvent
import com.example.helloandroid.timerecording.repository.ZeiterfassungRepository

class SharedTeamupEventViewModel(private val zeiterfassungRepository: ZeiterfassungRepository) :
    ViewModel() {

    lateinit var currentEvent: TeamupEvent

    fun deleteArbeitsverhaeltnis() {
        zeiterfassungRepository.deleteArbeitsverhaeltnis(currentEvent)
    }


}