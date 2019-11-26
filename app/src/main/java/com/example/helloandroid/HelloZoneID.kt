package com.example.helloandroid

import java.util.*

enum class HelloZoneID {

    EUROPE_BERLIN("Europe/Berlin");

    val zoneId : String

    constructor(zoneId: String) {
        this.zoneId = zoneId
    }

    fun getTimeZone() : TimeZone {
        return TimeZone.getTimeZone(zoneId)
    }

}