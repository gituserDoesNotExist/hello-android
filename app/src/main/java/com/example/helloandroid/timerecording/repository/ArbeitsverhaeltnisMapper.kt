package com.example.helloandroid.timerecording.repository

import com.example.helloandroid.timerecording.Arbeitsverhaeltnis
import com.example.helloandroid.timerecording.config.Person
import com.example.helloandroid.timerecording.view.CalendarConfiguration
import com.example.helloandroid.timerecording.web.remotemodel.RemoteArbeitsverhaeltnis

open class ArbeitsverhaeltnisMapper {


    fun mapToArbeitsverhaeltnisFromKeys(target: Arbeitsverhaeltnis, remoteArbeitsverhaeltnis: RemoteArbeitsverhaeltnis,
                                        config: CalendarConfiguration) {
        val leistungsnehmerKey = remoteArbeitsverhaeltnis.leistungsnehmerKey
        val leistungserbringer = config.teilnehmer.find { it.key == remoteArbeitsverhaeltnis.leistungserbringerKey }
        val leistungsnehmer = config.teilnehmer.find { it.key == leistungsnehmerKey }

        target.apply {
            this.title = remoteArbeitsverhaeltnis.title
            this.datum = remoteArbeitsverhaeltnis.datum
            this.kommentar = remoteArbeitsverhaeltnis.kommentar
            this.leistungserbringer = leistungserbringer
            this.leistungsnehmer = leistungsnehmer ?: Person()
        }
    }

    fun mapToRemoteArbeitsverhaeltnis(target: RemoteArbeitsverhaeltnis, source: Arbeitsverhaeltnis) {
        target.title = source.title
        target.datum = source.datum
        target.kommentar = source.kommentar
        target.leistungsnehmerKey = source.leistungsnehmer.key
        target.leistungserbringerKey = source.leistungserbringer?.key
    }


}
