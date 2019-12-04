package com.example.helloandroid.timerecording

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime

class Arbeitsverhaeltnis {

    lateinit var kategorie: String
    lateinit var datum: LocalDate
    lateinit var dauer: Arbeitszeit
    lateinit var leistungserbringer: Person
    lateinit var leistungsnehmer: Person
    lateinit var kommentar: String

    fun calculateEndDatum() : LocalDateTime {
        return datum.atStartOfDay().plusMinutes(dauer.minutes)
    }

    fun createTitleForArbeitsverhaeltnis(): String {
        return "${leistungserbringer.name} hilft ${leistungsnehmer.name} bei $kategorie"
    }
}