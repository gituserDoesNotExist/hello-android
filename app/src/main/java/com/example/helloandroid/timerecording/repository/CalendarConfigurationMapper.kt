package com.example.helloandroid.timerecording.repository

import com.example.helloandroid.timerecording.config.RemoteCalendarMetadata
import com.example.helloandroid.timerecording.persistence.CalendarConfigurationEntity
import com.example.helloandroid.timerecording.view.CalendarConfiguration

class CalendarConfigurationMapper {

    fun fromRemoteMetadataToCalendarConfigurationEntity(metadata: RemoteCalendarMetadata): CalendarConfigurationEntity {
        return CalendarConfigurationEntity().apply {
            this.taetigkeiten = metadata.taetigkeiten
            this.teilnehmer = metadata.teilnehmer
            this.fahrzeuge = metadata.fahrzeuge
            this.anbaugeraete = metadata.anbaugeraete
            this.produkte = metadata.produkte
        }
    }


    fun fromConfigEntityToCalendarConfig(entity: CalendarConfigurationEntity): CalendarConfiguration {
        return CalendarConfiguration(entity.appUser, entity.taetigkeiten, entity.teilnehmer, entity.fahrzeuge,
            entity.anbaugeraete, entity.produkte)
    }
}