package com.example.helloandroid.view

import androidx.databinding.InverseMethod
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

object LocalDateConverter {

    @JvmStatic
    @InverseMethod("stringToDate")
    fun dateToString(value: LocalDate): String {
        return value.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
    }

    @JvmStatic
    fun stringToDate(value: String): LocalDate {
        return LocalDate.parse(value, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
    }


}