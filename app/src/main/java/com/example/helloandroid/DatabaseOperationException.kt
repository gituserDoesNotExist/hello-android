package com.example.helloandroid

class DatabaseOperationException : RuntimeException {

    constructor(msg: String) : super(msg)

    constructor(msg: String, cause: Throwable) : super(msg, cause)

}