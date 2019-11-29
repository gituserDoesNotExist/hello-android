package com.example.helloandroid.timerecording

import org.threeten.bp.LocalDate
import java.math.BigDecimal

data class Zeiterfassung(var datum: LocalDate, var anzahlStunden: BigDecimal, var kommentar: String) {

}