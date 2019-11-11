package com.example.helloandroid.verzicht


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.ListFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.helloandroid.R
import com.example.helloandroid.VerzichtUebersichtViewModel
import com.example.helloandroid.VerzichtViewModelFactory
import com.example.helloandroid.persistence.Verzicht
import com.example.helloandroid.presentation.VerzichtArrayAdapter
import java.time.LocalDateTime
import java.util.function.Consumer

class VerzichtListFragment : ListFragment() {

    private lateinit var viewModel: VerzichtUebersichtViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_verzicht_list, container, false)

        viewModel = ViewModelProviders.of(this, VerzichtViewModelFactory(activity!!.application)).get(VerzichtUebersichtViewModel::class.java)
        viewModel.initializeByLoadingAllVerzichte()

        val verzichteObserver = Observer<List<Verzicht>> {
            listAdapter = VerzichtArrayAdapter(activity!!.application, it, Consumer { t -> viewModel.deleteVerzicht(t) })
        }
        viewModel.verzichteLiveData.observe(this, verzichteObserver)


        return view
    }


}
