package com.example.helloandroid.timerecording

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime

class Arbeitsverhaeltnis {

    var fahrzeug: String = ""
    var maschine: String = ""
    lateinit var kategorie: String
    lateinit var datum: LocalDate
    lateinit var arbeitszeit: Arbeitszeit
    lateinit var leistungserbringer: Person
    lateinit var leistungsnehmer: Person
    lateinit var kommentar: String

    fun calculateEndDatum(): LocalDateTime {
        return datum.atStartOfDay().plusMinutes(arbeitszeit.getTimeInMinutes())
    }

}