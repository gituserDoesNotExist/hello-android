package com.example.helloandroid.timerecording

import com.example.helloandroid.timerecording.web.remotemodel.RemoteArbeitsverhaeltnis
import org.threeten.bp.ZonedDateTime

class TeamupEvent {

    lateinit var remoteCalenderId: String
    lateinit var version: String
    lateinit var erstelltAm: ZonedDateTime
    lateinit var erstelltVon: String

    lateinit var remoteArbeitsverhaeltnis: RemoteArbeitsverhaeltnis


}