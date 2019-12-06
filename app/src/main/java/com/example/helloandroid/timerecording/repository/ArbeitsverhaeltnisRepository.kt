package com.example.helloandroid.timerecording.repository

import com.example.helloandroid.timerecording.Arbeitsverhaeltnis
import com.example.helloandroid.timerecording.Arbeitsverhaeltnisse
import com.example.helloandroid.timerecording.TeamupServiceGenerator
import com.example.helloandroid.timerecording.web.TeamUpDateConverter
import com.example.helloandroid.timerecording.web.remotemodel.Event
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.threeten.bp.LocalDate

class ArbeitsverhaeltnisRepository {

    private val teamUpApi = TeamupServiceGenerator.createService()
    private val eventToArbeitsverhaeltnisMapper = EventToArbeitsverhaeltnisMapper()

    fun fetchArbeitsverhaeltnisseFromRemote(startDate: LocalDate, endDate: LocalDate): Single<Arbeitsverhaeltnisse> {
        val start = TeamUpDateConverter.fromDateToFetchEventsQueryString(startDate)
        val end = TeamUpDateConverter.fromDateToFetchEventsQueryString(endDate)
        return teamUpApi.getEvents(start, end)//
            .subscribeOn(Schedulers.io())//
            .map {
                eventToArbeitsverhaeltnisMapper.fromEventsToArbeitsverhaeltnisse(it)
            }
    }

    fun addArbeitsverhaeltnisToRemoteCalendar(arbeitsverhaeltnis: Arbeitsverhaeltnis): Single<Long> {
        val event = eventToArbeitsverhaeltnisMapper.fromArbeitsverhaeltnisToEvent(arbeitsverhaeltnis)
        return teamUpApi.postEvent(event)//
            .subscribeOn(Schedulers.io())
            .map {
                it.id
            }
    }

    fun deleteArbeitsverhaeltnis(currentArbeitsverhaeltnis: Arbeitsverhaeltnis) {
        teamUpApi.deleteEvent(currentArbeitsverhaeltnis.remoteId).subscribeOn(Schedulers.io()).subscribe(object: SingleObserver<Event> {
            override fun onSubscribe(d: Disposable) {
                println("sdf")
            }

            override fun onError(e: Throwable) {
                println("sdf")
            }

            override fun onSuccess(t: Event) {
                println("sdf")
            }

        })
    }


}
