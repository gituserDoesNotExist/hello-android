package com.example.helloandroid.timerecording.repository

import com.example.helloandroid.HelloException
import com.example.helloandroid.HelloJson
import com.example.helloandroid.timerecording.Arbeitsverhaeltnis
import com.example.helloandroid.timerecording.Arbeitszeit
import com.example.helloandroid.timerecording.ZeitArbeitsverhaeltnis
import com.example.helloandroid.timerecording.view.CalendarConfiguration
import com.example.helloandroid.timerecording.web.TeamUpDateConverter
import com.example.helloandroid.timerecording.web.TeamupCalendarConfig
import com.example.helloandroid.timerecording.web.remotemodel.Event
import com.example.helloandroid.timerecording.web.remotemodel.RemoteArbeitsverhaeltnis
import java.math.BigDecimal

class ZeitArbeitsverhaeltnisMapper {

    private val arbeitsverhaeltnisMapper = ArbeitsverhaeltnisMapper()


    fun mapArbeitsverhaeltnisFromKeys(remoteArbeitsverhaeltnis: RemoteArbeitsverhaeltnis,
                                      config: CalendarConfiguration): Arbeitsverhaeltnis {
        val taetigkeitKey = remoteArbeitsverhaeltnis.taetigkeitKey
        val anbaugeraet = config.anbaugeraete.find { it.key == remoteArbeitsverhaeltnis.anbaugeraetKey }
        val fahrzeug = config.fahrzeuge.find { it.key == remoteArbeitsverhaeltnis.fahrzeugKey }
        val taetigkeit = config.teatigkeiten.find { it.key == taetigkeitKey }
        return ZeitArbeitsverhaeltnis().apply {
            this.taetigkeit = taetigkeit ?: throw HelloException("Ungültiger key: $taetigkeitKey")
            this.anbaugeraet = anbaugeraet
            this.arbeitszeit = remoteArbeitsverhaeltnis.arbeitszeit ?: Arbeitszeit(BigDecimal.ZERO)
            this.fahrzeug = fahrzeug
            arbeitsverhaeltnisMapper.mapToArbeitsverhaeltnisFromKeys(this, remoteArbeitsverhaeltnis, config)
        }
    }


    fun fromArbeitsverhaeltnisToRemoteEvent(arbeitsverhaeltnis: ZeitArbeitsverhaeltnis, erstelltVon: String): Event {
        return Event().apply {
            this.title =
                "${arbeitsverhaeltnis.leistungserbringer?.name}_${arbeitsverhaeltnis.leistungsnehmer.name}_${arbeitsverhaeltnis.taetigkeit.bezeichnung}"
            this.subcalendarId = TeamupCalendarConfig.SUBCALENDAR_ID_NACHBARSCHAFTSHILFE
            this.startDt = TeamUpDateConverter.asEuropeBerlinZonedDateTimeString(arbeitsverhaeltnis.datum)
            this.endDt = TeamUpDateConverter.asEuropeBerlinZonedDateTimeString(arbeitsverhaeltnis.calculateEndDatum())
            this.notes = HelloJson.objectToJson(fromArbeitsverhaeltnisToRemoteArbeitsverhaeltnis(arbeitsverhaeltnis))
            this.who = erstelltVon
        }
    }

    private fun fromArbeitsverhaeltnisToRemoteArbeitsverhaeltnis(
        arbeitsverhaeltnis: ZeitArbeitsverhaeltnis): RemoteArbeitsverhaeltnis {
        return RemoteArbeitsverhaeltnis().apply {
            this.arbeitszeit = arbeitsverhaeltnis.arbeitszeit
            this.taetigkeitKey = arbeitsverhaeltnis.taetigkeit.key
            this.fahrzeugKey = arbeitsverhaeltnis.fahrzeug?.key
            this.anbaugeraetKey = arbeitsverhaeltnis.anbaugeraet?.key
            arbeitsverhaeltnisMapper.mapToRemoteArbeitsverhaeltnis(this, arbeitsverhaeltnis)
        }
    }

}
