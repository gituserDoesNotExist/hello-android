package com.example.helloandroid.timerecording

import java.math.BigDecimal

class Arbeitseinsaetze(val einsaetze: List<Arbeitseinsatz>) {

    fun findById(remoteId: String): Arbeitseinsatz? {
        return einsaetze.find { it.eventInfo.remoteCalenderId == remoteId }
    }


    fun gesamtkosten(): BigDecimal {
        return einsaetze.stream()//
            .map { it.arbeitsverhaeltnis.calculateKostenForArbeitsverhaeltnis() }//
            .reduce(BigDecimal::add)//
            .orElse(BigDecimal.ZERO)
    }

}
