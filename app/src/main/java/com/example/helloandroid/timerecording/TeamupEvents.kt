package com.example.helloandroid.timerecording

import java.math.BigDecimal

class TeamupEvents(val events: List<TeamupEvent>) {

    fun findById(remoteId: String): TeamupEvent? {
        return events.find { it.remoteCalenderId == remoteId }
    }

    fun gesamtstunden(): BigDecimal {
        return events.stream()//
            .map { it.arbeitsverhaeltnis.arbeitszeit.dauer }//
            .reduce(BigDecimal.ZERO, BigDecimal::add)
    }

}
