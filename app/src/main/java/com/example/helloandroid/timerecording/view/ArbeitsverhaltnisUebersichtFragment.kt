package com.example.helloandroid.timerecording.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.helloandroid.DialogOpener
import com.example.helloandroid.HelloDatePickerDialog
import com.example.helloandroid.R
import com.example.helloandroid.databinding.FragmentArbeitsverhaeltnisUebersichtBinding
import com.example.helloandroid.timerecording.TeamupEvents
import org.threeten.bp.LocalDate


class ArbeitsverhaltnisUebersichtFragment : Fragment(), OnUpdateArbeitsverhaeltnisseListener {


    override fun onArbeitsverhaeltnisseUpdated() {
        arbeitsverhaeltnisViewModel.loadArbeitsverhaeltnisse()
    }


    private lateinit var arbeitsverhaeltnisViewModel: ArbeitsverhaeltnisUebersichtViewModel
    private lateinit var sharedTeamupEventViewModel: SharedTeamupEventViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentArbeitsverhaeltnisUebersichtBinding.inflate(inflater, container, false)
        val rootView = binding.root

        (activity as? AppCompatActivity)?.let { activity ->
            activity.title = resources.getString(R.string.title_fragment_arbeitsvheraeltnis_ubersicht)
            initializeViewModel(activity)
            arbeitsverhaeltnisViewModel.teamupEvents.observe(this, Observer {
                addRecyclerView(rootView, activity, it)
            })

        }
        binding.arbeitsverhaltnisUebersichtFragment = this
        binding.viewModel = arbeitsverhaeltnisViewModel
        return rootView
    }

    private fun initializeViewModel(activity: AppCompatActivity) {
        arbeitsverhaeltnisViewModel =
            ViewModelProviders.of(activity, ZeiterfassungViewModelFactory(activity.application))
                .get(ArbeitsverhaeltnisUebersichtViewModel::class.java)//
                .apply { this.loadArbeitsverhaeltnisse() }
        sharedTeamupEventViewModel =
            ViewModelProviders.of(activity, ZeiterfassungViewModelFactory(activity.application))
                .get(SharedTeamupEventViewModel::class.java)
    }

    private fun addRecyclerView(root: View, activity: AppCompatActivity, events: TeamupEvents) {
        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view_arbeitsverhaeltnisse)
        recyclerView.adapter = ArbeitsverhaeltnisRecyclerViewAdapter(events).apply {
            this.onItemClickListener = View.OnClickListener { v ->
                val viewHolder = v.tag as ArbeitsverhaeltnisRecyclerViewAdapter.ItemViewHolder
                events.findById(viewHolder.arbeitsverhaeltnisRemoteId)?.let {
                    sharedTeamupEventViewModel.currentEvent = it
                    ZeiterfassungNavigation.getNavigation(findNavController()).fromUebersichtToDetails()
                }
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }


    fun openAddArbeitsverhaeltnisDialog() {
        activity?.let {
            DialogOpener.openDialog(it, AddArbeitsverhaeltnisDialog(this), "dialog_add_arbeitsverhaeltnis")
        }
    }

    fun openDatePickerStartDate() {
        val onDateSet = onDateSet { arbeitsverhaeltnisViewModel.startDate.set(it) }
        activity?.let { HelloDatePickerDialog(it, onDateSet, arbeitsverhaeltnisViewModel.startDate.get()).show() }
    }

    fun openDatePickerEndDate() {
        val dateSetListener = onDateSet { arbeitsverhaeltnisViewModel.endDate.set(it) }
        activity?.let { HelloDatePickerDialog(it, dateSetListener, arbeitsverhaeltnisViewModel.endDate.get()).show() }
    }

    private fun onDateSet(updateModelListener: (date: LocalDate) -> Unit): (LocalDate) -> Unit {
        return { date ->
            updateModelListener(date)
            arbeitsverhaeltnisViewModel.loadArbeitsverhaeltnisse()
        }
    }

}
