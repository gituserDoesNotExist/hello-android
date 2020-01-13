package com.example.helloandroid.timerecording

import com.example.helloandroid.view.BigDecimalConverter
import ru.gildor.databinding.observables.ObservableString

class ZeitArbeitsverhaeltnisForAnzeige : ArbeitsverhaeltnisForAnzeige() {


    var fahrzeug = ObservableString()
    var anbaugeraet = ObservableString()
    var taetigkeit = ObservableString()
    var arbeitszeit = ObservableString()

    fun copyValuesFromArbeitsverhaeltnis(arbeitsverhaeltnis: ZeitArbeitsverhaeltnis) {
        super.copyValuesFromArbeitsverhaeltnis(arbeitsverhaeltnis)
        this.fahrzeug.set(arbeitsverhaeltnis.fahrzeug?.bezeichnung ?: "")
        this.anbaugeraet.set(arbeitsverhaeltnis.anbaugeraet?.bezeichnung ?: "")
        this.taetigkeit.set(arbeitsverhaeltnis.taetigkeit.bezeichnung)
        this.arbeitszeit.set(BigDecimalConverter.bigDecimalToString(arbeitsverhaeltnis.arbeitszeit.dauer))
    }


}