package com.example.helloandroid.persistence

import androidx.room.TypeConverter
import com.example.helloandroid.HelloJson
import com.example.helloandroid.timerecording.config.Person

class PersonTypeConverter {

    @TypeConverter
    fun fromPerson(values: Person): String {
        return HelloJson.objectToJson(values)
    }

    @TypeConverter
    fun toPerson(value: String): Person {
        return HelloJson.fromJson(value)
    }

}