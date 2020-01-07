package com.example.helloandroid.timerecording.repository

import com.example.helloandroid.HelloJson
import com.example.helloandroid.timerecording.Arbeitsverhaeltnis
import com.example.helloandroid.timerecording.TeamupEvent
import com.example.helloandroid.timerecording.web.TeamUpDateConverter
import com.example.helloandroid.timerecording.web.TeamupCalenderConfig
import com.example.helloandroid.timerecording.web.remotemodel.Event

class TeamupEventToRemoteEventMapper {

    private val arbeitsverhaeltnisMapper = ArbeitsverhaeltnisMapper()

    fun fromRemoteToTeamupEvent(remoteEvent: Event): TeamupEvent {
        return TeamupEvent().apply {
            this.remoteCalenderId = remoteEvent.id
            this.version = remoteEvent.version
            this.erstelltAm = TeamUpDateConverter.asZonedDateTime(remoteEvent.creationDt)
            this.erstelltVon = remoteEvent.who ?: "unknown"
            this.arbeitsverhaeltnis = arbeitsverhaeltnisMapper.fromEventToArbeitsverhaeltnis(remoteEvent)
        }
    }


    /** Sollte beim Anlegen verwendet werden */
    fun fromArbeitsverhaeltnisToRemoteEvent(arbeitsverhaeltnis: Arbeitsverhaeltnis, erstelltVon: String): Event {
        return Event().apply {
            this.title =
                "${arbeitsverhaeltnis.leistungserbringer}_${arbeitsverhaeltnis.leistungsnehmer}_${arbeitsverhaeltnis.kategorie}"
            this.subcalendarId = TeamupCalenderConfig.SUBCALENDAR_ID_NACHBARSCHAFTSHILFE
            this.startDt = TeamUpDateConverter.asEuropeBerlinZonedDateTimeString(arbeitsverhaeltnis.datum)
            this.endDt = TeamUpDateConverter.asEuropeBerlinZonedDateTimeString(arbeitsverhaeltnis.calculateEndDatum())
            this.notes = HelloJson.objectToJson(arbeitsverhaeltnis)
            this.who = erstelltVon
        }
    }

    /** Sollte beim Updaten verwendet werden */
    fun fromTeamupEventToRemoteEvent(teamupEvent: TeamupEvent): Event {
        val arbeitsverhaeltnis = teamupEvent.arbeitsverhaeltnis
        return fromArbeitsverhaeltnisToRemoteEvent(arbeitsverhaeltnis, teamupEvent.erstelltVon).apply {
            this.id = teamupEvent.remoteCalenderId
            this.version = teamupEvent.version
        }
    }


}