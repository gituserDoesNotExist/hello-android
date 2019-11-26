package com.example.helloandroid.view

import androidx.databinding.InverseMethod
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

object LocalDateConverter {

    @JvmStatic
    @InverseMethod("stringToDate")
    fun dateToString(value: LocalDate): String {
        return "${value.dayOfMonth}.${value.monthValue}.${value.year}"
    }

    @JvmStatic
    fun stringToDate(value: String): LocalDate {
        return LocalDate.parse(value, DateTimeFormatter.ofPattern("d.M.yyyy"))
    }


}