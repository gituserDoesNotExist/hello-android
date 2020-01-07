package com.example.helloandroid.timerecording

import com.google.gson.Gson
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class RemoteCalendarMetadataSerializationTest {

    @Test
    fun fromJsonToKalenderMetadata() {
        val jsonString = "{\"categories\":[\"a\",\"b\"],\"participants\":[{\"name\":\"max\",\"rolle\":\"AKTUELLER_APP_USER\"}]}"

        val metadata = Gson().fromJson(jsonString, RemoteCalendarMetadata::class.java)

        assertThat(metadata.kategorien).containsExactlyInAnyOrder("a","b")
        assertThat(metadata.teilnehmer[0].name).isEqualTo("max")
    }
}