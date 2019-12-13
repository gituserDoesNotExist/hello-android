package com.example.helloandroid.timerecording.web.remotemodel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class TeamupDeleteEventResponseDTO {

    @SerializedName("undo_id")
    @Expose
    lateinit var undoId: String

}