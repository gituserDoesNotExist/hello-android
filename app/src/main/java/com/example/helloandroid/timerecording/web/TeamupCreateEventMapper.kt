package com.example.helloandroid.timerecording.web

import com.example.helloandroid.DateConverter
import com.example.helloandroid.timerecording.Zeiterfassung

class TeamupCreateEventMapper {

    fun asCreateEvent(zeiterfassung: Zeiterfassung, subcalendarId: Long): TeamupCreateEventRequestDTO {
        val request = TeamupCreateEventRequestDTO()

        request.subcalendarId = subcalendarId
        request.startDt = TeamUpDateConverter.asString(DateConverter.localDateToZonedDateTime(zeiterfassung.datum))
        request.endDt = TeamUpDateConverter.asString(DateConverter.localDateToZonedDateTime(zeiterfassung.datum).plusMinutes(zeiterfassung.anzahlStunden.toLong()))

        return request
    }


}