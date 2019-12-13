package com.example.helloandroid.timerecording

class TeamupEvents(val events: List<TeamupEvent>) {



    fun findById(remoteId: String): TeamupEvent? {
        return events.find { it.remoteCalenderId == remoteId}
    }

}
