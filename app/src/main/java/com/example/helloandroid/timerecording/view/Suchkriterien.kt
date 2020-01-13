package com.example.helloandroid.timerecording.view

import com.example.helloandroid.endOfMonth
import com.example.helloandroid.startOfMonth
import com.example.helloandroid.timerecording.config.Person
import com.example.helloandroid.timerecording.config.Taetigkeit
import org.threeten.bp.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class Suchkriterien {

    var startDate = DateSuchkriterium(LocalDate.now().startOfMonth())
    var endDate = DateSuchkriterium(LocalDate.now().endOfMonth())

    val leistungserbringerToFilter: MutableList<Person> = ArrayList()
    val leistungsnehmerToFilter: MutableList<Person> = ArrayList()
    val taetigkeitenToFilter: MutableList<Taetigkeit> = ArrayList()

    fun joinLeistungserbringer(): String {
        return leistungserbringerToFilter.joinToString(",") { it.name }
    }


    fun joinLeistungsnehmer(): String {
        return leistungsnehmerToFilter.joinToString(",") { it.name }
    }


    fun joinTaetigkeiten(): String {
        return taetigkeitenToFilter.joinToString(",") { it.bezeichnung }
    }

    fun updateFilterForStartDate(date: LocalDate) {
        this.startDate = DateSuchkriterium(date)
    }

    fun updateFilterForEndDate(date: LocalDate) {
        this.endDate = DateSuchkriterium(date)
    }

    fun addFilterForLeistungsnehmer(value: Person) {
        if (!leistungsnehmerToFilter.contains(value)) {
            leistungsnehmerToFilter.add(value)
        }
    }


    fun addFilterForLeistungserbringer(value: Person) {
        if (!leistungserbringerToFilter.contains(value)) {
            leistungserbringerToFilter.add(value)
        }
    }


    fun addFilterForTaetigkeit(value: Taetigkeit) {
        if (!taetigkeitenToFilter.contains(value)) {
            taetigkeitenToFilter.add(value)
        }
    }


    fun getFiltersForAnzeige(): List<Suchfilter<String>> {
        val filters: MutableMap<FilterKeys, String> = EnumMap(FilterKeys::class.java)
        filters[FilterKeys.START_DATE] = startDate.anzeigename()
        filters[FilterKeys.END_DATE] = endDate.anzeigename()
        filters[FilterKeys.LEISTUNGSNEHMER] = joinLeistungsnehmer()
        filters[FilterKeys.LEISTUNGSERBRINGER] = joinLeistungserbringer()
        filters[FilterKeys.TAETIGKEIT] = joinTaetigkeiten()
        return filters.keys.map { Suchfilter(it, filters.getOrDefault(it, "")) }

    }

    fun removeFilter(filterKey: FilterKeys) {
        when (filterKey) {
            FilterKeys.START_DATE -> startDate = DateSuchkriterium(LocalDate.now().startOfMonth())
            FilterKeys.END_DATE -> endDate = DateSuchkriterium(LocalDate.now().endOfMonth())
            FilterKeys.LEISTUNGSNEHMER -> leistungsnehmerToFilter.clear()
            FilterKeys.LEISTUNGSERBRINGER -> leistungserbringerToFilter.clear()
            FilterKeys.TAETIGKEIT -> taetigkeitenToFilter.clear()
        }
    }

    fun shouldSearchForLeistungsnehmer(key: String): Boolean {
        return leistungsnehmerToFilter.isEmpty() || leistungsnehmerToFilter.any { it.key == key }
    }


    fun shouldSearchForLeistungserbringer(key: String): Boolean {
        return leistungserbringerToFilter.isEmpty() || leistungserbringerToFilter.any { it.key == key }
    }

    fun shouldSearchForTaetigkeit(key: String): Boolean {
        return taetigkeitenToFilter.isEmpty() || taetigkeitenToFilter.any { it.key == key }
    }


}