package com.example.helloandroid.timerecording

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import java.math.BigDecimal

class ArbeitsverhaeltnisTest {

    @Test
    fun calculateEndDatum() {
        val arbeitsverhaeltnis = Arbeitsverhaeltnis().apply {
            this.datum = LocalDate.of(2000, 10, 5)
            this.arbeitszeit = Arbeitszeit(BigDecimal.TEN)
        }

        val result = arbeitsverhaeltnis.calculateEndDatum()

        assertThat(result).isEqualTo(LocalDateTime.of(2000,10,5,10,0,0))
    }
}