package com.example.helloandroid.view

import androidx.databinding.InverseMethod
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

object LocalDateConverter {

    private val FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy")

    @JvmStatic
    @InverseMethod("stringToDate")
    fun dateToString(value: LocalDate): String {
        return value.format(FORMATTER)
    }

    @JvmStatic
    fun stringToDate(value: String): LocalDate {
        return LocalDate.parse(value, FORMATTER)
    }


}