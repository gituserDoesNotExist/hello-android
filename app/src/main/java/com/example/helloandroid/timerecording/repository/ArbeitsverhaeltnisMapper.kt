package com.example.helloandroid.timerecording.repository

import com.example.helloandroid.HelloJson
import com.example.helloandroid.timerecording.Arbeitsverhaeltnis
import com.example.helloandroid.timerecording.web.remotemodel.Event

class ArbeitsverhaeltnisMapper {

    fun fromEventToArbeitsverhaeltnis(event: Event): Arbeitsverhaeltnis {
        return HelloJson.jsonToObject(removeHtmlParagraphs(event.notes),Arbeitsverhaeltnis::class.java)
    }

    private fun removeHtmlParagraphs(notes: String): String {
        return notes.replace("<p>","").replace("</p>","")
    }


}