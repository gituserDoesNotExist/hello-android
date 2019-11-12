package com.example.helloandroid.finances


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.helloandroid.R

class AusgabenUebersichtFragment : Fragment() {

    private lateinit var totalAmount: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val roowView = inflater.inflate(R.layout.fragment_ausgaben_uebersicht, container, false)

        totalAmount = roowView.findViewById(R.id.txt_finances_total_amount)

        return roowView
    }


}
