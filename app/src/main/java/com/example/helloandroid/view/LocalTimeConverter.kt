package com.example.helloandroid.view

import androidx.databinding.InverseMethod
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object LocalTimeConverter {

    @JvmStatic
    @InverseMethod("stringToLocalTime")
    fun localTimeToString(value: LocalTime): String {
        return "${value.hour}:${value.minute}"
    }

    @JvmStatic
    fun stringToLocalTime(value: String): LocalTime? {
        return LocalTime.parse(value, DateTimeFormatter.ofPattern("HH:mm"))
    }


}