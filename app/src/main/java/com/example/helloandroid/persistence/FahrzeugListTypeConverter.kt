package com.example.helloandroid.persistence

import androidx.room.TypeConverter
import com.example.helloandroid.HelloJson
import com.example.helloandroid.timerecording.config.Fahrzeug

class FahrzeugListTypeConverter {

    @TypeConverter
    fun fromFahrzeugList(values: List<Fahrzeug>): String {
        return HelloJson.objectToJson(values)
    }

    @TypeConverter
    fun toFahrzeugList(value: String): List<Fahrzeug> {
        return HelloJson.fromJson(value)
    }

}