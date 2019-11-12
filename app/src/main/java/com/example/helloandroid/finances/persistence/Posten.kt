package com.example.helloandroid.finances.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import com.example.helloandroid.persistence.BaseEntity
import java.time.LocalDateTime
import java.util.*


@Entity(tableName = "POSTEN", indices = [Index(value = ["NAME"], unique = true)])
class Posten(name: String) : BaseEntity() {

    @ColumnInfo(name = "NAME")
    val name: String = name

}