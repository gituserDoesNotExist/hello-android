package com.example.helloandroid.timerecording.persistence

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TITLE_ENTITY")
class TitleEntity(title: String) {

    @NonNull
    @PrimaryKey
    var title = title


}