package com.example.helloandroid.timerecording.view

import com.example.helloandroid.timerecording.Arbeitsverhaeltnis
import com.example.helloandroid.timerecording.Arbeitszeit
import com.example.helloandroid.timerecording.Person
import com.example.helloandroid.timerecording.TeamupEvent

class ArbeitsverhaeltnisToDTOMapper {

    fun fromErstellenDtoToArbeitsverhaeltnis(erstellenDTO: ArbeitsverhaeltnisErstellenDTO): Arbeitsverhaeltnis {
        return Arbeitsverhaeltnis().apply {
            this.leistungsnehmer = Person(erstellenDTO.leistungsnehmer)
            this.leistungserbringer = Person(erstellenDTO.leistungserbringer)
            this.kommentar = erstellenDTO.kommentar
            this.datum = erstellenDTO.datumZeiterfassung
            this.kategorie = erstellenDTO.kategorie
            this.arbeitszeit = Arbeitszeit(erstellenDTO.dauer)
        }
    }

    fun fromTeamupEventToArbeitsverhaeltnisDetailsDto(arbeitsverhaeltnis: TeamupEvent): ArbeitsverhaeltnisDetailsDTO {
        return ArbeitsverhaeltnisDetailsDTO().apply {
            this.erstelltAm = arbeitsverhaeltnis.erstelltAm.toLocalDateTime()
            this.erstelltVon = arbeitsverhaeltnis.erstelltVon
            this.datum = arbeitsverhaeltnis.arbeitsverhaeltnis.datum
            this.dauer = arbeitsverhaeltnis.arbeitsverhaeltnis.arbeitszeit.dauer
            this.kategorie = arbeitsverhaeltnis.arbeitsverhaeltnis.kategorie
            this.kommentar = arbeitsverhaeltnis.arbeitsverhaeltnis.kommentar
            this.leistungserbringer = arbeitsverhaeltnis.arbeitsverhaeltnis.leistungserbringer.name
            this.leistungsnehmer = arbeitsverhaeltnis.arbeitsverhaeltnis.leistungsnehmer.name
        }
    }


}
