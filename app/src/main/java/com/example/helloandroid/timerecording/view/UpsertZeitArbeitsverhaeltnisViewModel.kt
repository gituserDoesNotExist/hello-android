package com.example.helloandroid.timerecording.view

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.timerecording.Arbeitszeit
import com.example.helloandroid.timerecording.EventInfo
import com.example.helloandroid.timerecording.ZeitArbeitsverhaeltnis
import com.example.helloandroid.timerecording.ZeitArbeitsverhaeltnisForAnzeige
import com.example.helloandroid.timerecording.config.*
import com.example.helloandroid.timerecording.repository.ZeiterfassungRepository
import io.reactivex.Single
import org.threeten.bp.LocalDate
import java.math.BigDecimal

class UpsertZeitArbeitsverhaeltnisViewModel(private val zeiterfassungRepository: ZeiterfassungRepository) : ViewModel() {

    val updateArbeitsverhaeltnis = ObservableBoolean().apply { this.set(false) }
    var editable: ObservableBoolean = ObservableBoolean(false)

    val config: LiveData<CalendarConfiguration> = zeiterfassungRepository.getConfiguration()

    lateinit var eventInfo: EventInfo
    lateinit var zeitArbeitsverhaeltnis: ZeitArbeitsverhaeltnis
    var arbeitseinsatzForAnzeige = ZeitArbeitsverhaeltnisForAnzeige()


    fun initEventInfoAndArbeitsverhaeltnis(eventInfo: EventInfo, arbeitsverhaeltnis: ZeitArbeitsverhaeltnis) {
        this.eventInfo = eventInfo.copy()
        this.zeitArbeitsverhaeltnis = arbeitsverhaeltnis.copy()
        arbeitseinsatzForAnzeige.copyValuesFromArbeitsverhaeltnis(this.zeitArbeitsverhaeltnis)
    }



    fun updateArbeitsverhaeltnis(): Single<String> {
        takeRelevantValuesFromArbeitseinsatzForAnzeige()
        return zeiterfassungRepository.updateZeitArbeitsverhaeltnis(zeitArbeitsverhaeltnis,eventInfo)//
    }

    fun addArbeitsverhaeltnis(): Single<Long> {
        takeRelevantValuesFromArbeitseinsatzForAnzeige()
        return zeiterfassungRepository.addZeitArbeitsverhaeltnisToRemoteCalendar(zeitArbeitsverhaeltnis)//
    }


    fun resetArbeitsverhaeltnis() {
        zeitArbeitsverhaeltnis.apply {
            this.datum = LocalDate.now()
            this.leistungserbringer = Person()
            this.leistungsnehmer = Person()
            this.fahrzeug = Fahrzeug()
            this.anbaugeraet = Anbaugeraet()
            this.kommentar = ""
            this.arbeitszeit = Arbeitszeit(BigDecimal.ZERO)
            this.taetigkeit = Taetigkeit()
        }
        arbeitseinsatzForAnzeige.copyValuesFromArbeitsverhaeltnis(zeitArbeitsverhaeltnis)
    }

    fun setLeistungsnehmer(value: Person) {
        zeitArbeitsverhaeltnis.leistungsnehmer = value
        arbeitseinsatzForAnzeige.leistungsnehmer.set(value.name)
    }


    fun setLeistungserbringer(value: Person) {
        zeitArbeitsverhaeltnis.leistungserbringer = value
        arbeitseinsatzForAnzeige.leistungserbringer.set(value.name)
    }


    fun setTaetigkeit(value: Taetigkeit) {
        zeitArbeitsverhaeltnis.taetigkeit = value
        arbeitseinsatzForAnzeige.taetigkeit.set(value.bezeichnung)
    }


    fun setFahrzeug(value: Maschine) {
        zeitArbeitsverhaeltnis.fahrzeug = value
        arbeitseinsatzForAnzeige.fahrzeug.set(value.bezeichnung)
    }


    fun setAnbaugaeraet(value: Maschine) {
        zeitArbeitsverhaeltnis.anbaugeraet = value
        arbeitseinsatzForAnzeige.anbaugeraet.set(value.bezeichnung)
    }

    fun setDatum(value: LocalDate) {
        zeitArbeitsverhaeltnis.datum = value
        arbeitseinsatzForAnzeige.datum.set(value)
    }

    private fun takeRelevantValuesFromArbeitseinsatzForAnzeige() {
        zeitArbeitsverhaeltnis.kommentar = arbeitseinsatzForAnzeige.kommentar.get()
        arbeitseinsatzForAnzeige.arbeitszeit.get().let {
            zeitArbeitsverhaeltnis.arbeitszeit = Arbeitszeit(it)
        }
    }

    fun deleteArbeitsverhaeltnis() {
        return zeiterfassungRepository.deleteArbeitsverhaeltnis(eventInfo)
    }

}