package com.example.helloandroid.persistence

import androidx.room.TypeConverter
import com.example.helloandroid.HelloJson
import com.example.helloandroid.timerecording.config.Person

class PersonListTypeConverter {

    @TypeConverter
    fun fromPersonList(values: List<Person>): String {
        return HelloJson.objectToJson(values)
    }

    @TypeConverter
    fun toPersonList(value: String): List<Person> {
        return HelloJson.fromJson(value)
    }

}