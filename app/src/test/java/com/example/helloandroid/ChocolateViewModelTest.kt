package com.example.helloandroid

import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class ChocolateViewModelTest {

    private val chocolateViewModel = ChocolateViewModel()

    @Test
    fun testIncreaseDaysWithoutChocolateByOneDay_CalledOneTime_IncreasesDaysWithoutChocolate() {
        assertThat(chocolateViewModel.daysWithoutChocolate.get(), equalTo(0))

        chocolateViewModel.increaseDaysWithoutChocolateByOneDay()

        assertThat(chocolateViewModel.daysWithoutChocolate.get(), equalTo(1))
    }

    @Test
    fun testIncreaseDaysWithoutChocolateByOneDay_CalledMultipleTimes_IncreasesDaysWithoutChocolateEachTime() {
        assertThat(chocolateViewModel.daysWithoutChocolate.get(), equalTo(0))

        chocolateViewModel.increaseDaysWithoutChocolateByOneDay()
        chocolateViewModel.increaseDaysWithoutChocolateByOneDay()
        chocolateViewModel.increaseDaysWithoutChocolateByOneDay()
        chocolateViewModel.increaseDaysWithoutChocolateByOneDay()

        assertThat(chocolateViewModel.daysWithoutChocolate.get(), equalTo(4))
    }

}