package com.example.helloandroid.verzicht

import com.example.helloandroid.persistence.Verzicht
import com.example.helloandroid.persistence.VerzichtRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class VerzichtDetailsViewModelTest {

    private val testCandidate = VerzichtDetailsViewModel(Mockito.mock(VerzichtRepository::class.java))

    @Test
    fun testWereDaysIncreasedToday_DaysWereIncreasedToday_ReturnsTrue() {
        val verzichtUpdatedToday = Verzicht("egal", 0)

        val result = testCandidate.wereDaysIncreasedToday(verzichtUpdatedToday)

        assertThat(result).isTrue()
    }

    @Test
    fun testWereDaysIncreasedToday_DaysWereIncreasedYesterday_ReturnsFalse() {
        val verzichtUpdatedToday = Verzicht("egal", 0)

        val result = testCandidate.wereDaysIncreasedToday(verzichtUpdatedToday)

        assertThat(result).isFalse()
    }
}