package com.example.helloandroid.timerecording.web

import com.example.helloandroid.DateConverter
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter


object TeamUpDateConverter {

    private val FORMATTER_OFFSET_DATE_TIME = DateTimeFormatter.ISO_OFFSET_DATE_TIME
    private val FORMATTER_LOCAL_DATE_TIME = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    fun asZonedDateTimeString(value: ZonedDateTime): String {
        return DateConverter.asString(value, FORMATTER_OFFSET_DATE_TIME)
    }

    fun asZonedDateTime(value: String): ZonedDateTime {
        return DateConverter.asDate(value, FORMATTER_OFFSET_DATE_TIME)
    }

    fun asIsoLocalDateTime(value: String) : LocalDateTime {
        return LocalDateTime.parse(value, FORMATTER_LOCAL_DATE_TIME)
    }

}