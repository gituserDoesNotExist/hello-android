package com.example.helloandroid.timerecording.view


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helloandroid.BaseActivity
import com.example.helloandroid.R
import com.example.helloandroid.databinding.FragmentArbeitsverhaeltnisUebersichtBinding
import com.example.helloandroid.timerecording.Arbeitseinsaetze
import com.example.helloandroid.timerecording.StueckArbeitsverhaeltnis
import com.example.helloandroid.timerecording.ZeitArbeitsverhaeltnis


class ArbeitsverhaltnisUebersichtFragment : Fragment() {


    private lateinit var arbeitsverhaeltnisViewModel: ArbeitsverhaeltnisUebersichtViewModel
    private lateinit var sharedZeitArbeitsverhaeltnisViewModel: SharedZeitArbeitsverhaeltnisViewModel
    private lateinit var sharedStueckArbeitsverhaeltnisViewModel: SharedStueckArbeitsverhaeltnisViewModel
    private lateinit var filtersViewModel: FiltersViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        val binding = FragmentArbeitsverhaeltnisUebersichtBinding.inflate(inflater, container, false)
        val rootView = binding.root

        (activity as? BaseActivity)?.let { activity ->
            activity.supportActionBar?.title = resources.getString(R.string.title_fragment_arbeitsvheraeltnis_ubersicht)
            configureNetworkErrorHandling(activity)
            initializeViewModel(activity)
            addFiltersRecyclerView(rootView, activity)
            arbeitsverhaeltnisViewModel.arbeitseinsaetze.observe(this, Observer {
                addEventsRecyclerView(rootView, activity, it)
            })

        }
        binding.arbeitsverhaltnisUebersichtFragment = this
        binding.viewModel = arbeitsverhaeltnisViewModel




        return rootView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_arbeitsverhaeltnis_uebersicht, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_filter_arbeitshverhaeltnisse -> openFilterFragment()
        }
        return false
    }

    private fun configureNetworkErrorHandling(activity: BaseActivity) {
        activity.hideProgressbarCallback = {
            arbeitsverhaeltnisViewModel.showProgressbar.set(false)
        }
        activity.reloadCallback = {
            arbeitsverhaeltnisViewModel.loadArbeitsverhaeltnisse(filtersViewModel.suchkriterien)
        }
    }

    private fun initializeViewModel(activity: AppCompatActivity) {
        filtersViewModel = ViewModelProviders.of(activity, ZeiterfassungViewModelFactory(activity.application))
            .get(FiltersViewModel::class.java)
        arbeitsverhaeltnisViewModel =
            ViewModelProviders.of(activity, ZeiterfassungViewModelFactory(activity.application))
                .get(ArbeitsverhaeltnisUebersichtViewModel::class.java)//
                .apply {
                    this.loadArbeitsverhaeltnisse(filtersViewModel.suchkriterien)
                }
        sharedZeitArbeitsverhaeltnisViewModel =
            ViewModelProviders.of(activity).get(SharedZeitArbeitsverhaeltnisViewModel::class.java)
        sharedStueckArbeitsverhaeltnisViewModel =
            ViewModelProviders.of(activity).get(SharedStueckArbeitsverhaeltnisViewModel::class.java)
    }

    private fun addEventsRecyclerView(root: View, activity: AppCompatActivity, arbeitseinsaetze: Arbeitseinsaetze) {
        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view_arbeitsverhaeltnisse)
        recyclerView.adapter = ArbeitseinsaetzeRecyclerViewAdapter(arbeitseinsaetze).apply {
            this.onItemClickListener = View.OnClickListener { v ->
                val viewHolder = v.tag as ArbeitseinsaetzeRecyclerViewAdapter.ItemViewHolder
                openMatchingEditFragment(arbeitseinsaetze, viewHolder.arbeitsverhaeltnisRemoteId)
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    private fun openMatchingEditFragment(arbeitseinsaetze: Arbeitseinsaetze, eventId: String) {
        arbeitseinsaetze.findById(eventId)?.let {
            val verhaeltnis = it.arbeitsverhaeltnis
            val navController = findNavController()
            if (verhaeltnis is ZeitArbeitsverhaeltnis) {
                sharedZeitArbeitsverhaeltnisViewModel.eventInfo = it.eventInfo
                sharedZeitArbeitsverhaeltnisViewModel.currentArbeitsverhaeltnis = verhaeltnis
                ZeiterfassungNavigation.getNavigation(navController).fromUebersichtToEditZeitArbeitsverhaeltnis()
            } else if (verhaeltnis is StueckArbeitsverhaeltnis) {
                sharedStueckArbeitsverhaeltnisViewModel.eventInfo = it.eventInfo
                sharedStueckArbeitsverhaeltnisViewModel.currentArbeitsverhaeltnis = verhaeltnis
                ZeiterfassungNavigation.getNavigation(navController).fromUebersichtToEditStueckArbeitsverhaeltnis()
            }
        }
    }

    private fun addFiltersRecyclerView(root: View, activity: AppCompatActivity) {
        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view_filters)

        recyclerView.adapter = FiltersRecyclerViewAdapter(filtersViewModel.suchkriterien) {
            filtersViewModel.removeFilter(it)
            arbeitsverhaeltnisViewModel.loadArbeitsverhaeltnisse(filtersViewModel.suchkriterien)
        }
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
    }


    fun openAddArbeitsverhaeltnisFragment() {
        ZeiterfassungNavigation.getNavigation(findNavController()).fromUebersichtToAddZeitArbeitseinsatz()
    }

    fun openAddStueckArbeitsverhaeltnisFragment() {
        ZeiterfassungNavigation.getNavigation(findNavController()).fromUebersichtToAddStueckArbeitsverhaeltnis()
    }

    private fun openFilterFragment() {
        ZeiterfassungNavigation.getNavigation(findNavController()).fromUebersichtToSuchfilter()
    }


}
