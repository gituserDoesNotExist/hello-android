package com.example.helloandroid.timerecording.web

import com.example.helloandroid.timerecording.web.remotemodel.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface TeamUpApi {

    @Headers("Teamup-Token: ${TeamupCalenderConfig.API_KEY}")
    @GET("check-access")
    fun checkAccess() : Single<CheckAccessResponseDTO>

    @Headers("Teamup-Token: ${TeamupCalenderConfig.API_KEY}")
    @POST("${TeamupCalenderConfig.CALENDAR_KEY}/events")
    fun postEvent(request: TeamupCreateEventRequestDTO): Single<TeamupPostEventResponseDTO>

    @Headers("Teamup-Token: ${TeamupCalenderConfig.API_KEY}")
    @GET("${TeamupCalenderConfig.CALENDAR_KEY}/events")
    fun getEvents(@Query("startDate") startDate: String, @Query("endDate") endDate: String): Single<Events>

    @Headers("Teamup-Token: ${TeamupCalenderConfig.API_KEY}")
    @GET("${TeamupCalenderConfig.CALENDAR_KEY}/configuration")
    fun getConfiguration(): Single<ConfigurationWrapper>


}