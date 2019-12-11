package com.example.helloandroid.timerecording.repository

import com.example.helloandroid.HelloJson
import com.example.helloandroid.timerecording.Arbeitsverhaeltnis
import com.example.helloandroid.timerecording.TeamupEvent
import com.example.helloandroid.timerecording.TeamupEvents
import com.example.helloandroid.timerecording.web.TeamUpDateConverter
import com.example.helloandroid.timerecording.web.TeamupCalenderConfig
import com.example.helloandroid.timerecording.web.remotemodel.Event
import com.example.helloandroid.timerecording.web.remotemodel.Events
import com.example.helloandroid.timerecording.web.remotemodel.TeamupCreateEventRequest
import java.util.stream.Collectors

class TeamupEventMapper {

    private val arbeitsverhaeltnisMapper = ArbeitsverhaeltnisMapper()

    fun fromRemoteEventsToTeamupEvents(events: Events): TeamupEvents {
        return TeamupEvents(events.events.stream().map(this::fromRemoteToTeamupEvent).collect(Collectors.toList()))
    }

    fun fromRemoteToTeamupEvent(remoteEvent: Event): TeamupEvent {
        return TeamupEvent().apply {
            this.remoteCalenderId = remoteEvent.id
            this.version = remoteEvent.version
            this.erstelltAm = TeamUpDateConverter.asZonedDateTime(remoteEvent.creationDt)
            this.erstelltVon = remoteEvent.who ?: "unknown"
            this.arbeitsverhaeltnis = arbeitsverhaeltnisMapper.fromEventToArbeitsverhaeltnis(remoteEvent)
        }
    }

    fun fromArbeitsverhaeltnisToEvent(arbeitsverhaeltnis: Arbeitsverhaeltnis, ersteller: String): TeamupCreateEventRequest {
        return TeamupCreateEventRequest().apply {
            this.title = arbeitsverhaeltnis.createTitleForArbeitsverhaeltnis()
            this.subcalendarId = TeamupCalenderConfig.SUBCALENDAR_ID_NACHBARSCHAFTSHILFE
            this.startDt = TeamUpDateConverter.asEuropeBerlinZonedDateTimeString(arbeitsverhaeltnis.datum)
            this.endDt = TeamUpDateConverter.asEuropeBerlinZonedDateTimeString(arbeitsverhaeltnis.calculateEndDatum())
            this.notes = HelloJson.objectToJson(arbeitsverhaeltnis)
            this.who = ersteller
        }
    }

}