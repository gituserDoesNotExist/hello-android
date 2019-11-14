package com.example.helloandroid.finances.persistence

import androidx.room.*
import com.example.helloandroid.persistence.BaseEntity
import java.math.BigDecimal
import java.util.*
import java.util.function.BinaryOperator
import kotlin.collections.ArrayList


@Entity(tableName = "POSTEN", indices = [Index(value = ["NAME"], unique = true)])
class Posten(name: String) : BaseEntity() {

    @ColumnInfo(name = "NAME")
    val name: String = name

    @Ignore var ausgaben: MutableList<Ausgabe> = ArrayList()

    fun calculateGesamtausgaben(): BigDecimal {
        return ausgaben.stream().map(Ausgabe::wert).reduce(BigDecimal::add).orElse(BigDecimal.ZERO)
    }

}