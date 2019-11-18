package com.example.helloandroid.finances.persistence

import com.example.helloandroid.finances.Posten

class PostenService constructor(postenDao: PostenDao) {

    private val postenDao = postenDao
    private val postenEntityToPostenMapper: PostenEntityToPostenMapper = PostenEntityToPostenMapper()

    fun savePosten(posten: Posten) {
        val postenEntity = postenEntityToPostenMapper.asPostenEntity(posten)
        Thread(Runnable { postenDao.insertPosten(postenEntity) }).start()
    }

}