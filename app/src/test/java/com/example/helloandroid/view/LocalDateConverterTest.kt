package com.example.helloandroid.view

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDate

class LocalDateConverterTest {

    @Test
    fun testStringToDate_OneDigitForDayAndMonth() {
        val result = LocalDateConverter.stringToDate("3.9.2019")

        assertThat(result).isEqualTo(LocalDate.of(2019,9,3))
    }

    @Test
    fun testStringToDate_TwoDigitsForDayAndMonth() {
        val result = LocalDateConverter.stringToDate("13.11.2019")

        assertThat(result).isEqualTo(LocalDate.of(2019,11,13))
    }
}