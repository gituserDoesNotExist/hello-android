package com.example.helloandroid.timerecording.repository

import com.example.helloandroid.timerecording.TeamupEvent
import com.example.helloandroid.timerecording.TeamupEvents
import com.example.helloandroid.timerecording.TeamupServiceGenerator
import com.example.helloandroid.timerecording.web.TeamUpDateConverter
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.threeten.bp.LocalDate

class ArbeitsverhaeltnisRepository {

    private val teamUpApi = TeamupServiceGenerator.createService()
    private val teamupEventMapper = TeamupEventToRemoteEventMapper()

    fun fetchArbeitsverhaeltnisseFromRemote(startDate: LocalDate, endDate: LocalDate): Single<TeamupEvents> {
        val start = TeamUpDateConverter.fromDateToFetchEventsQueryString(startDate)
        val end = TeamUpDateConverter.fromDateToFetchEventsQueryString(endDate)
        return teamUpApi.getEvents(start, end)//
            .subscribeOn(Schedulers.io())//
            .map {
                teamupEventMapper.fromRemoteEventsToTeamupEvents(it)
            }
    }

    fun addTeamupEventToRemoteCalendar(event: TeamupEvent): Single<Long> {
        return teamUpApi.postEvent(teamupEventMapper.fromTeamupEventToRemoteEvent(event))//
            .subscribeOn(Schedulers.io()).map { it.id.toLong() }
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
