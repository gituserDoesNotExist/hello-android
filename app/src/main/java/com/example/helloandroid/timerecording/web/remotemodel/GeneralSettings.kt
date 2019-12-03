package com.example.helloandroid.timerecording.web.remotemodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class GeneralSettings {

    @SerializedName("admin_email")
    @Expose
    var adminEmail: String? = null
    @SerializedName("language")
    @Expose
    var language: String? = null
    @SerializedName("control_panel_default_state")
    @Expose
    var controlPanelDefaultState: String? = null
    @SerializedName("onetomany_enabled")
    @Expose
    var onetomanyEnabled: Boolean? = null
    @SerializedName("onetomany_layout")
    @Expose
    var onetomanyLayout: Int? = null
    @SerializedName("user_signup_enabled")
    @Expose
    var userSignupEnabled: Boolean? = null
    @SerializedName("icsfeeds_enabled")
    @Expose
    var icsfeedsEnabled: Boolean? = null
    @SerializedName("about")
    @Expose
    var about: String? = null
    @SerializedName("about_enabled")
    @Expose
    var aboutEnabled: Boolean? = null
    @SerializedName("about_title")
    @Expose
    var aboutTitle: String? = null
    @SerializedName("event_sharing_enabled")
    @Expose
    var eventSharingEnabled: Boolean? = null
    @SerializedName("enabled_event_sharing_services")
    @Expose
    var enabledEventSharingServices: EnabledEventSharingServices? = null

}
