package com.example.helloandroid

import androidx.databinding.ObservableInt

class ChocolateViewModel {

    val daysWithoutChocolate: ObservableInt = ObservableInt(0)

    fun increaseDaysWithoutChocolateByOneDay(): Unit {
        daysWithoutChocolate.get().inc()
    }

}