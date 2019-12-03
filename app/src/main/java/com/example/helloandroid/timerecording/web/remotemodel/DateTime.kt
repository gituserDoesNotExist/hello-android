package com.example.helloandroid.timerecording.web.remotemodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class DateTime {

    @SerializedName("tz_dynamic")
    @Expose
    var tzDynamic: Boolean? = null
    @SerializedName("tz")
    @Expose
    var tz: String? = null
    @SerializedName("tz_show")
    @Expose
    var tzShow: Boolean? = null
    @SerializedName("date_locale")
    @Expose
    var dateLocale: String? = null
    @SerializedName("use_24h_times")
    @Expose
    var use24hTimes: Boolean? = null
    @SerializedName("time_range_start")
    @Expose
    var timeRangeStart: Int? = null
    @SerializedName("time_range_end")
    @Expose
    var timeRangeEnd: Int? = null
    @SerializedName("start_date_type")
    @Expose
    var startDateType: String? = null
    @SerializedName("start_date")
    @Expose
    var startDate: String? = null
    @SerializedName("start_date_pattern")
    @Expose
    var startDatePattern: String? = null
    @SerializedName("week_start")
    @Expose
    var weekStart: Int? = null
    @SerializedName("scroll_start_hour")
    @Expose
    var scrollStartHour: Int? = null
    @SerializedName("default_event_duration")
    @Expose
    var defaultEventDuration: Int? = null

}
