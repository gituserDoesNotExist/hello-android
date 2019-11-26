package com.example.helloandroid.view

import androidx.databinding.InverseMethod
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter

object LocalTimeConverter {

    @JvmStatic
    @InverseMethod("stringToTime")
    fun timeToString(value: LocalTime): String {
        return value.format(DateTimeFormatter.ofPattern("HH:mm"))
    }

    @JvmStatic
    fun stringToTime(value: String): LocalTime {
        return LocalTime.parse(value, DateTimeFormatter.ofPattern("HH:mm"))
    }


}