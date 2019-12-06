package com.example.helloandroid.timerecording

import java.util.*


class Arbeitsverhaeltnisse(val verhaeltnisse: List<Arbeitsverhaeltnis>) {


    private val arbeitsverhaeltnisDateComparator: (o1: Arbeitsverhaeltnis, o2: Arbeitsverhaeltnis) -> Int =
        { o1, o2 -> o1.datum.compareTo(o2.datum) }

    fun findEarliestArbeitsverhaeltnis(): Optional<Arbeitsverhaeltnis> {
        return verhaeltnisse.stream().min(arbeitsverhaeltnisDateComparator)
    }

    fun findLatestArbeitsverhaeltnis(): Optional<Arbeitsverhaeltnis> {
        return verhaeltnisse.stream().max(arbeitsverhaeltnisDateComparator)
    }

    fun findById(arbeitsverhaeltnisRemoteId: String): Arbeitsverhaeltnis? {
        return verhaeltnisse.find { it.remoteId == arbeitsverhaeltnisRemoteId }
    }


}