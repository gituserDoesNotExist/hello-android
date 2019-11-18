package com.example.helloandroid.finances.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.helloandroid.databinding.AddAusgabeDialogBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class AddAusgabeDialog : DialogFragment() {

    private lateinit var postenDetailsViewModel: PostenDetailsViewModel
    val ausgabeDTO: AusgabeDTO = AusgabeDTO()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.let {
            postenDetailsViewModel = ViewModelProviders.of(it, FinancesViewModelFactory(it.application))
                .get(PostenDetailsViewModel::class.java)
        }
        val binding: AddAusgabeDialogBinding = AddAusgabeDialogBinding.inflate(inflater, container, false)
        binding.addAusgabeDialog = this

        return binding.root
    }

    fun saveAusgabeAndCloseDialog() {
        postenDetailsViewModel.saveAusgabeForPosten(ausgabeDTO)
        closeDialog()
    }

    fun closeDialog() {
        this.dismiss()
    }

    fun openDatePickerDialog() {
        val crrntDate = LocalDate.now()
        val onDateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            ausgabeDTO.datum = LocalDate.of(year,month,dayOfMonth)
        }
        activity?.let {
            DatePickerDialog(it, onDateSetListener, crrntDate.year, crrntDate.month.value, crrntDate.dayOfMonth).show()
        }
    }

    fun openTimePickerDialog() {
        val currentTime = LocalDateTime.now()
        val onTimeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            ausgabeDTO.uhrzeit = LocalTime.of(hourOfDay, minute)
        }
        activity?.let {
            TimePickerDialog(it, onTimeSetListener, currentTime.hour, currentTime.minute, true).show()
        }
    }


    private fun buildDateForAnzeige(year: Int, month: Int, dayOfMonth: Int): String {
        return "$dayOfMonth.$month.$year"
    }

    private fun buildCurrentTimeForAnzeige(): String {
        val currentTime = LocalDateTime.now()
        return buildTimeForAnzeige(currentTime.hour, currentTime.minute)
    }

    private fun buildTimeForAnzeige(hour: Int, minute: Int): String {
        return "$hour:$minute"
    }

}