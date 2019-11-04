package com.example.helloandroid.persistence

import java.lang.RuntimeException

class TypeNotFoundException constructor(message: String) : RuntimeException(message) {

    val exceptionMessage: String = message


}