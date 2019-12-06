package com.example.helloandroid

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.threeten.bp.LocalDate

class HelloExtensionsTest {

    @Test
    fun testFindMinDate() {
        val aEarlyDate = LocalDate.of(2000, 10, 10)
        val aLateDate = LocalDate.of(2010, 1, 1)
        val dates = listOf(aEarlyDate, aLateDate)

        val result = dates.findMinDate()

        assertThat(result).isEqualTo(aEarlyDate)
    }

}