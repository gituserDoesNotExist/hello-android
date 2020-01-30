package com.example.helloandroid.timerecording

import androidx.databinding.ObservableField
import org.threeten.bp.LocalDate
import ru.gildor.databinding.observables.ObservableString
import java.math.BigDecimal

open class ArbeitsverhaeltnisForAnzeige {

    var title = ObservableString()
    var datum = ObservableField<LocalDate>().apply { this.set(LocalDate.now()) }
    var leistungserbringer = ObservableString()
    var leistungsnehmer = ObservableString()
    var kommentar = ObservableString()
    var kosten = ObservableField<BigDecimal>()

    protected fun copyValuesFromArbeitsverhaeltnis(arbeitsverhaeltnis: Arbeitsverhaeltnis) {
        this.title.set(arbeitsverhaeltnis.title)
        this.datum.set(arbeitsverhaeltnis.datum)
        this.leistungserbringer.set(arbeitsverhaeltnis.leistungserbringer?.name ?: "")
        this.leistungsnehmer.set(arbeitsverhaeltnis.leistungsnehmer.name)
        this.kommentar.set(arbeitsverhaeltnis.kommentar)
        this.kosten.set(arbeitsverhaeltnis.calculateKostenForArbeitsverhaeltnis())
    }


}