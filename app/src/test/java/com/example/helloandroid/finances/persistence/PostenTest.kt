package com.example.helloandroid.finances.persistence

import com.example.helloandroid.finances.Posten
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.math.BigDecimal
import java.time.LocalDateTime

class PostenTest {

    @Test
    fun testCalculateGesamtausgaben() {
        val posten = Posten("PostenWithAusgaben")
        posten.ausgaben.addAll(
            listOf(
                AusgabeEntity(BigDecimal.TEN, "-", LocalDateTime.now()),
                AusgabeEntity(BigDecimal.ONE, "-", LocalDateTime.now())
            )
        )

        val result = posten.calculateGesamtausgaben()

        assertThat(result).isEqualTo(BigDecimal(11))
    }

}


