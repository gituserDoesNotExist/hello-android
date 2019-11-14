package com.example.helloandroid.finances

import androidx.lifecycle.ViewModel
import com.example.helloandroid.finances.persistence.Posten

class SharedPostenViewModel : ViewModel() {

    lateinit var currentPosten: Posten

}