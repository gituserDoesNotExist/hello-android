package com.example.helloandroid.timerecording.repository

import com.example.helloandroid.timerecording.Arbeitseinsatz
import com.example.helloandroid.timerecording.EventInfo
import com.example.helloandroid.timerecording.StueckArbeitsverhaeltnis
import com.example.helloandroid.timerecording.ZeitArbeitsverhaeltnis
import com.example.helloandroid.timerecording.view.CalendarConfiguration
import com.example.helloandroid.timerecording.view.Suchkriterien
import com.example.helloandroid.timerecording.web.TeamUpApi
import com.example.helloandroid.timerecording.web.TeamUpDateConverter
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class ArbeitsverhaeltnisRepository(private val teamUpApi: TeamUpApi) {

    private val arbeitseinsatzMapper = ArbeitseinsatzMapper()


    fun fetchArbeitsverhaeltnisseFromRemote(suchkriterien: Suchkriterien,
                                            config: CalendarConfiguration): Single<List<Arbeitseinsatz>> {
        val start = TeamUpDateConverter.fromDateToFetchEventsQueryString(suchkriterien.startDate.getSuchkriterium())
        val end = TeamUpDateConverter.fromDateToFetchEventsQueryString(suchkriterien.endDate.getSuchkriterium())
        return teamUpApi.getEvents(start, end)//
            .subscribeOn(Schedulers.io())//
            .map { events ->
                events.events.map {
                    arbeitseinsatzMapper.fromRemoteEventToArbeitseinsatz(it, config)
                }
            }.map { arbeitseinsaetze ->
                arbeitseinsaetze.filter {
                    it.arbeitsverhaeltnis.matchesSuchkriterien(suchkriterien)
                }
            }
    }

    fun addZeitArbeitsverhaeltnisToRemoteCalendar(verhaeltnis: ZeitArbeitsverhaeltnis,
                                                  erstelltVon: String): Single<Long> {
        val info = EventInfo(erstelltVon = erstelltVon)
        val event = arbeitseinsatzMapper.fromZeitArbeitsverhaeltnisToRemoteEvent(verhaeltnis, info)
        return teamUpApi.postEvent(event)//
            .subscribeOn(Schedulers.io()).map { it.event.id.toLong() }
    }

    fun updateZeitArbeitsverhaeltnis(arbeitsverhaeltnis: ZeitArbeitsverhaeltnis, info: EventInfo): Single<String> {
        val remoteEvent = arbeitseinsatzMapper.fromZeitArbeitsverhaeltnisToRemoteEvent(arbeitsverhaeltnis, info)
        return teamUpApi.updateEvent(remoteEvent.id, remoteEvent)//
            .subscribeOn(Schedulers.io())//
            .map {
                it.event.id
            }
    }


    fun addStueckArbeitsverhaeltnisToRemoteCalendar(verhaeltnis: StueckArbeitsverhaeltnis, who: String): Single<Long> {
        val info = EventInfo(erstelltVon = who)
        val event = arbeitseinsatzMapper.fromStueckArbeitsverhaeltnisToRemoteEvent(verhaeltnis, info)
        return teamUpApi.postEvent(event)//
            .subscribeOn(Schedulers.io()).map { it.event.id.toLong() }
    }

    fun updateStueckArbeitsverhaeltnis(arbeitsverhaeltnis: StueckArbeitsverhaeltnis, info: EventInfo): Single<String> {
        val remoteEvent = arbeitseinsatzMapper.fromStueckArbeitsverhaeltnisToRemoteEvent(arbeitsverhaeltnis, info)
        return teamUpApi.updateEvent(remoteEvent.id, remoteEvent)//
            .subscribeOn(Schedulers.io())//
            .map {
                it.event.id
            }
    }


    fun deleteArbeitseinsatz(eventInfo: EventInfo): Single<String> {
        return teamUpApi.deleteEvent(eventInfo.remoteCalenderId, eventInfo.version)//
            .subscribeOn(Schedulers.io())//
            .map { it.undoId }
    }


}
