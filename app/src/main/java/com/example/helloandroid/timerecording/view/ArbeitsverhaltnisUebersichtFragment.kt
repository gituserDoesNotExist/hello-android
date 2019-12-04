package com.example.helloandroid.timerecording.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helloandroid.DialogOpener
import com.example.helloandroid.R
import com.example.helloandroid.databinding.FragmentArbeitsverhaeltnisUebersichtBinding
import com.example.helloandroid.timerecording.Arbeitsverhaeltnis

class ArbeitsverhaltnisUebersichtFragment : Fragment() {

    private lateinit var arbeitsverhaeltnisUebersichtViewModel: ArbeitsverhaeltnisUebersichtViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentArbeitsverhaeltnisUebersichtBinding.inflate(inflater, container, false)
        val rootView = binding.root
        binding.arbeitsverhaltnisUebersichtFragment = this

        activity?.let { fragmentActivity ->
            initializeViewModel(fragmentActivity)
            arbeitsverhaeltnisUebersichtViewModel.arbeitsverhaeltnisse.observe(this, Observer {
                connectRecyclerViewAdapterWithView(rootView, fragmentActivity, it)
            })
        }
        return rootView
    }

    private fun connectRecyclerViewAdapterWithView(rootView: View, fragmentActivity: FragmentActivity,
                                                   arbeitsverhaeltnisse: List<Arbeitsverhaeltnis>) {
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recycler_view_arbeitsverhaeltnisse)
        recyclerView.adapter = ArbeitsverhaeltnisRecyclerViewAdapter(arbeitsverhaeltnisse)
        recyclerView.layoutManager = LinearLayoutManager(fragmentActivity)
    }

    private fun initializeViewModel(fragmentActivity: FragmentActivity) {
        arbeitsverhaeltnisUebersichtViewModel =
            ViewModelProviders.of(this, ArbeitsverhaeltnisUebersichtViewModelFactory(fragmentActivity.application))
                .get(ArbeitsverhaeltnisUebersichtViewModel::class.java)
    }

    fun addArbeitsverhaeltnis() {
        activity?.let {
            DialogOpener.openDialog(it, AddArbeitsverhaeltnisDialog(), "dialog_add_arbeitsverhaeltnis")
        }
    }


}
