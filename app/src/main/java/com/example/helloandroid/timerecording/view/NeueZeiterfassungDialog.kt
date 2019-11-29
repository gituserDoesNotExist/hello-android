package com.example.helloandroid.timerecording.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.example.helloandroid.databinding.DialogNeueZeiterfassungBinding
import org.threeten.bp.LocalDate

class NeueZeiterfassungDialog : DialogFragment() {

    private lateinit var zeiterfassungViewModel: ZeiterfassungViewModel
    val zeiterfassungDTO = ZeiterfassungDTO()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.let {
            initializeViewModel(it)
        }
        val binding = DialogNeueZeiterfassungBinding.inflate(inflater, container, false)
        binding.neueZeiterfassungDialog = this

        return binding.root
    }

    private fun initializeViewModel(it: FragmentActivity) {
        zeiterfassungViewModel = ViewModelProviders.of(it, ZeiterfassungViewModelFactory(it)).get(ZeiterfassungViewModel::class.java)
    }

    fun saveEintragAndCloseDialog() {
        zeiterfassungViewModel.addEintrag(zeiterfassungDTO)
        closeDialog()
    }

    fun closeDialog() {
        this.dismiss()
    }

    fun openDatePickerDialog() {
        val crrntDate = LocalDate.now()
        val onDateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            zeiterfassungDTO.datumZeiterfassung = LocalDate.of(year,month, dayOfMonth)
        }
        activity?.let {
            DatePickerDialog(it, onDateSetListener, crrntDate.year, crrntDate.monthValue, crrntDate.dayOfMonth).show()
        }
    }

}