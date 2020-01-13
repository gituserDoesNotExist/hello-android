package com.example.helloandroid.timerecording.view

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.timerecording.config.Person
import com.example.helloandroid.timerecording.config.Taetigkeit
import com.example.helloandroid.timerecording.repository.ZeiterfassungRepository
import org.threeten.bp.LocalDate
import ru.gildor.databinding.observables.ObservableString

class FiltersViewModel(zeiterfassungRepository: ZeiterfassungRepository) : ViewModel() {

    var calendarConfig: LiveData<CalendarConfiguration> = zeiterfassungRepository.getConfiguration()

    val suchkriterien = Suchkriterien()

    val observableStartDate = ObservableField<LocalDate>().apply { set(getStartDate()) }
    val observableEndDate = ObservableField<LocalDate>().apply { set(getEndDate()) }
    val observableLeistungsnehmer = ObservableString().apply { set(suchkriterien.joinLeistungsnehmer()) }
    val observableLeistungserbringer = ObservableString().apply { set(suchkriterien.joinLeistungserbringer()) }
    val observableTaetigkeit = ObservableString().apply { set(suchkriterien.joinTaetigkeiten()) }

    private val selectedLeistungsnehmer: MutableList<Person> = ArrayList()
    private val selectedLeistungserbringer: MutableList<Person> = ArrayList()
    private val selectedTaetigkeiten: MutableList<Taetigkeit> = ArrayList()

    fun updateStartDate(date: LocalDate) {
        suchkriterien.updateFilterForStartDate(date)
        observableStartDate.set(date)
    }

    fun getStartDate(): LocalDate {
        return suchkriterien.startDate.getSuchkriterium()
    }


    fun updateEndDate(date: LocalDate) {
        suchkriterien.updateFilterForEndDate(date)
        observableEndDate.set(date)
    }

    fun getEndDate(): LocalDate {
        return suchkriterien.endDate.getSuchkriterium()
    }


    fun removeFilter(filterKey: FilterKeys) {
        suchkriterien.removeFilter(filterKey)
    }

    fun addSelectedItemLeistungsnehmer(item: Person) {
        selectedLeistungsnehmer.add(item)
    }


    fun removeSelectedItemLeistungsnehmer(item: Person) {
        selectedLeistungsnehmer.remove(item)
    }

    fun addSelectedItemLeistungserbringer(item: Person) {
        selectedLeistungserbringer.add(item)
    }

    fun removeSelectedItemLeistungserbringer(item: Person) {
        selectedLeistungserbringer.remove(item)
    }

    fun addSelectedItemTaetigkeit(item: Taetigkeit) {
        selectedTaetigkeiten.add(item)
    }


    fun removeSelectedItemTaetigkeit(item: Taetigkeit) {
        selectedTaetigkeiten.remove(item)
    }

    fun saveSelectedLeistungsnehmerItems() {
        suchkriterien.leistungsnehmerToFilter.clear()
        selectedLeistungsnehmer.forEach { suchkriterien.addFilterForLeistungsnehmer(it) }
        selectedLeistungsnehmer.clear()
        observableLeistungsnehmer.set(suchkriterien.joinLeistungsnehmer())
    }

    fun saveSelectedLeistungserbringerItems() {
        suchkriterien.leistungserbringerToFilter.clear()
        selectedLeistungserbringer.forEach { suchkriterien.addFilterForLeistungserbringer(it) }
        selectedLeistungserbringer.clear()
        observableLeistungserbringer.set(suchkriterien.joinLeistungserbringer())
    }

    fun saveSelectedTaetigkeitItems() {
        suchkriterien.taetigkeitenToFilter.clear()
        selectedTaetigkeiten.forEach { suchkriterien.addFilterForTaetigkeit(it) }
        selectedTaetigkeiten.clear()
        observableTaetigkeit.set(suchkriterien.joinTaetigkeiten())
    }


}