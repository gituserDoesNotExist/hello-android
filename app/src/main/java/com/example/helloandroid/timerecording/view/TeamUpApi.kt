package com.example.helloandroid.timerecording.view

import com.example.helloandroid.timerecording.web.CheckAccessResponseDTO
import com.example.helloandroid.timerecording.web.TeamupCalenderConfig
import com.example.helloandroid.timerecording.web.TeamupCreateEventRequestDTO
import com.example.helloandroid.timerecording.web.TeamupPostEventResponseDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import rx.Single

interface TeamUpApi {

    @Headers("Teamup-Token: ${TeamupCalenderConfig.API_KEY}")
    @GET("check-access")
    fun checkAccess() : Single<CheckAccessResponseDTO>

    @Headers("Teamup-Token: ${TeamupCalenderConfig.API_KEY}")
    @POST("${TeamupCalenderConfig.CALENDAR_KEY}}/events")
    fun postEvent(request: TeamupCreateEventRequestDTO): Single<TeamupPostEventResponseDTO>

}