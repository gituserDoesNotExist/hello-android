package com.example.helloandroid.persistence

import androidx.room.TypeConverter
import com.example.helloandroid.HelloJson

class StringListTypeConverter {

    @TypeConverter
    fun fromStringList(values: List<String>): String {
        return HelloJson.objectToJson(values)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return HelloJson.jsonToObjects(value)
    }

}