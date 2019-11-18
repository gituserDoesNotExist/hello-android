package com.example.helloandroid

import kotlin.math.min

data class Uhrzeit(val hour: String, val minute: String) {

    override fun toString(): String {
        return "$hour:$minute"
    }
}