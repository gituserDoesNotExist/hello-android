package com.example.helloandroid

import org.junit.Test

class ChocolateViewModelTest {

    val chocolateViewModel = ChocolateViewModel()

    @Test
    fun testIncreaseDaysWithoutChocolateByOneDay_CalledOneTime_IncreasesDaysWithoutChocolate() {
        chocolateViewModel.increaseDaysWithoutChocolateByOneDay()
    }
}