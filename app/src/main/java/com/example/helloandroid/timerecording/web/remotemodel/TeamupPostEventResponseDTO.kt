package com.example.helloandroid.timerecording.web.remotemodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class TeamupPostEventResponseDTO {

    @SerializedName("id")
    @Expose
    var id: Long = 0

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
    lateinit var allDay: String

    @SerializedName("rrule")
    @Expose
    lateinit var rrule: String

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
    var notes: Any? = null

    @SerializedName("version")
    @Expose
    lateinit var version: String

    @SerializedName("ristart_dt")
    @Expose
    var ristartDt: String? = null

    @SerializedName("creation_dt")
    @Expose
    lateinit var creationDt: String

    @SerializedName("update_dt")
    @Expose
    var updateDt: String? = null

    @SerializedName("readonly")
    @Expose
    lateinit var readonly: String
}