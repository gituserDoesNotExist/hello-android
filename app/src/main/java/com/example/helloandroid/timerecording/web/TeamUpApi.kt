package com.example.helloandroid.timerecording.web

import com.example.helloandroid.timerecording.web.remotemodel.*
import io.reactivex.Single
import retrofit2.http.*

interface TeamUpApi {

    @Headers("Teamup-Token: ${TeamupCalenderConfig.API_KEY}")
    @GET("check-access")
    fun checkAccess(): Single<CheckAccessResponseDTO>

    @Headers("Teamup-Token: ${TeamupCalenderConfig.API_KEY}")
    @POST("${TeamupCalenderConfig.CALENDAR_KEY}/events")
    fun postEvent(@Body request: Event): Single<EventResponseDTO>


    @Headers("Teamup-Token: ${TeamupCalenderConfig.API_KEY}")
    @PUT("${TeamupCalenderConfig.CALENDAR_KEY}/events/{event_id}")
    fun updateEvent(//
        @Path("event_id") eventId: String,//
        @Body request: Event): Single<EventResponseDTO>


    @Headers("Teamup-Token: ${TeamupCalenderConfig.API_KEY}")
    @DELETE("${TeamupCalenderConfig.CALENDAR_KEY}/events/{event_id}")
    fun deleteEvent(//
        @Path("event_id") eventId: String,//
        @Query("version") version: String): Single<TeamupDeleteEventResponseDTO>

    @Headers("Teamup-Token: ${TeamupCalenderConfig.API_KEY}")
    @GET("${TeamupCalenderConfig.CALENDAR_KEY}/events")
    fun getEvents(@Query("startDate") startDate: String, @Query("endDate") endDate: String): Single<Events>

    @Headers("Teamup-Token: ${TeamupCalenderConfig.API_KEY}")
    @GET("${TeamupCalenderConfig.CALENDAR_KEY}/configuration")
    fun getConfiguration(): Single<ConfigurationWrapper>


}