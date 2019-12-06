package com.example.helloandroid.timerecording.repository

import com.example.helloandroid.HelloJson
import com.example.helloandroid.timerecording.Arbeitsverhaeltnis
import com.example.helloandroid.timerecording.Arbeitsverhaeltnisse
import com.example.helloandroid.timerecording.web.TeamUpDateConverter
import com.example.helloandroid.timerecording.web.TeamupCalenderConfig
import com.example.helloandroid.timerecording.web.remotemodel.Event
import com.example.helloandroid.timerecording.web.remotemodel.Events
import com.example.helloandroid.timerecording.web.remotemodel.TeamupCreateEventRequest
import java.util.stream.Collectors

class EventToArbeitsverhaeltnisMapper {

    private fun fromEventToArbeitsverhaeltnis(event: Event): Arbeitsverhaeltnis {
        return HelloJson.jsonToObject(removeHtmlParagraphs(event.notes),Arbeitsverhaeltnis::class.java).apply {
            this.remoteId = event.id
        }
    }

    private fun removeHtmlParagraphs(notes: String): String {
        return notes.replace("<p>","").replace("</p>","")
    }

    fun fromEventsToArbeitsverhaeltnisse(events: Events): Arbeitsverhaeltnisse {
        val verhaeltnisse = events.events.stream().map(this::fromEventToArbeitsverhaeltnis).collect(Collectors.toList())
        return Arbeitsverhaeltnisse(verhaeltnisse)
    }

    fun fromArbeitsverhaeltnisToEvent(arbeitsverhaeltnis: Arbeitsverhaeltnis): TeamupCreateEventRequest {
        return TeamupCreateEventRequest().apply {
            this.title = arbeitsverhaeltnis.createTitleForArbeitsverhaeltnis()
            this.subcalendarId = TeamupCalenderConfig.SUBCALENDAR_ID_NACHBARSCHAFTSHILFE
            this.startDt = TeamUpDateConverter.asEuropeBerlinZonedDateTimeString(arbeitsverhaeltnis.datum)
            this.endDt = TeamUpDateConverter.asEuropeBerlinZonedDateTimeString(arbeitsverhaeltnis.calculateEndDatum())
            this.notes = HelloJson.objectToJson(arbeitsverhaeltnis)
        }
    }

}