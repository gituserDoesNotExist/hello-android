package com.example.helloandroid.persistence

import org.hamcrest.core.IsEqual.equalTo
import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDateTime

class HelloTypeConvertersTest {

    private val converter: HelloTypeConverters = HelloTypeConverters()

    @Test
    fun fromLocalDateTime() {
        val result = converter.fromLocalDateTime(SOME_TIME_LOCAL_DATE_TIME)

        assertThat(result, equalTo(TIME_STRING))
    }

    @Test
    fun toLocalDateTime() {
        val result = converter.toLocalDateTime(TIME_STRING)

        assertThat(result, equalTo(SOME_TIME_LOCAL_DATE_TIME))
    }

    companion object Egal {

        private const val TIME_STRING: String = "2000-10-09T01:02:03"
        private val SOME_TIME_LOCAL_DATE_TIME = LocalDateTime.of(2000, 10, 9, 1, 2, 3)

    }


}