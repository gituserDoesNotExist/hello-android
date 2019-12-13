package com.example.helloandroid.view

import androidx.databinding.InverseMethod
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

object LocalDateTimeConverter {

    private val FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm")

    @JvmStatic
    @InverseMethod("stringToDate")
    fun dateToString(value: LocalDateTime): String {
        return value.format(FORMATTER)
    }

    @JvmStatic
    fun stringToDate(value: String): LocalDateTime {
        return LocalDateTime.parse(value, FORMATTER)
    }

}