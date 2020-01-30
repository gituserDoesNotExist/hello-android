package com.example.helloandroid.timerecording.view

import androidx.lifecycle.ViewModel
import com.example.helloandroid.timerecording.repository.ZeiterfassungRepository
import io.reactivex.Single

class StartZeiterfassungViewModel(private val zeiterfassungRepository: ZeiterfassungRepository) : ViewModel() {

    fun existsConfiguration(): Single<Boolean> {
        return zeiterfassungRepository.existsConfiguration()
    }


}