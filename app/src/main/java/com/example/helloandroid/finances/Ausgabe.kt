package com.example.helloandroid.finances

import org.threeten.bp.LocalDateTime
import java.math.BigDecimal

data class Ausgabe(val wert: BigDecimal, val datum: LocalDateTime, val beschreibung: String, var postenId: Long = 0)