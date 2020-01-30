package com.example.helloandroid.timerecording.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TITLE_ENTITY")
class TitleEntity(title: String) {

    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 1

    var title = title


}