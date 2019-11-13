package com.example.helloandroid.persistence

import org.hamcrest.core.IsEqual.equalTo
import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDateTime
import java.util.*

class LocalDateTimeConverterTest {

    private val converter: LocalDateTimeConverter = LocalDateTimeConverter()

    @Test
    fun testFromLocalDateTime() {
        val result = converter.fromLocalDateTime(Optional.of(SOME_LOCAL_DATE_TIME))

        assertThat(result, equalTo(SOME_TIME_STRING))
    }

    @Test
    fun testToLocalDateTime() {
        val result = converter.toLocalDateTime(SOME_TIME_STRING).get()

        assertThat(result, equalTo(SOME_LOCAL_DATE_TIME))
    }

    companion object Egal {

        private const val SOME_TIME_STRING: String = "2000-10-09T01:02:03"
        private val SOME_LOCAL_DATE_TIME = LocalDateTime.of(2000, 10, 9, 1, 2, 3)

    }


}