package com.example.helloandroid.finances

import java.math.BigDecimal
import java.time.LocalDateTime

class Ausgabe(wert: BigDecimal, datum: LocalDateTime, beschreibung: String, postenId: Long) {

    val wert: BigDecimal = wert
    val datum: LocalDateTime = datum
    val beschreibung = beschreibung
    var postenId: Long = 0

}