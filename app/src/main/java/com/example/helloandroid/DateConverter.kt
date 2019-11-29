package com.example.helloandroid

import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

object DateConverter {

    fun asString(value: ZonedDateTime, formatter: DateTimeFormatter): String {
        return value.format(formatter)
    }

    fun asDate(value: String, formatter: DateTimeFormatter): ZonedDateTime {
        return ZonedDateTime.parse(value, formatter)
    }

    fun localDateToZonedDateTime(value: LocalDate) :ZonedDateTime {
        return value.atStartOfDay(ZoneId.of(ZoneIds.EUROPE_BERLIN.zoneId))
    }


}