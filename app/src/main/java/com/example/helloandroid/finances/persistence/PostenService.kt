package com.example.helloandroid.finances.persistence

class PostenService constructor(postenDao: PostenDao) {

    private val postenDao = postenDao

    fun savePosten(posten:PostenEntity) {
        Thread(Runnable { postenDao.insertPosten(posten) }).start()
    }

}