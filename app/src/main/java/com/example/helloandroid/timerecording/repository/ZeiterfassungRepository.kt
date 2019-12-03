package com.example.helloandroid.timerecording.repository

import com.example.helloandroid.timerecording.Arbeitsverhaeltnis
import com.example.helloandroid.timerecording.TeamupServiceGenerator
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class ZeiterfassungRepository {

    private val teamUpApi = TeamupServiceGenerator.createService()
    private val arbeitsverhaeltnisMapper = EventToArbeitsverhaeltnisMapper()

    fun fetchAllArbeitsverhaeltnisseFromRemoteCalender(): Single<List<Arbeitsverhaeltnis>> {
        return teamUpApi.getEvents("2019-12-03", "2019-12-05")//
            .subscribeOn(Schedulers.io())//
            .map {
                arbeitsverhaeltnisMapper.fromEventsToArbeitsverhaeltnisse(it)
            }
    }


}
