package com.example.helloandroid.timerecording

import android.content.res.Resources
import com.example.helloandroid.R
import com.example.helloandroid.timerecording.config.Produkt
import java.math.BigDecimal

class StueckArbeitsverhaeltnis(var stueckzahl: Int = 0, var produkt: Produkt = Produkt()) : Arbeitsverhaeltnis() {

    override fun createDescription(resources: Resources): String {
        return resources.getString(R.string.description_stueck_arbeitsverhaeltnis, produkt.name, stueckzahl.toString())
    }

    override fun calculateKostenForArbeitsverhaeltnis(): BigDecimal {
        return BigDecimal(stueckzahl).times(produkt.kostenProStueck)
    }


    fun copy(): StueckArbeitsverhaeltnis {
        val source = this
        return StueckArbeitsverhaeltnis().apply {
            this.stueckzahl = source.stueckzahl
            this.produkt = source.produkt
            mapToArbeitsverhaeltnis(source, this)
        }
    }

}