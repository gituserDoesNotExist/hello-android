package com.example.helloandroid.finances.view

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.helloandroid.HelloDatePickerDialog
import com.example.helloandroid.databinding.DialogAddAusgabeBinding
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime

class AddAusgabeDialog : DialogFragment() {

    private lateinit var postenDetailsViewModel: PostenDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.let {
            postenDetailsViewModel = ViewModelProviders.of(it, FinancesViewModelFactory(it.application))
                .get(PostenDetailsViewModel::class.java)
        }
        val binding = DialogAddAusgabeBinding.inflate(inflater, container, false)

        binding.ausgabeDialog = this
        binding.ausgabeDto = postenDetailsViewModel.ausgabeDto
        return binding.root
    }

    fun saveAusgabeAndCloseDialog() {
        postenDetailsViewModel.saveAusgabeForPosten()
        closeDialog()
    }

    fun closeDialog() {
        this.dismiss()
    }

    fun openDatePickerDialog() {
        val onDateSet: (LocalDate) -> Unit = { date -> postenDetailsViewModel.ausgabeDto.datum = date }
        activity?.let { HelloDatePickerDialog(it, onDateSet, LocalDate.now()).show() }
    }

    fun openTimePickerDialog() {
        val currentTime = LocalTime.now()
        val onTimeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            postenDetailsViewModel.ausgabeDto.uhrzeit = LocalTime.of(hourOfDay, minute)
        }
        activity?.let {
            TimePickerDialog(it, onTimeSetListener, currentTime.hour, currentTime.minute, true).show()
        }
    }


}