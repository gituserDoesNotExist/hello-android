package com.example.helloandroid.finances.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.example.helloandroid.R
import java.time.LocalDate
import java.time.LocalDateTime

class AddAusgabeDialog : DialogFragment() {

    private lateinit var postenDetailsViewModel: PostenDetailsViewModel

    private lateinit var editTextWert: EditText
    private lateinit var editTextBeschreibung: EditText

    private lateinit var btnSelectDate: ImageButton
    private lateinit var editTextDate: EditText

    private lateinit var btnSelectTime: ImageButton
    private lateinit var editTextTime: EditText

    private lateinit var btnSaveAusgabe: ImageButton
    private lateinit var btnCancel: ImageButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.let {
            postenDetailsViewModel = ViewModelProviders.of(it,
                FinancesViewModelFactory(it.application)
            )
                .get(PostenDetailsViewModel::class.java)
        }


        val rootView = inflater.inflate(R.layout.add_ausgabe_dialog, container, false)

        editTextDate = rootView.findViewById(R.id.input_add_ausgabe_datum)
        editTextDate.setText(buildCurrentDateForAnzeige(), TextView.BufferType.EDITABLE)
        btnSelectDate = rootView.findViewById(R.id.btn_add_ausgabe_dialog_select_date)
        btnSelectDate.setOnClickListener { openDatePickerDialog() }

        editTextTime = rootView.findViewById(R.id.input_add_ausgabe_time)
        editTextTime.setText(buildCurrentTimeForAnzeige(), TextView.BufferType.EDITABLE)
        btnSelectTime = rootView.findViewById(R.id.btn_add_ausgabe_dialog_select_time)
        btnSelectTime.setOnClickListener { openTimePickerDialog() }

        editTextWert = rootView.findViewById(R.id.input_add_ausgabe_wert)
        editTextBeschreibung = rootView.findViewById(R.id.input_add_ausgabe_description)

        btnSaveAusgabe = rootView.findViewById(R.id.btn_ausgabe_hinzufuegen)
        btnSaveAusgabe.setOnClickListener {
            saveAusgabe()
            closeDialog()
        }
        btnCancel = rootView.findViewById(R.id.btn_ausgabe_hinzufuegen_abbrechen)
        btnCancel.setOnClickListener { closeDialog() }


        return rootView
    }

    private fun saveAusgabe() {
        val datum = editTextDate.text.toString()
        val uhrzeit = editTextTime.text.toString()
        val wert = editTextWert.text.toString()
        val beschreibung = editTextBeschreibung.text.toString()
        postenDetailsViewModel.saveAusgabeForPosten(
            AusgabeDTO(
                datum,
                uhrzeit,
                wert,
                beschreibung
            )
        )
    }

    private fun closeDialog() {
        this.dismiss()
    }

    private fun openDatePickerDialog() {
        val crrntDate = LocalDate.now()
        val onDateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            editTextDate.setText(buildDateForAnzeige(year, month, dayOfMonth), TextView.BufferType.EDITABLE)
        }
        activity?.let {
            DatePickerDialog(it, onDateSetListener, crrntDate.year, crrntDate.month.value, crrntDate.dayOfMonth).show()
        }
    }

    private fun openTimePickerDialog() {
        val currentTime = LocalDateTime.now()
        val onTimeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            val timeToDisplay = "$hourOfDay:$minute"
            editTextTime.setText(timeToDisplay, TextView.BufferType.EDITABLE)
        }
        activity?.let {
            TimePickerDialog(it, onTimeSetListener, currentTime.hour, currentTime.minute, true).show()
        }
    }

    private fun buildCurrentDateForAnzeige(): String {
        val currentDate = LocalDate.now()
        return buildDateForAnzeige(currentDate.year, currentDate.monthValue, currentDate.dayOfMonth)
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