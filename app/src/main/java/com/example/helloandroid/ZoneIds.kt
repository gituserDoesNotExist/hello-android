package com.example.helloandroid

import org.threeten.bp.ZoneId

enum class ZoneIds(val zoneId: String) {

    EUROPE_BERLIN("Europe/Berlin");

    fun getZone(): ZoneId {
        return ZoneId.of(zoneId)
    }

}