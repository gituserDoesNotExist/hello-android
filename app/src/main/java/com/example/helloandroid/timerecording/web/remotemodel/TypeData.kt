package com.example.helloandroid.timerecording.web.remotemodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class TypeData {

    @SerializedName("min")
    @Expose
    var min: Int? = null
    @SerializedName("shown_in_title")
    @Expose
    var shownInTitle: String? = null
    @SerializedName("default_enabled")
    @Expose
    var defaultEnabled: Boolean? = null
    @SerializedName("default_deadline_offset")
    @Expose
    var defaultDeadlineOffset: String? = null
    @SerializedName("default_visibility")
    @Expose
    var defaultVisibility: String? = null
    @SerializedName("default_limit")
    @Expose
    var defaultLimit: Int? = null

}

