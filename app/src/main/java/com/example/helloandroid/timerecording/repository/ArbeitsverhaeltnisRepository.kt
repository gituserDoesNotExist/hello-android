package com.example.helloandroid.timerecording.repository

import com.example.helloandroid.timerecording.Arbeitsverhaeltnis
import com.example.helloandroid.timerecording.TeamupServiceGenerator
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class ArbeitsverhaeltnisRepository {

    private val teamUpApi = TeamupServiceGenerator.createService()
    private val eventToArbeitsverhaeltnisMapper = EventToArbeitsverhaeltnisMapper()

    fun fetchAllArbeitsverhaeltnisseFromRemoteCalender(): Single<List<Arbeitsverhaeltnis>> {
        return teamUpApi.getEvents("2019-12-03", "2019-12-05")//
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


}
