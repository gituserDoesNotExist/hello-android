package com.example.helloandroid.timerecording.web.remotemodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class EnabledEventSharingServices {

    @SerializedName("page")
    @Expose
    var page: Boolean? = null
    @SerializedName("facebook")
    @Expose
    var facebook: Boolean? = null
    @SerializedName("twitter")
    @Expose
    var twitter: Boolean? = null
    @SerializedName("linkedin")
    @Expose
    var linkedin: Boolean? = null
    @SerializedName("whatsapp")
    @Expose
    var whatsapp: Boolean? = null
    @SerializedName("xing")
    @Expose
    var xing: Boolean? = null
    @SerializedName("email")
    @Expose
    var email: Boolean? = null
    @SerializedName("google")
    @Expose
    var google: Boolean? = null
    @SerializedName("outlook")
    @Expose
    var outlook: Boolean? = null
    @SerializedName("ical")
    @Expose
    var ical: Boolean? = null
    @SerializedName("yahoo")
    @Expose
    var yahoo: Boolean? = null
    @SerializedName("other")
    @Expose
    var other: Boolean? = null

}
