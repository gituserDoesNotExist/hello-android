package com.example.helloandroid.timerecording.view

import androidx.databinding.ObservableBoolean
import com.example.helloandroid.timerecording.Arbeitszeit
import com.example.helloandroid.timerecording.EventInfo
import com.example.helloandroid.timerecording.ZeitArbeitsverhaeltnis
import com.example.helloandroid.timerecording.ZeitArbeitsverhaeltnisForAnzeige
import com.example.helloandroid.timerecording.config.Maschine
import com.example.helloandroid.timerecording.config.Person
import com.example.helloandroid.timerecording.config.Taetigkeit
import com.example.helloandroid.timerecording.repository.ZeiterfassungRepository
import io.reactivex.Single
import org.threeten.bp.LocalDate

class UpsertZeitArbeitsverhaeltnisViewModel(zeiterfassungRepository: ZeiterfassungRepository) :
    UpsertArbeitsverhaeltnisViewModel(zeiterfassungRepository) {

    var taetigkeitMissing = ObservableBoolean()

    lateinit var zeitArbeitsverhaeltnis: ZeitArbeitsverhaeltnis
    var arbeitsverhaeltnisForAnzeige = ZeitArbeitsverhaeltnisForAnzeige()


    fun initEventInfoAndArbeitsverhaeltnis(eventInfo: EventInfo, arbeitsverhaeltnis: ZeitArbeitsverhaeltnis) {
        super.initEventInfo(eventInfo)
        this.zeitArbeitsverhaeltnis = arbeitsverhaeltnis.copy()
        arbeitsverhaeltnisForAnzeige.copyValuesFromArbeitsverhaeltnis(this.zeitArbeitsverhaeltnis)
    }


    override fun updateArbeitsverhaeltnis(): Single<String> {
        takeRelevantValuesFromArbeitseinsatzForAnzeige()
        return zeiterfassungRepository.updateZeitArbeitsverhaeltnis(zeitArbeitsverhaeltnis, eventInfo)//
    }

    override fun addArbeitsverhaeltnis(): Single<Long> {
        takeRelevantValuesFromArbeitseinsatzForAnzeige()
        return zeiterfassungRepository.addZeitArbeitsverhaeltnisToRemoteCalendar(zeitArbeitsverhaeltnis)//
    }


    fun setLeistungsnehmer(value: Person) {
        zeitArbeitsverhaeltnis.leistungsnehmer = value
        arbeitsverhaeltnisForAnzeige.leistungsnehmer.set(value.name)
    }


    fun setLeistungserbringer(value: Person) {
        zeitArbeitsverhaeltnis.leistungserbringer = value
        arbeitsverhaeltnisForAnzeige.leistungserbringer.set(value.name)
    }


    fun setTaetigkeit(value: Taetigkeit) {
        zeitArbeitsverhaeltnis.taetigkeit = value
        arbeitsverhaeltnisForAnzeige.taetigkeit.set(value.bezeichnung)
    }


    fun setFahrzeug(value: Maschine) {
        zeitArbeitsverhaeltnis.fahrzeug = value
        arbeitsverhaeltnisForAnzeige.fahrzeug.set(value.bezeichnung)
    }


    fun setAnbaugaeraet(value: Maschine) {
        zeitArbeitsverhaeltnis.anbaugeraet = value
        arbeitsverhaeltnisForAnzeige.anbaugeraet.set(value.bezeichnung)
    }

    fun setDatum(value: LocalDate) {
        zeitArbeitsverhaeltnis.datum = value
        arbeitsverhaeltnisForAnzeige.datum.set(value)
    }

    private fun takeRelevantValuesFromArbeitseinsatzForAnzeige() {
        zeitArbeitsverhaeltnis.title = arbeitsverhaeltnisForAnzeige.title.get()
        zeitArbeitsverhaeltnis.kommentar = arbeitsverhaeltnisForAnzeige.kommentar.get()
        arbeitsverhaeltnisForAnzeige.arbeitszeit.get().let {
            zeitArbeitsverhaeltnis.arbeitszeit = Arbeitszeit(it)
        }
    }

    fun deleteArbeitsverhaeltnis(): Single<String> {
        return zeiterfassungRepository.deleteArbeitsverhaeltnis(eventInfo)
    }

    fun isValid(): Boolean {
        val taetigkeitSet = arbeitsverhaeltnisForAnzeige.taetigkeit.get().isNotBlank()
        taetigkeitMissing.set(!taetigkeitSet)
        return taetigkeitSet
    }

}