package com.example.helloandroid.timerecording.repository

import com.example.helloandroid.timerecording.Person
import com.example.helloandroid.timerecording.RemoteCalendarMetadata
import com.example.helloandroid.timerecording.persistence.CalendarConfigurationEntity
import java.util.stream.Collectors

class CalendarConfigurationMapper {

    fun fromRemoteMetadataToCalendarConfigurationEntity(metadata: RemoteCalendarMetadata): CalendarConfigurationEntity {
        return CalendarConfigurationEntity().apply {
            this.categories = metadata.categories
            this.participants = metadata.participants.stream().map(Person::name).collect(Collectors.toList())
        }
    }

}