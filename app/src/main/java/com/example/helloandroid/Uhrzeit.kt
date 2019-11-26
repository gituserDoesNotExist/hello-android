package com.example.helloandroid

data class Uhrzeit(val hour: String, val minute: String) {

    override fun toString(): String {
        return "$hour:$minute"
    }
}