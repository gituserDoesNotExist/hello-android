package com.example.helloandroid.timerecording

import android.content.res.Resources
import com.example.helloandroid.timerecording.config.Person
import com.example.helloandroid.timerecording.view.Suchkriterien
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import java.math.BigDecimal

abstract class Arbeitsverhaeltnis {

    lateinit var datum: LocalDate
    var leistungserbringer: Person? = null
    lateinit var leistungsnehmer: Person
    lateinit var kommentar: String

    abstract fun createTitle(resources: Resources): String

    abstract fun createDescription(resources: Resources) : String

    abstract fun getQuantity() : String

    abstract fun calculateEndDatum(): LocalDateTime

    abstract fun calculateKostenForArbeitsverhaeltnis(): BigDecimal

    abstract fun matchesSuchkriterien(suchkriterien: Suchkriterien): Boolean

}