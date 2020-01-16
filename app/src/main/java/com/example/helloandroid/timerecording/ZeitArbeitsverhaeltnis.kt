package com.example.helloandroid.timerecording

import android.content.res.Resources
import com.example.helloandroid.R
import com.example.helloandroid.timerecording.config.Maschine
import com.example.helloandroid.timerecording.config.Taetigkeit
import com.example.helloandroid.timerecording.view.Suchkriterien
import org.apache.commons.lang3.StringUtils
import org.threeten.bp.LocalDateTime
import java.math.BigDecimal

class ZeitArbeitsverhaeltnis(var fahrzeug: Maschine? = null, var anbaugeraet: Maschine? = null,
                             var taetigkeit: Taetigkeit = Taetigkeit(),
                             var arbeitszeit: Arbeitszeit = Arbeitszeit(BigDecimal.ZERO)) : Arbeitsverhaeltnis() {


    override fun createTitle(): String {
        return taetigkeit.bezeichnung
    }

    override fun createDescription(resources: Resources): String {
        val fahrzeugBezeichnung = fahrzeug?.bezeichnung ?: ""
        val anbaugeraetBezeichnung = anbaugeraet?.bezeichnung ?: ""
        return when {
            (StringUtils.isBlank(fahrzeugBezeichnung) && StringUtils.isBlank(anbaugeraetBezeichnung)) -> ""
            StringUtils.isBlank(anbaugeraetBezeichnung) -> fahrzeugBezeichnung
            else -> {
                resources.getString(//
                    R.string.description_zeit_arbeitsverhaeltnis, fahrzeugBezeichnung, anbaugeraetBezeichnung)

            }
        }
    }

    override fun getQuantity(): String {
        return arbeitszeit.toString()
    }


    override fun calculateEndDatum(): LocalDateTime {
        return datum.atStartOfDay().plusMinutes(arbeitszeit.getTimeInMinutes())
    }

    override fun calculateKostenForArbeitsverhaeltnis(): BigDecimal {
        val dauer = arbeitszeit.dauer
        val kostenFahrzeug = fahrzeug?.stundensatz?.times(dauer) ?: BigDecimal.ZERO
        val kostenAnbaugeraet = anbaugeraet?.stundensatz?.times(dauer) ?: BigDecimal.ZERO
        val kostenLeistungserbringer = leistungserbringer?.stundensatz?.times(dauer) ?: BigDecimal.ZERO

        return kostenFahrzeug.plus(kostenAnbaugeraet).plus(kostenLeistungserbringer)
    }

    override fun matchesSuchkriterien(suchkriterien: Suchkriterien): Boolean {
        val containsTaetigkeit = suchkriterien.shouldSearchForTaetigkeit(taetigkeit.key)
        return super.matchesSuchkriterien(suchkriterien) && containsTaetigkeit
    }

    fun copy(): ZeitArbeitsverhaeltnis {
        val source = this
        return ZeitArbeitsverhaeltnis().apply {
            this.anbaugeraet = source.anbaugeraet
            this.fahrzeug = source.fahrzeug
            this.arbeitszeit = source.arbeitszeit
            this.taetigkeit = source.taetigkeit
            mapToArbeitsverhaeltnis(source, this)
        }
    }


}