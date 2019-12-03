package com.example.helloandroid.timerecording.web.remotemodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class TeamupCreateEventRequestDTO {

    @SerializedName("subcalendar_id")
    @Expose
    var subcalendarId: Long = 0

    @SerializedName("start_dt")
    @Expose
    lateinit var startDt: String

    @SerializedName("end_dt")
    @Expose
    lateinit var endDt: String

    @SerializedName("all_day")
    @Expose
    var allDay: Any? = null

    @SerializedName("rrule")
    @Expose
    var rrule: String = ""

    @SerializedName("title")
    @Expose
    lateinit var title: String

    @SerializedName("who")
    @Expose
    lateinit var who: String

    @SerializedName("location")
    @Expose
    lateinit var location: String

    @SerializedName("notes")
    @Expose
    lateinit var notes: String

}
