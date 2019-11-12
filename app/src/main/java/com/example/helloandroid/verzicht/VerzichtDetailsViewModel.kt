package com.example.helloandroid.verzicht

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.persistence.Verzicht
import com.example.helloandroid.persistence.VerzichtRepository
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class VerzichtDetailsViewModel(verzichtRepository: VerzichtRepository) : ViewModel() {

    private val verzichtRepository = verzichtRepository
    lateinit var verzichtLiveData: LiveData<Verzicht>


    fun loadCurrentVerzichtAndInitializeLiveData(currentVerzichtId: Long) {
        verzichtLiveData = verzichtRepository.findById(currentVerzichtId)
    }

    fun wereDaysIncreasedToday(verzicht: Verzicht): Boolean {
        return verzicht.timestampDayAdded.map { ldt -> ldt.toLocalDate().isEqual(LocalDate.now()) }.orElse(false)
    }

    fun increaseVerzichtDurationByOneDay() {
        verzichtLiveData.value?.let {
            if (!wereDaysIncreasedToday(it)) {
                increaseAndUpdate(it)
            }
        }
    }

    private fun increaseAndUpdate(verzicht: Verzicht) {
        verzicht.days = verzicht.days.plus(1)
        verzicht.timestampDayAdded = Optional.ofNullable(LocalDateTime.now())
        verzichtRepository.updateVerzicht(verzicht)
    }


}