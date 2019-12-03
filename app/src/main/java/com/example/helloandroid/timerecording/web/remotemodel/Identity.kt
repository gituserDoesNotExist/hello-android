package com.example.helloandroid.timerecording.web.remotemodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Identity {

    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("customize")
    @Expose
    var customize: Boolean? = null
    @SerializedName("show_custom_logo")
    @Expose
    var showCustomLogo: Boolean? = null
    @SerializedName("logo_uri")
    @Expose
    var logoUri: String? = null
    @SerializedName("header_background_color")
    @Expose
    var headerBackgroundColor: String? = null
    @SerializedName("header_font_color")
    @Expose
    var headerFontColor: String? = null

}

