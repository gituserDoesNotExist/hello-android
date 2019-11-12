package com.example.helloandroid.verzicht


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.ListFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.helloandroid.*

import com.example.helloandroid.persistence.Verzicht
import com.example.helloandroid.presentation.VerzichtArrayAdapter
import java.util.function.Consumer
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager


class VerzichtListFragment : ListFragment() {

    private lateinit var viewModel: VerzichtUebersichtViewModel
    private lateinit var sharedVerzichtViewModel: SharedVerzichtViewModel
    private lateinit var userInputNewVerzicht: EditText
    private lateinit var newVerzichtBtn: ImageButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_verzicht_list, container, false)

        userInputNewVerzicht = rootView.findViewById(R.id.input_new_verzicht_name)
        newVerzichtBtn = rootView.findViewById(R.id.new_verzicht_button)
        newVerzichtBtn.setOnClickListener {
            saveVerzicht()
            resetInputField()
        }

        activity?.let {
            viewModel =
                ViewModelProviders.of(it, VerzichtViewModelFactory(it.application))
                    .get(VerzichtUebersichtViewModel::class.java)
            sharedVerzichtViewModel = ViewModelProviders.of(it).get(SharedVerzichtViewModel::class.java)
            viewModel.initializeByLoadingAllVerzichte()
            viewModel.verzichteLiveData.observe(this, aVerzichtListObserver())

        }

        return rootView
    }

    private fun saveVerzicht() {
        val verzichtName = userInputNewVerzicht.text.toString()
        viewModel.saveVerzicht(Verzicht(verzichtName, 0))
    }

    private fun resetInputField() {
        userInputNewVerzicht.text.clear()
        activity?.let {
            (it.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                userInputNewVerzicht.windowToken,
                0
            )
        }
    }

    private fun aVerzichtListObserver(): Observer<List<Verzicht>> {
        return Observer { verzichte ->
            listAdapter =
                VerzichtArrayAdapter(activity!!, //
                    verzichte,//
                    Consumer { verzicht -> openEditVerzichtFragment(verzicht) },//
                    Consumer { t -> viewModel.deleteVerzicht(t) })
        }
    }

    private fun openEditVerzichtFragment(currentVerzicht: Verzicht) {
        sharedVerzichtViewModel.currentVerzicht = currentVerzicht
        activity?.let {
            it.supportFragmentManager.beginTransaction()//
                .replace(R.id.fragment_container_verzicht, EditVerzichtFragment())//
                .addToBackStack("egal")
                .commit()
        }
    }

}
