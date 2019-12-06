package com.example.helloandroid

import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth
import java.util.stream.Stream

fun Stream<LocalDate>.findMinDate() : LocalDate {
    return this.min { o1, o2 ->  o1.compareTo(o2)}.orElseThrow { IllegalArgumentException("Empty List...") }
}

fun List<LocalDate>.findMinDate() : LocalDate {
    return this.stream().findMinDate()
}

fun LocalDate.startOfMonth() : LocalDate {
    return YearMonth.from(LocalDate.now()).atDay(1)
}

fun LocalDate.endOfMonth() : LocalDate {
    return YearMonth.from(LocalDate.now()).atEndOfMonth()
}
