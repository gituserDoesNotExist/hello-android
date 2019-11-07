package com.example.helloandroid.persistence

import java.lang.RuntimeException

class ConstraintViolationException constructor(message: String) : RuntimeException(message) {

    val exceptionMessage: String = message


}