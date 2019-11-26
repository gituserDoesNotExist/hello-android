package com.example.helloandroid.verzicht.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.verzicht.persistence.Verzicht
import com.example.helloandroid.verzicht.persistence.VerzichtRepository
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import java.util.*

class VerzichtDetailsViewModel(private val verzichtRepository: VerzichtRepository) : ViewModel() {

    lateinit var verzichtLiveData: LiveData<Verzicht>


    fun initialize(currentVerzichtId: Long) {
        verzichtLiveData = verzichtRepository.findById(currentVerzichtId)
    }

    fun wereDaysIncreasedToday(verzicht: Verzicht): Boolean {
        return verzicht.timestampDayAdded//
            .map { ldt -> ldt.toLocalDate().isEqual(LocalDate.now()) }//
            .orElse(false)
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