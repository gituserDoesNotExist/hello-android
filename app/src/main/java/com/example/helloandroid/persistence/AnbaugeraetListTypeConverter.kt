package com.example.helloandroid.persistence

import androidx.room.TypeConverter
import com.example.helloandroid.HelloJson
import com.example.helloandroid.timerecording.config.Anbaugeraet

class AnbaugeraetListTypeConverter {

    @TypeConverter
    fun fromAnbaugeraetList(values: List<Anbaugeraet>): String {
        return HelloJson.objectToJson(values)
    }

    @TypeConverter
    fun toAnbaugeraetList(value: String): List<Anbaugeraet> {
        return HelloJson.fromJson(value)
    }

}