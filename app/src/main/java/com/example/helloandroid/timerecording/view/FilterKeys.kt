package com.example.helloandroid.timerecording.view

enum class FilterKeys(name: String) {

    START_DATE("Start"),//
    END_DATE("Ende"),//
    LEISTUNGSNEHMER("Leistungsnehmer"),//
    LEISTUNGSERBRINGER("Leistungserbringer"),//
    TAETIGKEIT("Taetigkeit");

    val filterName: String = name

}