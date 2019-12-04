package com.example.helloandroid.timerecording.web

import com.example.helloandroid.DateConverter
import com.example.helloandroid.timerecording.Zeiterfassung
import com.example.helloandroid.timerecording.web.remotemodel.TeamupCreateEventRequest

class TeamupCreateEventMapper {

    fun asCreateEvent(zeiterfassung: Zeiterfassung, subcalendarId: Long): TeamupCreateEventRequest {
        val request = TeamupCreateEventRequest()

        request.subcalendarId = subcalendarId
        request.startDt = TeamUpDateConverter.asZonedDateTimeString(DateConverter.localDateToZonedDateTime(zeiterfassung.datum))
        request.endDt = TeamUpDateConverter.asZonedDateTimeString(DateConverter.localDateToZonedDateTime(zeiterfassung.datum).plusMinutes(zeiterfassung.anzahlStunden.toLong()))

        return request
    }


}