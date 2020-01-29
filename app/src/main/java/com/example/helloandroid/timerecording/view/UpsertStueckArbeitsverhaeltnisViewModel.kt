package com.example.helloandroid.timerecording.view

import androidx.databinding.ObservableBoolean
import com.example.helloandroid.timerecording.EventInfo
import com.example.helloandroid.timerecording.StueckArbeitsverhaeltnis
import com.example.helloandroid.timerecording.StueckArbeitsverhaeltnisForAnzeige
import com.example.helloandroid.timerecording.config.Person
import com.example.helloandroid.timerecording.config.Produkt
import com.example.helloandroid.timerecording.repository.ZeiterfassungRepository
import io.reactivex.Single
import org.threeten.bp.LocalDate

class UpsertStueckArbeitsverhaeltnisViewModel(zeiterfassungRepository: ZeiterfassungRepository) :
    UpsertArbeitsverhaeltnisViewModel(zeiterfassungRepository) {

    var anzahlMissing = ObservableBoolean()
    var produktnameMissing = ObservableBoolean()

    lateinit var stueckArbeitsverhaeltnis: StueckArbeitsverhaeltnis
    var arbeitsverhaeltnisForAnzeige = StueckArbeitsverhaeltnisForAnzeige()

    fun initEventInfoAndArbeitsverhaeltnis(eventInfo: EventInfo, arbeitsverhaeltnis: StueckArbeitsverhaeltnis) {
        super.initEventInfo(eventInfo)
        this.stueckArbeitsverhaeltnis = arbeitsverhaeltnis.copy()
        arbeitsverhaeltnisForAnzeige.copyValuesFromArbeitsverhaeltnis(this.stueckArbeitsverhaeltnis)
    }


    override fun updateArbeitsverhaeltnis(): Single<String> {
        takeRelevantValuesFromArbeitseinsatzForAnzeige()
        return zeiterfassungRepository.updateStueckArbeitsverhaeltnis(stueckArbeitsverhaeltnis, eventInfo)//
    }

    override fun addArbeitsverhaeltnis(): Single<Long> {
        takeRelevantValuesFromArbeitseinsatzForAnzeige()
        return zeiterfassungRepository.addStueckArbeitsverhaeltnisToRemoteCalendar(stueckArbeitsverhaeltnis)
    }

    override fun validate(): Boolean {
        val stueckzahlSet = arbeitsverhaeltnisForAnzeige.stueckzahl.get() > 0
        val produktSet = arbeitsverhaeltnisForAnzeige.produktName.get().isNotBlank()
        anzahlMissing.set(!stueckzahlSet)
        produktnameMissing.set(!produktSet)
        return stueckzahlSet && produktSet
    }


    fun setLeistungsnehmer(value: Person) {
        stueckArbeitsverhaeltnis.leistungsnehmer = value
        arbeitsverhaeltnisForAnzeige.leistungsnehmer.set(value.name)
    }


    fun setLeistungserbringer(value: Person) {
        stueckArbeitsverhaeltnis.leistungserbringer = value
        arbeitsverhaeltnisForAnzeige.leistungserbringer.set(value.name)
    }

    fun setProdukt(value: Produkt) {
        stueckArbeitsverhaeltnis.produkt = value
        arbeitsverhaeltnisForAnzeige.produktName.set(value.name)
    }


    fun setDatum(value: LocalDate) {
        stueckArbeitsverhaeltnis.datum = value
        arbeitsverhaeltnisForAnzeige.datum.set(value)
    }

    private fun takeRelevantValuesFromArbeitseinsatzForAnzeige() {
        stueckArbeitsverhaeltnis.title = arbeitsverhaeltnisForAnzeige.title.get()
        stueckArbeitsverhaeltnis.kommentar = arbeitsverhaeltnisForAnzeige.kommentar.get()
        stueckArbeitsverhaeltnis.stueckzahl = arbeitsverhaeltnisForAnzeige.stueckzahl.get()
    }

    fun deleteArbeitsverhaeltnis(): Single<String> {
        return zeiterfassungRepository.deleteArbeitsverhaeltnis(eventInfo)
    }

}