package com.example.helloandroid.timerecording.web.remotemodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Configuration {

    @SerializedName("general_settings")
    @Expose
    var generalSettings: GeneralSettings? = null
    @SerializedName("calendar_views")
    @Expose
    var calendarViews: CalendarViews? = null
    @SerializedName("date_time")
    @Expose
    var dateTime: DateTime? = null
    @SerializedName("identity")
    @Expose
    var identity: Identity? = null
    @SerializedName("link")
    @Expose
    var link: Link? = null
    @SerializedName("features")
    @Expose
    var features: Features? = null
    @SerializedName("fields")
    @Expose
    var fields: Fields? = null

}
