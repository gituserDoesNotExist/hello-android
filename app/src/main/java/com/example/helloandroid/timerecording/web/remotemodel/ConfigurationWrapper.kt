package com.example.helloandroid.timerecording.web.remotemodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ConfigurationWrapper {

    @SerializedName("configuration")
    @Expose
    var configuration: Configuration? = null

}