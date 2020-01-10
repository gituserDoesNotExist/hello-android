package com.example.helloandroid.timerecording.view

import com.example.helloandroid.timerecording.config.Maschine
import com.example.helloandroid.timerecording.config.Person
import com.example.helloandroid.timerecording.config.Produkt
import com.example.helloandroid.timerecording.config.Taetigkeit

data class CalendarConfiguration(var appUser: String,
                                 var teatigkeiten: List<Taetigkeit>,
                                 var teilnehmer: List<Person>,
                                 var fahrzeuge: List<Maschine>,
                                 var anbaugeraete: List<Maschine>,
                                 val produkte: List<Produkt>)