package com.example.helloandroid

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object HelloJson {

    private val gson: Gson = Gson()

    fun <T> objectToJson(value: T) : String {
        return gson.toJson(value)
    }

    fun <T> jsonToObject(value: String, clazz: Class<T>) : T {
        return gson.fromJson(value, clazz)
    }

    fun <T> jsonToObjects(value: String) : List<T> {
        val type = object : TypeToken<List<T>>() {}.type
        return gson.fromJson(value,type)
    }


}