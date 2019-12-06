package com.example.helloandroid.timerecording

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.math.BigDecimal

class ArbeitszeitTest {

    @Test
    fun testGetMinutes() {
        assertThat(Arbeitszeit(BigDecimal(2.5)).toString()).isEqualTo("2,5")
        assertThat(Arbeitszeit(BigDecimal(1)).toString()).isEqualTo("1,0")
        assertThat(Arbeitszeit(BigDecimal(0.22)).toString()).isEqualTo("0,2")
        assertThat(Arbeitszeit(BigDecimal(0.33333)).toString()).isEqualTo("0,3")
    }
}