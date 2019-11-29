package com.example.helloandroid.view

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.threeten.bp.LocalDate

class LocalDateConverterTest {

    @Test
    fun dateToString() {
        val result = LocalDateConverter.dateToString(LocalDate.of(2000, 9, 8))

        assertThat(result).isEqualTo("08.09.2000")
    }

    @Test
    fun stringToDate() {
        val result = LocalDateConverter.stringToDate("08.09.2000")

        assertThat(result).isEqualTo(LocalDate.of(2000, 9, 8))
    }



}