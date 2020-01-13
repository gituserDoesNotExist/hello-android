package com.example.helloandroid.timerecording.repository

import com.example.helloandroid.HelloJson
import com.example.helloandroid.timerecording.Arbeitsverhaeltnis
import com.example.helloandroid.timerecording.StueckArbeitsverhaeltnis
import com.example.helloandroid.timerecording.config.Produkt
import com.example.helloandroid.timerecording.view.CalendarConfiguration
import com.example.helloandroid.timerecording.web.TeamUpDateConverter
import com.example.helloandroid.timerecording.web.TeamupCalendarConfig
import com.example.helloandroid.timerecording.web.remotemodel.Event
import com.example.helloandroid.timerecording.web.remotemodel.RemoteArbeitsverhaeltnis
import java.math.BigInteger

class StueckArbeitsverhaeltnisMapper  {

    private val arbeitsverhaeltnisMapper = ArbeitsverhaeltnisMapper()

    fun mapArbeitsverhaeltnisFromKeys(remoteArbeitsverhaeltnis: RemoteArbeitsverhaeltnis,
                                      config: CalendarConfiguration): Arbeitsverhaeltnis {
        val produktKey = remoteArbeitsverhaeltnis.produktKey
        val produkt = config.produkte.find { it.key == produktKey}

        return StueckArbeitsverhaeltnis().apply {
            this.produkt = produkt ?: Produkt()
            this.stueckzahl = remoteArbeitsverhaeltnis.stueckzahl?.toInt() ?: 0
            arbeitsverhaeltnisMapper.mapToArbeitsverhaeltnisFromKeys(this,remoteArbeitsverhaeltnis,config)
        }

    }



    /** Sollte beim Anlegen verwendet werden */
    fun fromArbeitsverhaeltnisToRemoteEvent(arbeitsverhaeltnis: StueckArbeitsverhaeltnis, who: String): Event {
        return Event().apply {
            this.title =
                "${arbeitsverhaeltnis.leistungserbringer?.name}_${arbeitsverhaeltnis.leistungsnehmer.name}_${arbeitsverhaeltnis.produkt.name}"
            this.subcalendarId = TeamupCalendarConfig.SUBCALENDAR_ID_NACHBARSCHAFTSHILFE
            this.startDt = TeamUpDateConverter.asEuropeBerlinZonedDateTimeString(arbeitsverhaeltnis.datum)
            this.endDt = TeamUpDateConverter.asEuropeBerlinZonedDateTimeString(arbeitsverhaeltnis.calculateEndDatum())
            this.notes = HelloJson.objectToJson(fromArbeitsverhaeltnisToRemoteArbetisverhaeltnis(arbeitsverhaeltnis))
            this.who = who
        }
    }

    private fun fromArbeitsverhaeltnisToRemoteArbetisverhaeltnis(
        arbeitsverhaeltnis: StueckArbeitsverhaeltnis): RemoteArbeitsverhaeltnis {
        return RemoteArbeitsverhaeltnis().apply {
            this.datum = arbeitsverhaeltnis.datum
            this.kommentar = arbeitsverhaeltnis.kommentar
            this.leistungsnehmerKey = arbeitsverhaeltnis.leistungsnehmer.key
            this.leistungserbringerKey = arbeitsverhaeltnis.leistungserbringer?.key

            this.stueckzahl = BigInteger.valueOf(arbeitsverhaeltnis.stueckzahl.toLong())
            this.produktKey = arbeitsverhaeltnis.produkt.key
        }
    }


}
