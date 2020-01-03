package com.example.helloandroid.finances.view

import androidx.lifecycle.ViewModel
import com.example.helloandroid.finances.PostenStub

class SharedPostenViewModel : ViewModel() {

    lateinit var currentPostenStub: PostenStub

    fun reset() {
        currentPostenStub = PostenStub()
    }


}