package com.example.helloandroid.timerecording.web

import com.example.helloandroid.DateConverter
import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter


object TeamUpDateConverter {

    private val FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    fun asString(value: ZonedDateTime): String {
        return DateConverter.asString(value, FORMATTER)
    }

    fun asDate(value: String): ZonedDateTime {
        return DateConverter.asDate(value, FORMATTER)
    }

}