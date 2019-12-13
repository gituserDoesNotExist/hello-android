package com.example.helloandroid.timerecording.view


import android.app.DatePickerDialog
import android.content.Context
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
import com.example.helloandroid.timerecording.TeamupEvent
import com.example.helloandroid.timerecording.TeamupEvents
import org.threeten.bp.LocalDate


class ArbeitsverhaltnisUebersichtFragment : Fragment(), OnUpdateArbeitsverhaeltnisseListener {

    private var onFragmentInteractionListener: OnFragmentInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onFragmentInteractionListener = context as? OnFragmentInteractionListener
    }

    override fun onArbeitsverhaeltnisseUpdated() {
        arbeitsverhaeltnisViewModel.loadArbeitsverhaeltnisse()
    }


    private lateinit var arbeitsverhaeltnisViewModel: ArbeitsverhaeltnisUebersichtViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentArbeitsverhaeltnisUebersichtBinding.inflate(inflater, container, false)
        val rootView = binding.root

        activity?.let { fragmentActivity ->
            fragmentActivity?.title = resources.getString(R.string.title_fragment_arbeitsvheraeltnis_ubersicht)
            initializeViewModel(fragmentActivity)
            arbeitsverhaeltnisViewModel.teamupEvents.observe(this, Observer {
                addRecyclerView(rootView, fragmentActivity, it)
            })

        }
        binding.arbeitsverhaltnisUebersichtFragment = this
        binding.arbeitsverhaeltnisDTO = arbeitsverhaeltnisViewModel.arbeitsverhaeltnisDTO
        return rootView
    }

    private fun addRecyclerView(root: View, activity: FragmentActivity, events: TeamupEvents) {
        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view_arbeitsverhaeltnisse)
        recyclerView.adapter = ArbeitsverhaeltnisRecyclerViewAdapter(events).apply {
            this.onItemClickListener = View.OnClickListener { v ->
                val viewHolder = v.tag as ArbeitsverhaeltnisRecyclerViewAdapter.ItemViewHolder
                events.findById(viewHolder.arbeitsverhaeltnisRemoteId)?.let {
                    onFragmentInteractionListener?.openArbeitsverhaeltnisDetailsFragment(it)
                }
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    private fun initializeViewModel(fragmentActivity: FragmentActivity) {
        arbeitsverhaeltnisViewModel =
            ViewModelProviders.of(this, ZeiterfassungViewModelFactory(fragmentActivity.application))
                .get(ArbeitsverhaeltnisUebersichtViewModel::class.java)//
                .apply {
                    this.loadArbeitsverhaeltnisse()
                }
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

    interface OnFragmentInteractionListener {
        fun openArbeitsverhaeltnisDetailsFragment(teamupEvent: TeamupEvent)
    }

}
