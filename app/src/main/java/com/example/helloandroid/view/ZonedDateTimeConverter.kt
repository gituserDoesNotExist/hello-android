package com.example.helloandroid.view

import androidx.databinding.InverseMethod
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

object ZonedDateTimeConverter {

    private val FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm")

    @JvmStatic
    @InverseMethod("stringToDate")
    fun dateToString(value: ZonedDateTime): String {
        return value.format(FORMATTER)
    }

    @JvmStatic
    fun stringToDate(value: String): ZonedDateTime {
        return ZonedDateTime.parse(value, FORMATTER)
    }

}