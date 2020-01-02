package com.example.helloandroid.timerecording.repository

import com.example.helloandroid.timerecording.TeamupEvent
import com.example.helloandroid.timerecording.TeamupEvents
import com.example.helloandroid.timerecording.TeamupServiceGenerator
import com.example.helloandroid.timerecording.view.Suchkriterien
import com.example.helloandroid.timerecording.web.TeamUpDateConverter
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class ArbeitsverhaeltnisRepository {

    private val teamUpApi = TeamupServiceGenerator.createService()
    private val teamupEventMapper = TeamupEventToRemoteEventMapper()

    fun fetchArbeitsverhaeltnisseFromRemote(suchkriterien: Suchkriterien): Single<TeamupEvents> {
        val start = TeamUpDateConverter.fromDateToFetchEventsQueryString(suchkriterien.startDate.getSuchkriterium())
        val end = TeamUpDateConverter.fromDateToFetchEventsQueryString(suchkriterien.endDate.getSuchkriterium())
        return teamUpApi.getEvents(start, end)//
            .subscribeOn(Schedulers.io())//
            .map { events -> events.events.map { teamupEventMapper.fromRemoteToTeamupEvent(it) } }//
            .map {
                val filteredEvents = it.filter { event -> matchesSuchkriterien(suchkriterien, event) }
                TeamupEvents(filteredEvents)
            }
    }

    private fun matchesSuchkriterien(suchkriterien: Suchkriterien, event: TeamupEvent): Boolean {
        val verhaeltnis = event.arbeitsverhaeltnis
        val containsLeistungsnehmer = suchkriterien.shouldSearchForLeistungsnehmer(verhaeltnis.leistungsnehmer.name)
        val containsLeistungserbringer =
            suchkriterien.shouldSearchForLeistungserbringer(verhaeltnis.leistungserbringer.name)
        val containsKategorie = suchkriterien.shouldSearchForKategorie(verhaeltnis.kategorie)
        return containsLeistungsnehmer && containsLeistungserbringer && containsKategorie
    }

    fun addTeamupEventToRemoteCalendar(event: TeamupEvent): Single<Long> {
        val event = teamupEventMapper.fromArbeitsverhaeltnisToRemoteEvent(event.arbeitsverhaeltnis, event.erstelltVon)
        return teamUpApi.postEvent(event)//
            .subscribeOn(Schedulers.io()).map { it.event.id.toLong() }
    }

    fun updateArbeitsverhaeltnisInRemoteCalender(teamupEvent: TeamupEvent): Single<TeamupEvent> {
        val remoteEvent = teamupEventMapper.fromTeamupEventToRemoteEvent(teamupEvent)
        return teamUpApi.updateEvent(remoteEvent.id, remoteEvent)//
            .subscribeOn(Schedulers.io())//
            .map { teamupEventMapper.fromRemoteToTeamupEvent(it.event) }
    }

    fun deleteTeamupEvent(teamupEvent: TeamupEvent) {
        teamUpApi.deleteEvent(teamupEvent.remoteCalenderId, teamupEvent.version)//
            .subscribeOn(Schedulers.io())//
            .subscribe()
    }


}
