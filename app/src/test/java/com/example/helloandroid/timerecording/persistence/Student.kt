package com.example.helloandroid.timerecording.persistence

internal class Student {
    var name: String? = null
    var age: Int = 0

    override fun toString(): String {
        return "Student [ name: $name, age: $age ]"
    }
}