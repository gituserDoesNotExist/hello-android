package com.example.helloandroid.timerecording

import androidx.databinding.ObservableInt
import ru.gildor.databinding.observables.ObservableString

class StueckArbeitsverhaeltnisForAnzeige : ArbeitsverhaeltnisForAnzeige() {


    var stueckzahl = ObservableInt()
    var produktName = ObservableString()

    fun copyValuesFromArbeitsverhaeltnis(arbeitsverhaeltnis: StueckArbeitsverhaeltnis) {
        super.copyValuesFromArbeitsverhaeltnis(arbeitsverhaeltnis)
        this.stueckzahl.set(arbeitsverhaeltnis.stueckzahl)
        this.produktName.set(arbeitsverhaeltnis.produkt.name)
    }


}