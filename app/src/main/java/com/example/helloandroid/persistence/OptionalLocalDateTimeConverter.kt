package com.example.helloandroid.persistence

import androidx.room.TypeConverter
import org.threeten.bp.LocalDateTime
import java.util.*


class OptionalLocalDateTimeConverter {

    @TypeConverter
    fun fromOptionalCalendar(value: Optional<LocalDateTime>): String {
        val converter = LocalDateTimeConverter()
        return value.map { c -> converter.fromLocalDateTime(c) }.orElse("")
    }

    @TypeConverter
    fun toOptionalCalendar(value: String): Optional<LocalDateTime> {
        val converter = LocalDateTimeConverter()
        if (value.isEmpty()) {
            return Optional.empty()
        }
        return Optional.ofNullable(converter.toLocalDateTime(value))
    }




}