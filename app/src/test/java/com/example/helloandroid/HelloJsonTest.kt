package com.example.helloandroid

import com.example.helloandroid.timerecording.Arbeitseinsatz
import com.example.helloandroid.timerecording.Arbeitszeit
import com.example.helloandroid.timerecording.StueckArbeitsverhaeltnis
import com.example.helloandroid.timerecording.ZeitArbeitsverhaeltnis
import com.example.helloandroid.timerecording.config.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Test
import org.threeten.bp.LocalDate
import java.math.BigDecimal

class HelloJsonTest {

    @Test
    fun jsonToObjects() {
        val zeitArbeitsverhaeltnis = ZeitArbeitsverhaeltnis().apply {
            this.anbaugeraet = Anbaugeraet()
            this.arbeitszeit = Arbeitszeit("5.0")
            this.fahrzeug = Fahrzeug()
            this.taetigkeit = Taetigkeit()
            this.kommentar = "sfdf"
            this.leistungsnehmer = Person()
            this.leistungserbringer = Person()
            this.datum = LocalDate.now()
        }
        val stueckArbeitsverhaeltnis = StueckArbeitsverhaeltnis().apply {
            this.kommentar = "sfdf"
            this.leistungsnehmer = Person()
            this.leistungserbringer = Person()
            this.datum = LocalDate.now()
            this.stueckzahl = 2
            this.produkt = Produkt("KEY", "sdfsf", BigDecimal.TEN)
        }
        val zeitArbeitsverhaeltnisJson = HelloJson.objectToJson(zeitArbeitsverhaeltnis)
        val stueckArbeitsverhaeltnisJson = HelloJson.objectToJson(stueckArbeitsverhaeltnis)

        var arbeitseinsatz = HelloJson.fromJson<Arbeitseinsatz>(zeitArbeitsverhaeltnisJson)
        println(arbeitseinsatz)

        throw HelloException("sfd")
        arbeitseinsatz = HelloJson.fromJson<Arbeitseinsatz>(stueckArbeitsverhaeltnisJson)
        println(arbeitseinsatz)


    }

    companion object {
        inline fun <reified T> fromJson(json: String): T {
            return Gson().fromJson(json, object : TypeToken<T>() {}.type)
        }
    }
}