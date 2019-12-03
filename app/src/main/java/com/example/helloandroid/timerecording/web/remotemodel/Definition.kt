package com.example.helloandroid.timerecording.web.remotemodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Definition {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("type_data")
    @Expose
    var typeData: TypeData? = null
    @SerializedName("active")
    @Expose
    var active: Boolean? = null
    @SerializedName("hidden")
    @Expose
    var hidden: Boolean? = null
    @SerializedName("creation_dt")
    @Expose
    var creationDt: String? = null
    @SerializedName("update_dt")
    @Expose
    var updateDt: Any? = null

}
