package com.example.helloandroid.timerecording.web

import com.example.helloandroid.DateConverter
import com.example.helloandroid.ZoneIds
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter


object TeamUpDateConverter {

    private val FORMATTER_OFFSET_DATE_TIME = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    private val FORMATTER_LOCAL_DATE_TIME = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    fun asZonedDateTimeString(value: ZonedDateTime): String {
        return DateConverter.asString(value, FORMATTER_OFFSET_DATE_TIME)
    }

    fun asEuropeBerlinZonedDateTimeString(value: LocalDate): String {
        val zdt = value.atStartOfDay().atZone(ZoneId.of(ZoneIds.EUROPE_BERLIN.zoneId))
        return DateConverter.asString(zdt, FORMATTER_OFFSET_DATE_TIME)
    }

    fun asEuropeBerlinZonedDateTimeString(value: LocalDateTime): String {
        val zdt = value.atZone(ZoneId.of(ZoneIds.EUROPE_BERLIN.zoneId))
        return DateConverter.asString(zdt, FORMATTER_OFFSET_DATE_TIME)
    }

    fun asZonedDateTime(value: String): ZonedDateTime {
        return DateConverter.asDate(value, FORMATTER_OFFSET_DATE_TIME)
    }

    fun asIsoLocalDateTime(value: String) : LocalDateTime {
        return DateConverter.asDate(value, FORMATTER_LOCAL_DATE_TIME).toLocalDateTime()
    }

    fun fromDateToFetchEventsQueryString(date: LocalDate): String {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }



}