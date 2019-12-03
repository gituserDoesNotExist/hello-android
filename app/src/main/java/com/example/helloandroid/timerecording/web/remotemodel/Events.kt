package com.example.helloandroid.timerecording.web.remotemodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Events {

    @SerializedName("events")
    @Expose
    lateinit var events: List<Event>
    @SerializedName("timestamp")
    @Expose
    var timestamp: Int? = null

}