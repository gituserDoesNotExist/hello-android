package com.example.helloandroid.timerecording.view


import android.app.DatePickerDialog
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
        binding.arbeitsverhaeltnisDTO = arbeitsverhaeltnisViewModel.arbeitsverhaeltnisDTO
        return rootView
    }

    private fun addRecyclerView(root: View, activity: AppCompatActivity, events: TeamupEvents) {
        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view_arbeitsverhaeltnisse)
        recyclerView.adapter = ArbeitsverhaeltnisRecyclerViewAdapter(events).apply {
            this.onItemClickListener = View.OnClickListener { v ->
                val viewHolder = v.tag as ArbeitsverhaeltnisRecyclerViewAdapter.ItemViewHolder
                events.findById(viewHolder.arbeitsverhaeltnisRemoteId)?.let {
                    sharedTeamupEventViewModel.currentEvent = it
                    val action =
                        ArbeitsverhaltnisUebersichtFragmentDirections.actionArbeitsverhaltnisUebersichtFragmentToEditArbeitsverhaeltnisDetailsFragment(
                            false)
                    findNavController().navigate(action)
                }
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    private fun initializeViewModel(activity: AppCompatActivity) {
        arbeitsverhaeltnisViewModel =
            ViewModelProviders.of(activity, ZeiterfassungViewModelFactory(activity.application))
                .get(ArbeitsverhaeltnisUebersichtViewModel::class.java)//
                .apply {
                    this.loadArbeitsverhaeltnisse()
                }
        sharedTeamupEventViewModel =
            ViewModelProviders.of(activity, ZeiterfassungViewModelFactory(activity.application))
                .get(SharedTeamupEventViewModel::class.java)
    }

    fun openAddArbeitsverhaeltnisDialog() {
        activity?.let {
            DialogOpener.openDialog(it, AddArbeitsverhaeltnisDialog(this), "dialog_add_arbeitsverhaeltnis")
        }
    }

    fun openDatePickerStartDate() {
        activity?.let {
            val onDateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                arbeitsverhaeltnisViewModel.arbeitsverhaeltnisDTO.startDate = LocalDate.of(year, month + 1, dayOfMonth)
                arbeitsverhaeltnisViewModel.loadArbeitsverhaeltnisse()
            }
            val startDate = arbeitsverhaeltnisViewModel.arbeitsverhaeltnisDTO.startDate
            DatePickerDialog(it, onDateSetListener, startDate.year, startDate.monthValue, startDate.dayOfMonth).show()
        }
    }

    fun openDatePickerEndDate() {
        activity?.let {
            val onDateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                arbeitsverhaeltnisViewModel.arbeitsverhaeltnisDTO.endDate = LocalDate.of(year, month + 1, dayOfMonth)
                arbeitsverhaeltnisViewModel.loadArbeitsverhaeltnisse()
            }
            val endDate = arbeitsverhaeltnisViewModel.arbeitsverhaeltnisDTO.endDate
            DatePickerDialog(it, onDateSetListener, endDate.year, endDate.monthValue, endDate.dayOfMonth).show()
        }
    }

}
