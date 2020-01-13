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
            this.datum = remoteArbeitsverhaeltnis.datum
            this.kommentar = remoteArbeitsverhaeltnis.kommentar
            this.leistungserbringer = leistungserbringer
            this.leistungsnehmer = leistungsnehmer ?: Person()
        }
    }


}
