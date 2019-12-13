package com.example.helloandroid.timerecording.view

import com.example.helloandroid.timerecording.Arbeitsverhaeltnis
import com.example.helloandroid.timerecording.Arbeitszeit
import com.example.helloandroid.timerecording.Person
import com.example.helloandroid.timerecording.TeamupEvent

class TeamupEventToDTOMapper {

    fun fromDtoToTeamupEvent(dto: ArbeitsverhaeltnisDTO): TeamupEvent {
        return TeamupEvent().apply { this.arbeitsverhaeltnis = fromDtoToArbeitsverhaeltnis(dto)}
    }

    private fun fromDtoToArbeitsverhaeltnis(dto: ArbeitsverhaeltnisDTO): Arbeitsverhaeltnis {
        return Arbeitsverhaeltnis().apply {
            this.leistungsnehmer = Person(dto.leistungsnehmer)
            this.leistungserbringer = Person(dto.leistungserbringer)
            this.kommentar = dto.kommentar
            this.datum = dto.datumZeiterfassung
            this.kategorie = dto.kategorie
            this.arbeitszeit = Arbeitszeit(dto.dauer)
        }
    }

    fun fromTeamupEventToArbeitsverhaeltnisDetailsDto(event: TeamupEvent): ArbeitsverhaeltnisDetailsDTO {
        return ArbeitsverhaeltnisDetailsDTO().apply {
            this.remoteId = event.remoteCalenderId
            this.version = event.version
            this.erstelltAm = event.erstelltAm.toLocalDateTime()
            this.erstelltVon = event.erstelltVon
            this.arbeitsverhaeltnisDto.datumZeiterfassung = event.arbeitsverhaeltnis.datum
            this.arbeitsverhaeltnisDto.dauer = event.arbeitsverhaeltnis.arbeitszeit.dauer
            this.arbeitsverhaeltnisDto.kategorie = event.arbeitsverhaeltnis.kategorie
            this.arbeitsverhaeltnisDto.kommentar = event.arbeitsverhaeltnis.kommentar
            this.arbeitsverhaeltnisDto.leistungserbringer = event.arbeitsverhaeltnis.leistungserbringer.name
            this.arbeitsverhaeltnisDto.leistungsnehmer = event.arbeitsverhaeltnis.leistungsnehmer.name
        }
    }

    fun fromArbeitsverhaeltnisDetailsDtoToTeamupEvent(dto: ArbeitsverhaeltnisDetailsDTO): TeamupEvent {
        return TeamupEvent().apply {
            this.arbeitsverhaeltnis = fromDtoToArbeitsverhaeltnis(dto.arbeitsverhaeltnisDto)
            this.remoteCalenderId = dto.remoteId
            this.version = dto.version
        }
    }

}
