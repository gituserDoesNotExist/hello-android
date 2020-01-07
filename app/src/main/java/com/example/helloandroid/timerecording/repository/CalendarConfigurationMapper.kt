package com.example.helloandroid.timerecording.repository

import com.example.helloandroid.timerecording.Person
import com.example.helloandroid.timerecording.RemoteCalendarMetadata
import com.example.helloandroid.timerecording.persistence.CalendarConfigurationEntity
import java.util.stream.Collectors

class CalendarConfigurationMapper {

    fun fromRemoteMetadataToCalendarConfigurationEntity(metadata: RemoteCalendarMetadata): CalendarConfigurationEntity {
        return CalendarConfigurationEntity().apply {
            this.kategorien = metadata.kategorien
            this.teilnehmer = metadata.teilnehmer.stream().map(Person::name).collect(Collectors.toList())
            this.fahrzeuge = metadata.fahrzeuge
            this.maschinen = metadata.maschinen
        }
    }

}