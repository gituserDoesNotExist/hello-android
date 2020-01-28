package com.example.helloandroid.timerecording

import android.content.res.Resources
import com.example.helloandroid.timerecording.config.Person
import com.example.helloandroid.timerecording.view.Suchkriterien
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import java.math.BigDecimal

abstract class Arbeitsverhaeltnis {

    var title: String = "Test"
    var datum: LocalDate = LocalDate.now()
    var leistungserbringer: Person? = null
    var leistungsnehmer: Person = Person()
    var kommentar: String = ""

    abstract fun createDescription(resources: Resources): String

    open fun calculateEndDatum(): LocalDateTime {
        return datum.atStartOfDay()
    }

    abstract fun calculateKostenForArbeitsverhaeltnis(): BigDecimal

    open fun matchesSuchkriterien(suchkriterien: Suchkriterien): Boolean {
        val containsLeistungsnehmer = suchkriterien.shouldSearchForLeistungsnehmer(leistungsnehmer.key)
        val containsLeistungserbringer = suchkriterien.shouldSearchForLeistungserbringer(leistungserbringer?.key ?: "")
        return containsLeistungsnehmer && containsLeistungserbringer
    }

    protected fun mapToArbeitsverhaeltnis(source: Arbeitsverhaeltnis, target: Arbeitsverhaeltnis) {
        target.datum = source.datum
        target.leistungserbringer = source.leistungserbringer
        target.leistungsnehmer = source.leistungsnehmer
        target.kommentar = source.kommentar
    }

}