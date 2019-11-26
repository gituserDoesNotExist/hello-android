package com.example.helloandroid.view

import androidx.databinding.InverseMethod
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter

object LocalTimeConverter {

    private val FORMATTER = DateTimeFormatter.ofPattern("HH:mm")

    @JvmStatic
    @InverseMethod("stringToTime")
    fun timeToString(value: LocalTime): String {
        return value.format(FORMATTER)
    }

    @JvmStatic
    fun stringToTime(value: String): LocalTime {
        return LocalTime.parse(value, FORMATTER)
    }


}