package com.example.helloandroid.timerecording.repository

import com.example.helloandroid.timerecording.Arbeitsverhaeltnis
import com.example.helloandroid.timerecording.Person
import com.example.helloandroid.timerecording.web.TeamUpDateConverter
import com.example.helloandroid.timerecording.web.remotemodel.Event
import com.example.helloandroid.timerecording.web.remotemodel.Events
import java.util.stream.Collectors

class EventToArbeitsverhaeltnisMapper {

    private fun fromEventToArbeitsverhaeltnis(event: Event): Arbeitsverhaeltnis {
         return Arbeitsverhaeltnis().apply {
             this.startDateTime = TeamUpDateConverter.asIsoLocalDateTime(event.startDt)
             this.endDateTime = TeamUpDateConverter.asIsoLocalDateTime(event.endDt)
             this.title = event.title
             this.comment = event.notes
             this.leistungserbringer = Person("ich")
             this.leistungsnehmer = Person("du")
         }
    }

    fun fromEventsToArbeitsverhaeltnisse(events: Events) : List<Arbeitsverhaeltnis> {
        return events.events.stream().map(this::fromEventToArbeitsverhaeltnis).collect(Collectors.toList())
    }

}