package com.example.helloandroid.timerecording.view

import androidx.lifecycle.ViewModel
import com.example.helloandroid.timerecording.ZeiterfassungMapper

class ZeiterfassungViewModel(private val zeiterfassungRepository: ZeiterfassungRepository): ViewModel() {

    private val zeiterfassungMapper = ZeiterfassungMapper()


    fun addEintrag(zeiterfassungDTO: ZeiterfassungDTO) {
        val zeiterfassung = zeiterfassungMapper.asZeiterfassung(zeiterfassungDTO)
        zeiterfassungRepository.addEintragToTeamUpNachbarschaftshilfe(zeiterfassung)
    }


}