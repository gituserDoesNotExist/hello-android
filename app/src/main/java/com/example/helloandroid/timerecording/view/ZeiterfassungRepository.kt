package com.example.helloandroid.timerecording.view

import com.example.helloandroid.timerecording.TeamupServiceGenerator
import com.example.helloandroid.timerecording.Zeiterfassung
import com.example.helloandroid.timerecording.web.TeamupCalenderConfig.SUBCALENDAR_ID_NACHBARSCHAFTSHILFE
import com.example.helloandroid.timerecording.web.TeamupCreateEventMapper
import com.example.helloandroid.timerecording.web.TeamupPostEventResponseDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import rx.schedulers.Schedulers

class ZeiterfassungRepository {

    private val teamUpApi = TeamupServiceGenerator.createService()
    private val teamupMapper = TeamupCreateEventMapper()

    fun addEintragToTeamUpNachbarschaftshilfe(zeiterfassung: Zeiterfassung) {
        val request = teamupMapper.asCreateEvent(zeiterfassung, SUBCALENDAR_ID_NACHBARSCHAFTSHILFE)

        teamUpApi.postEvent(request).subscribeOn(Schedulers.io())

        teamUpApi.postEvent(request).enqueue(object : Callback<TeamupPostEventResponseDTO> {
            override fun onResponse(call: Call<TeamupPostEventResponseDTO>?,response: Response<TeamupPostEventResponseDTO>?) {
                println("this did work as expected!")
            }

            override fun onFailure(call: Call<TeamupPostEventResponseDTO>?, t: Throwable?) {
                println("this did not work as expected...")
            }
        })


        println("adding shit")
    }


}
