package com.example.helloandroid.timerecording.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.helloandroid.DialogOpener
import com.example.helloandroid.databinding.FragmentZeiterfassungBinding

class ZeiterfassungFragment : Fragment() {

    private lateinit var zeiterfassungViewModel: ZeiterfassungViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentZeiterfassungBinding.inflate(inflater, container, false)
        binding.zeiterfassungFragment = this

        return binding.root
    }

    fun newZeiterfassungsEntry() {
        activity?.let {
            DialogOpener.openDialog(it, NeueZeiterfassungDialog(), "dialog_neue_zeiterfassung")
        }
    }


}
