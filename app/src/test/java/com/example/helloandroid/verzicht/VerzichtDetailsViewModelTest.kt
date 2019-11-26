package com.example.helloandroid.verzicht

import com.example.helloandroid.verzicht.persistence.Verzicht
import com.example.helloandroid.verzicht.persistence.VerzichtRepository
import com.example.helloandroid.verzicht.view.VerzichtDetailsViewModel
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.threeten.bp.LocalDateTime
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class VerzichtDetailsViewModelTest {

    private val testCandidate =
        VerzichtDetailsViewModel(Mockito.mock(VerzichtRepository::class.java))

    @Test
    fun testWereDaysIncreasedToday_DaysWereIncreasedToday_ReturnsTrue() {
        val verzichtUpdatedToday = Verzicht("egal")
        verzichtUpdatedToday.timestampDayAdded = Optional.of(LocalDateTime.now())


        val result = testCandidate.wereDaysIncreasedToday(verzichtUpdatedToday)

        assertThat(result).isTrue()
    }

    @Test
    fun testWereDaysIncreasedToday_DaysWereIncreasedYesterday_ReturnsFalse() {
        val verzichtUpdatedYesterday = Verzicht("egal")
        verzichtUpdatedYesterday.timestampDayAdded =
            Optional.of(LocalDateTime.now().minusDays(1))

        val result = testCandidate.wereDaysIncreasedToday(verzichtUpdatedYesterday)

        assertThat(result).isFalse()
    }
}