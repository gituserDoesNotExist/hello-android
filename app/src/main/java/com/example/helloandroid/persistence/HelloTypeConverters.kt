package com.example.helloandroid.persistence

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class HelloTypeConverters {

    @TypeConverter
    fun fromLocalDateTime(value: Optional<LocalDateTime>): String {
        return value.map { ldt -> ldt.format(DateTimeFormatter.ISO_DATE_TIME) }.orElse(null)
    }

    @TypeConverter
    fun toLocalDateTime(value: String): Optional<LocalDateTime> {
        return Optional.ofNullable(LocalDateTime.parse(value, DateTimeFormatter.ISO_DATE_TIME))
    }




}