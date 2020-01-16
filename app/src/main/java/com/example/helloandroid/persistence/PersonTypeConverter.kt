package com.example.helloandroid.persistence

import androidx.room.TypeConverter
import com.example.helloandroid.HelloJson
import com.example.helloandroid.timerecording.config.Person

class PersonTypeConverter {

    @TypeConverter
    fun fromPerson(value: Person): String {
        return HelloJson.objectToJson(value)
    }

    @TypeConverter
    fun toPerson(value: String): Person {
        return HelloJson.fromJson(value)
    }

}