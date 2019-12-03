package com.example.helloandroid.timerecording.web.remotemodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Features {

    @SerializedName("calendar_limit")
    @Expose
    var calendarLimit: Int? = null
    @SerializedName("daily_agenda")
    @Expose
    var dailyAgenda: Boolean? = null
    @SerializedName("file_storage")
    @Expose
    var fileStorage: Int? = null
    @SerializedName("passwords")
    @Expose
    var passwords: Boolean? = null
    @SerializedName("history")
    @Expose
    var history: Int? = null
    @SerializedName("refresh_param")
    @Expose
    var refreshParam: Boolean? = null
    @SerializedName("file_upload")
    @Expose
    var fileUpload: Boolean? = null
    @SerializedName("notification_for_past")
    @Expose
    var notificationForPast: Boolean? = null
    @SerializedName("import_feed_frequency")
    @Expose
    var importFeedFrequency: List<Int>? = null
    @SerializedName("show_footer_logo")
    @Expose
    var showFooterLogo: Boolean? = null
    @SerializedName("custom_field_limit")
    @Expose
    var customFieldLimit: Int? = null
    @SerializedName("custom_field_option_limit")
    @Expose
    var customFieldOptionLimit: Int? = null

}

