package com.example.helloandroid.finances.persistence

import com.example.helloandroid.finances.Ausgabe
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
                Ausgabe(BigDecimal.TEN, LocalDateTime.now(),"",1),
                Ausgabe(BigDecimal.ONE, LocalDateTime.now(),"",1)
            )
        )

        val result = posten.calculateGesamtausgaben()

        assertThat(result).isEqualTo(BigDecimal(11))
    }

}


