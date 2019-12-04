package com.example.helloandroid.timerecording

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.math.BigDecimal

class ArbeitszeitTest {

    @Test
    fun testGetMinutes() {
        assertThat(Arbeitszeit(BigDecimal(2.5)).minutes).isEqualTo(150)
        assertThat(Arbeitszeit(BigDecimal(1)).minutes).isEqualTo(60)
        assertThat(Arbeitszeit(BigDecimal(0.2)).minutes).isEqualTo(12)
        assertThat(Arbeitszeit(BigDecimal(0.33333)).minutes).isEqualTo(18)

    }
}