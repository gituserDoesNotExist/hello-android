package com.example.helloandroid.persistence

import androidx.room.TypeConverter
import org.threeten.bp.LocalDateTime


class LocalDateTimeConverter {

    @TypeConverter
    fun fromLocalDateTime(value: LocalDateTime): String {
        return value.toString()
    }

    @TypeConverter
    fun toLocalDateTime(value: String): LocalDateTime {
        return LocalDateTime.parse(value)
    }


}