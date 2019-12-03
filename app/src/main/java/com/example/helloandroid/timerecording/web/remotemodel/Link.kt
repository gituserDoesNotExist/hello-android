package com.example.helloandroid.timerecording.web.remotemodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Link {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("key")
    @Expose
    var key: String? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("app_id")
    @Expose
    var appId: Int? = null

}
