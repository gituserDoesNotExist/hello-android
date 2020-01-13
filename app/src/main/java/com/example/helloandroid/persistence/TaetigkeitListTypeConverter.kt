package com.example.helloandroid.persistence

import androidx.room.TypeConverter
import com.example.helloandroid.HelloJson
import com.example.helloandroid.timerecording.config.Taetigkeit

class TaetigkeitListTypeConverter {

    @TypeConverter
    fun fromTaetigkeitList(values: List<Taetigkeit>): String {
        return HelloJson.objectToJson(values)
    }

    @TypeConverter
    fun toTaetigkeitList(value: String): List<Taetigkeit> {
        return HelloJson.fromJson(value)
    }

}