package com.example.helloandroid.timerecording

import androidx.databinding.ObservableField
import org.threeten.bp.LocalDate
import ru.gildor.databinding.observables.ObservableString

open class ArbeitsverhaeltnisForAnzeige {


    var datum = ObservableField<LocalDate>().apply { this.set(LocalDate.now()) }
    var leistungserbringer = ObservableString()
    var leistungsnehmer = ObservableString()
    var kommentar = ObservableString()

    protected fun copyValuesFromArbeitsverhaeltnis(arbeitsverhaeltnis: Arbeitsverhaeltnis) {
        this.datum.set(arbeitsverhaeltnis.datum)
        this.leistungserbringer.set(arbeitsverhaeltnis.leistungserbringer?.name ?: "")
        this.leistungsnehmer.set(arbeitsverhaeltnis.leistungsnehmer.name)
        this.kommentar.set(arbeitsverhaeltnis.kommentar)
    }


}