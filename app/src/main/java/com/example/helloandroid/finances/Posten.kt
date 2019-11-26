package com.example.helloandroid.finances

import java.math.BigDecimal

class Posten(name: String) {

    var id: Long = 0
    val name: String = name
    var ausgaben: MutableList<Ausgabe> = ArrayList()

    fun calculateGesamtausgaben(): BigDecimal {
        return ausgaben.stream().map(Ausgabe::wert).reduce(BigDecimal::add).orElse(BigDecimal.ZERO)
    }
}