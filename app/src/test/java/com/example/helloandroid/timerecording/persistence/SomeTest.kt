package com.example.helloandroid.timerecording.persistence

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.junit.Test
import java.lang.reflect.Type


class SomeTest {

    @Test
    fun fromRolle() {
        var categoriesString = "[\"a\",\"b\"]"

        val test: Type = object : TypeToken<List<String>>() {}.type
        val categories: List<String> = Gson().fromJson(categoriesString, test)

        var jsonString = "[{\"id\": 1,\"name\": \"Max\",\"rolle\": \"AKTUELLER_APP_USER\"},{\"id\": 2,\"name\": \"Max2\",\"rolle\": \"AKTUELLER_APP_USER\"}]"

        val gson = GsonBuilder().create()


        val type: Type = object : TypeToken<List<PersonEntity>>() {}.type
        val person : List<PersonEntity> = Gson().fromJson(jsonString, type)
        println(person)

        jsonString = gson.toJson(person)
        println(jsonString)
    }


}