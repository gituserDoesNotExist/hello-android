package com.example.helloandroid.timerecording.web.remotemodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Fields {

    @SerializedName("definitions")
    @Expose
    var definitions: List<Definition>? = null

}
