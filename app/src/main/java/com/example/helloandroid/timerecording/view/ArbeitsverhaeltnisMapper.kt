package com.example.helloandroid.timerecording.view

import com.example.helloandroid.timerecording.Arbeitsverhaeltnis
import com.example.helloandroid.timerecording.Arbeitszeit
import com.example.helloandroid.timerecording.Person

class ArbeitsverhaeltnisMapper {

    fun fromDto(arbeitsverhaeltnisErstellenDTO: ArbeitsverhaeltnisErstellenDTO): Arbeitsverhaeltnis {
        return Arbeitsverhaeltnis().apply {
            this.leistungsnehmer = Person(arbeitsverhaeltnisErstellenDTO.leistungsnehmer)
            this.leistungserbringer = Person(arbeitsverhaeltnisErstellenDTO.leistungserbringer)
            this.kommentar = arbeitsverhaeltnisErstellenDTO.kommentar
            this.datum = arbeitsverhaeltnisErstellenDTO.datumZeiterfassung
            this.kategorie = arbeitsverhaeltnisErstellenDTO.kategorie
            this.dauer = Arbeitszeit(arbeitsverhaeltnisErstellenDTO.dauer)
        }
    }

}
