package com.example.helloandroid.view

import androidx.databinding.InverseMethod
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object LocalTimeConverter {

    @InverseMethod("stringToLocalTime")
    fun localTimeToString(value: LocalTime): String {
        return "${value.hour}:${value.minute}"
    }

    fun stringToLocalTime(value: String): LocalTime? {
        return LocalTime.parse(value, DateTimeFormatter.ofPattern("HH:mm"))
    }


}