package com.example.helloandroid.persistence

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class HelloTypeConverters {

    @TypeConverter
    fun fromLocalDateTime(value: LocalDateTime): String {
        return value.format(DateTimeFormatter.ISO_DATE_TIME)
    }

    @TypeConverter
    fun toLocalDateTime(value: String): LocalDateTime {
        return LocalDateTime.parse(value, DateTimeFormatter.ISO_DATE_TIME)
    }

    @TypeConverter
    fun fromLebensmittelType(value: LebensmittelType): String {
        return value.lebensmittelName
    }

    @TypeConverter
    fun toLebensmittelType(value: String): LebensmittelType{
        return LebensmittelType.fromLebensmittelName(value)
    }



}