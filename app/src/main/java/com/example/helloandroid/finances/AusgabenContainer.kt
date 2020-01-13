package com.example.helloandroid.finances

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import java.util.stream.Collectors

class AusgabenContainer constructor(private val ausgaben: List<Ausgabe>) {


    fun findEarliestAusgabe(): LocalDate {
        return ausgaben.stream()//
            .map(Ausgabe::datum)//
            .min { o1, o2 -> o1.compareTo(o2) }//
            .orElse(LocalDateTime.now()).toLocalDate()
    }

     fun findAusgabenForDateRange(startDate: LocalDate, endDate: LocalDate): MutableList<Ausgabe> {
        return ausgaben.stream()//
            .filter { t -> !t.datum.toLocalDate().isBefore(startDate) && !t.datum.toLocalDate().isAfter(endDate) }//
            .collect(Collectors.toList())
    }



}