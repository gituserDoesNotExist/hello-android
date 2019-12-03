package com.example.helloandroid.timerecording

import org.threeten.bp.LocalDateTime
import java.math.BigDecimal

class Arbeitsverhaeltnis {

    lateinit var startDateTime: LocalDateTime
    lateinit var endDateTime: LocalDateTime
    lateinit var leistungserbringer: Person
    lateinit var leistungsnehmer: Person
    lateinit var title: String
    lateinit var comment: String

    fun computeDuration(): BigDecimal {
        return BigDecimal.TEN
    }

}