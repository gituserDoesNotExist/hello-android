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
import com.example.helloandroid.endOfMonth
import com.example.helloandroid.startOfMonth
import com.example.helloandroid.timerecording.Arbeitsverhaeltnis
import com.example.helloandroid.timerecording.Arbeitsverhaeltnisse
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDate.now


class ArbeitsverhaltnisUebersichtFragment : Fragment(), OnUpdateArbeitsverhaeltnisseListener {

    private var onFragmentInteractionListener: OnFragmentInteractionListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onFragmentInteractionListener = context as? OnFragmentInteractionListener
    }

    override fun onArbeitsverhaeltnisseUpdated() {
        arbeitsverhaeltnisUebersichtViewModel.loadArbeitsverhaeltnisse(arbeitsverhaeltnisDTO)
    }


    private val arbeitsverhaeltnisDTO = ArbeitsverhaeltnisUebersichtDTO(now().startOfMonth(), now().endOfMonth())
    private lateinit var arbeitsverhaeltnisUebersichtViewModel: ArbeitsverhaeltnisUebersichtViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentArbeitsverhaeltnisUebersichtBinding.inflate(inflater, container, false)
        val rootView = binding.root
        binding.arbeitsverhaltnisUebersichtFragment = this
        binding.arbeitsverhaeltnisDTO = arbeitsverhaeltnisDTO

        activity?.let { fragmentActivity ->
            initializeViewModel(fragmentActivity)
            arbeitsverhaeltnisUebersichtViewModel.arbeitsverhaeltnisse.observe(this, Observer {
                addRecyclerView(rootView, fragmentActivity, it)
            })

        }
        return rootView
    }

    private fun addRecyclerView(root: View, activity: FragmentActivity, arbeitsverhaeltnisse: Arbeitsverhaeltnisse) {
        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view_arbeitsverhaeltnisse)
        recyclerView.adapter = ArbeitsverhaeltnisRecyclerViewAdapter(arbeitsverhaeltnisse).apply {
            this.onItemClickListener = View.OnClickListener { v ->
                val viewHolder = v.tag as ArbeitsverhaeltnisRecyclerViewAdapter.ItemViewHolder
                arbeitsverhaeltnisse.findById(viewHolder.arbeitsverhaeltnisRemoteId)?.let {
                    onFragmentInteractionListener?.openArbeitsverhaeltnisDetails(it)
                }
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    private fun initializeViewModel(fragmentActivity: FragmentActivity) {
        arbeitsverhaeltnisUebersichtViewModel =
            ViewModelProviders.of(this, ArbeitsverhaeltnisViewModelFactory())
                .get(ArbeitsverhaeltnisUebersichtViewModel::class.java)//
                .apply {
                    this.loadArbeitsverhaeltnisse(arbeitsverhaeltnisDTO)
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
                arbeitsverhaeltnisDTO.startDate = LocalDate.of(year, month + 1, dayOfMonth)
                arbeitsverhaeltnisUebersichtViewModel.loadArbeitsverhaeltnisse(arbeitsverhaeltnisDTO)
            }
            val startDate = arbeitsverhaeltnisDTO.startDate
            DatePickerDialog(it, onDateSetListener, startDate.year, startDate.monthValue, startDate.dayOfMonth).show()
        }
    }

    fun openDatePickerEndDate() {
        activity?.let {
            val onDateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                arbeitsverhaeltnisDTO.endDate = LocalDate.of(year, month + 1, dayOfMonth)
                arbeitsverhaeltnisUebersichtViewModel.loadArbeitsverhaeltnisse(arbeitsverhaeltnisDTO)
            }
            val endDate = arbeitsverhaeltnisDTO.endDate
            DatePickerDialog(it, onDateSetListener, endDate.year, endDate.monthValue, endDate.dayOfMonth).show()
        }
    }

    interface OnFragmentInteractionListener {
        fun openArbeitsverhaeltnisDetails(arbeitsverhaeltnis: Arbeitsverhaeltnis)
    }

}
