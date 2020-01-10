package com.example.helloandroid.view

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.math.BigDecimal

class BigDecimalConverterTest {

    @Test
    fun bigDecimalToString() {
        assertThat(BigDecimalConverter.bigDecimalToString(BigDecimal.ZERO)).isEqualTo("")
        assertThat(BigDecimalConverter.bigDecimalToString(BigDecimal.ONE)).isEqualTo("1")
        assertThat(BigDecimalConverter.bigDecimalToString(BigDecimal("1.0"))).isEqualTo("1.")
    }

    @Test
    fun stringToBigDecimal() {
        assertThat(BigDecimalConverter.stringToBigDecimal("5.3")).isEqualTo(BigDecimal("5.3"))
        assertThat(BigDecimalConverter.stringToBigDecimal("5")).isEqualTo(BigDecimal("5.0"))
        assertThat(BigDecimalConverter.stringToBigDecimal("1.")).isEqualTo(BigDecimal("1.0"))
    }
}