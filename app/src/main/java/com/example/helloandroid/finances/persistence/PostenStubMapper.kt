package com.example.helloandroid.finances.persistence

import com.example.helloandroid.finances.Ausgabe
import com.example.helloandroid.finances.PostenStub
import java.util.stream.Collectors

class PostenStubMapper {

    fun asPostenStub(stubEntity: PostenStubEntity): PostenStub {
        return PostenStub(stubEntity.id, stubEntity.name, stubEntity.gesamtausgaben)
    }

}