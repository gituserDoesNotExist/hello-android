package com.example.helloandroid.view

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.threeten.bp.LocalTime

class LocalTimeConverterTest {

    @Test
    fun timeToString() {
        val result = LocalTimeConverter.timeToString(LocalTime.of(9, 23))

        assertThat(result).isEqualTo("09:23")
    }

    @Test
    fun stringToTime() {
        val result = LocalTimeConverter.stringToTime("09:23")

        assertThat(result).isEqualTo(LocalTime.of(9,23))
    }
}