package com.example.helloandroid.finances.view

import androidx.lifecycle.ViewModel
import com.example.helloandroid.finances.Posten
import com.example.helloandroid.finances.persistence.PostenEntity

class SharedPostenViewModel : ViewModel() {

    lateinit var currentPosten: Posten


}