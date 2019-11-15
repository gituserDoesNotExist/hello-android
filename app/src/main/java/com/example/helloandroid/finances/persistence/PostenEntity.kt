package com.example.helloandroid.finances.persistence

import androidx.room.*
import com.example.helloandroid.persistence.BaseEntity
import java.math.BigDecimal
import kotlin.collections.ArrayList


@Entity(tableName = "POSTEN_ENTITY", indices = [Index(value = ["NAME"], unique = true)])
class PostenEntity(name: String) : BaseEntity() {

    @ColumnInfo(name = "NAME")
    val name: String = name

}