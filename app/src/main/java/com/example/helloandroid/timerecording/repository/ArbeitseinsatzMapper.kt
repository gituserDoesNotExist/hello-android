package com.example.helloandroid.timerecording.repository

import com.example.helloandroid.HelloException
import com.example.helloandroid.HelloJson
import com.example.helloandroid.timerecording.*
import com.example.helloandroid.timerecording.view.CalendarConfiguration
import com.example.helloandroid.timerecording.web.TeamUpDateConverter
import com.example.helloandroid.timerecording.web.remotemodel.Event
import com.example.helloandroid.timerecording.web.remotemodel.RemoteArbeitsverhaeltnis
import org.apache.commons.lang3.StringUtils.isNotBlank

class ArbeitseinsatzMapper {

    private val zeitArbeitsverhaeltnisMapper = ZeitArbeitsverhaeltnisMapper()
    private val stueckArbeitsverhaeltnisMapper = StueckArbeitsverhaeltnisMapper()

    fun fromRemoteEventToArbeitseinsatz(remoteEvent: Event, config: CalendarConfiguration): Arbeitseinsatz {
        return Arbeitseinsatz().apply {
            this.eventInfo.remoteCalenderId = remoteEvent.id
            this.eventInfo.version = remoteEvent.version
            this.eventInfo.erstelltAm = TeamUpDateConverter.asZonedDateTime(remoteEvent.creationDt)
            this.eventInfo.erstelltVon = remoteEvent.who
            this.arbeitsverhaeltnis = arbeitsverhaeltnisFromJsonString(remoteEvent, config)
        }
    }

    private fun arbeitsverhaeltnisFromJsonString(event: Event, config: CalendarConfiguration): Arbeitsverhaeltnis {
        val json = removeHtmlParagraphs(event.notes)
        val remoteVerhaeltnis = HelloJson.fromJson<RemoteArbeitsverhaeltnis>(json)
        if (!isStueckArbeitsverhaeltnis(remoteVerhaeltnis) && !isZeitArbeitsverhaeltnis(remoteVerhaeltnis)) {
            throw HelloException("Invalid data!")
        }
        return if (isStueckArbeitsverhaeltnis(remoteVerhaeltnis)) {
            stueckArbeitsverhaeltnisMapper.mapArbeitsverhaeltnisFromKeys(remoteVerhaeltnis, config)
        } else {
            zeitArbeitsverhaeltnisMapper.mapArbeitsverhaeltnisFromKeys(remoteVerhaeltnis, config)
        }
    }

    private fun isStueckArbeitsverhaeltnis(verhaeltnis: RemoteArbeitsverhaeltnis): Boolean {
        return verhaeltnis.stueckzahl != null && isNotBlank(verhaeltnis.produktKey)
    }

    private fun isZeitArbeitsverhaeltnis(verhaeltnis: RemoteArbeitsverhaeltnis): Boolean {
        return isNotBlank(verhaeltnis.anbaugeraetKey) && isNotBlank(verhaeltnis.fahrzeugKey) && isNotBlank(
            verhaeltnis.taetigkeitKey)
    }

    private fun removeHtmlParagraphs(notes: String): String {
        return notes.replace("<p>", "").replace("</p>", "")
    }

    /** Sollte beim Updaten verwendet werden */
    fun fromZeitArbeitsverhaeltnisToRemoteEvent(arbeitsverhaeltnis: ZeitArbeitsverhaeltnis, info: EventInfo): Event {
        val who = info.erstelltVon
        return zeitArbeitsverhaeltnisMapper.fromArbeitsverhaeltnisToRemoteEvent(arbeitsverhaeltnis, who).apply {
            this.id = info.remoteCalenderId
            this.version = info.version
        }
    }


    /** Sollte beim Updaten verwendet werden */
    fun fromStueckArbeitsverhaeltnisToRemoteEvent(verhaeltnis: StueckArbeitsverhaeltnis, info: EventInfo): Event {
        val who = info.erstelltVon
        return stueckArbeitsverhaeltnisMapper.fromArbeitsverhaeltnisToRemoteEvent(verhaeltnis, who).apply {
            this.id = info.remoteCalenderId
            this.version = info.version
        }
    }

}