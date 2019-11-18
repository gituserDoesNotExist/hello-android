package com.example.helloandroid.view

import org.assertj.core.api.Assertions.*
import org.junit.Test

class LocalTimeConverterTest {

    @Test
    fun stringToUhrzeit() {
        val result = LocalTimeConverter.stringToLocalTime("20:25")

        assertThat(result.toString()).isEqualTo("20:25")
    }
}