package com.example.helloandroid.persistence

import androidx.room.TypeConverter
import com.example.helloandroid.HelloJson
import com.example.helloandroid.timerecording.config.Produkt

class ProduktListTypeConverter {

    @TypeConverter
    fun fromProduktList(values: List<Produkt>): String {
        return HelloJson.objectToJson(values)
    }

    @TypeConverter
    fun toProduktList(value: String): List<Produkt> {
        return HelloJson.fromJson(value)
    }

}