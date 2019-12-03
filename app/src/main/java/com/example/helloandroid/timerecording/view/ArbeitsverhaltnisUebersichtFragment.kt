package com.example.helloandroid.timerecording.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helloandroid.DialogOpener
import com.example.helloandroid.R
import com.example.helloandroid.databinding.FragmentArbeitsverhaeltnisUebersichtBinding
import com.example.helloandroid.timerecording.Arbeitsverhaeltnis
import io.reactivex.android.schedulers.AndroidSchedulers

class ArbeitsverhaltnisUebersichtFragment : Fragment() {

    private lateinit var arbeitsverhaeltnisUebersichtViewModel: ArbeitsverhaeltnisUebersichtViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentArbeitsverhaeltnisUebersichtBinding.inflate(inflater, container, false)
        val rootView = binding.root
        binding.arbeitsverhaltnisUebersichtFragment = this
        initializeViewModel()

        activity?.let {
            arbeitsverhaeltnisUebersichtViewModel.findAllArbeitsverhaeltnisse()//
                .observeOn(AndroidSchedulers.mainThread())//
                .subscribe { arbeitsverhaeltnisse ->
                    connectRecyclerViewAdapterWithView(rootView, arbeitsverhaeltnisse, it)
                }
        }
        return rootView
    }

    private fun connectRecyclerViewAdapterWithView(rootView: View, arbeitsverhaeltnisse: List<Arbeitsverhaeltnis>,
                                                   fragmentActivity: FragmentActivity) {
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recycler_view_arbeitsverhaeltnisse)
        recyclerView.adapter = ArbeitsverhaeltnisRecyclerViewAdapter(arbeitsverhaeltnisse)
        recyclerView.layoutManager = LinearLayoutManager(fragmentActivity)
    }

    private fun initializeViewModel() {
        arbeitsverhaeltnisUebersichtViewModel =
            ViewModelProviders.of(this, ArbeitsverhaeltnisUebersichtViewModelFactory())
                .get(ArbeitsverhaeltnisUebersichtViewModel::class.java)
    }

    fun newZeiterfassungsEntry() {
        activity?.let {
            DialogOpener.openDialog(it, NeueZeiterfassungDialog(), "dialog_neue_zeiterfassung")
        }
    }


}
