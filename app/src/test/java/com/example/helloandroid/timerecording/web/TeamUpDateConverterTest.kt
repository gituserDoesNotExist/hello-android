package com.example.helloandroid.timerecording.web

import com.example.helloandroid.ZoneIds
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

class TeamUpDateConverterTest {

    companion object {
        private val ZDT = LocalDateTime.of(2000, 10, 1, 1, 2, 3).atZone(ZoneId.of(ZoneIds.EUROPE_BERLIN.zoneId))
        private const val ZDT_STRING = "2000-10-01T01:02:03+02:00"
    }

    @Test
    fun asString() {
        val result = TeamUpDateConverter.asZonedDateTimeString(ZDT)

        assertThat(result).isEqualTo(ZDT_STRING)
    }

    @Test
    fun asDate() {
        val result = TeamUpDateConverter.asZonedDateTime(ZDT_STRING)

        assertThat(result.toString()).isEqualTo(ZDT.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
    }
}