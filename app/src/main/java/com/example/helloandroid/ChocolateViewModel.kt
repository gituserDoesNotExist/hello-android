package com.example.helloandroid

import androidx.databinding.ObservableField


class ChocolateViewModel {

    val daysWithoutChocolate: ObservableField<Int> = ObservableField(0)


    fun increaseDaysWithoutChocolateByOneDay() {
        var valueDaysWithoutChocolate = daysWithoutChocolate.get()
        valueDaysWithoutChocolate = valueDaysWithoutChocolate?.inc()
        daysWithoutChocolate.set(valueDaysWithoutChocolate)
    }

}