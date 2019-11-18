package com.example.helloandroid.finances

import com.example.helloandroid.finances.persistence.AusgabeEntity
import com.example.helloandroid.finances.persistence.FinancesRepository
import com.example.helloandroid.finances.view.PostenUebersichtViewModel
import org.assertj.core.api.Assertions.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.math.BigDecimal
import java.time.LocalDateTime

@RunWith(MockitoJUnitRunner::class)
class PostenEntityUebersichtViewModelTest {

    @InjectMocks
    private lateinit var testCandidate: PostenUebersichtViewModel

    @Mock
    private lateinit var postenRepository: FinancesRepository

    @Test
    fun testCalculateGesamtausgaben() {
        Mockito.`when`(postenRepository.findAllAusgaben()).thenReturn(
            listOf(Ausgabe(BigDecimal.ONE, LocalDateTime.now(), "", 1),
                Ausgabe(BigDecimal.ONE, LocalDateTime.now(), "", 1)))

        val result = testCandidate.calculateGesamtausgaben()

        assertThat(result).isEqualTo(BigDecimal(2))
    }

}