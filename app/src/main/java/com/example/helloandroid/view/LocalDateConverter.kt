package com.example.helloandroid.view

import androidx.databinding.InverseMethod
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object LocalDateConverter {

    @InverseMethod("stringToDate")
    fun dateToString(value: LocalDate): String {
        return "${value.dayOfMonth}.${value.monthValue}.${value.year}"
    }

    fun stringToDate(value: String): LocalDate {
        val dateTimeFormatter = DateTimeFormatter.ofPattern("MM.dd.yyyy")
        return LocalDate.parse(value,dateTimeFormatter)
    }


}