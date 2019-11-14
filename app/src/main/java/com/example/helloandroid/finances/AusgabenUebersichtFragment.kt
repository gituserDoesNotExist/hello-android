package com.example.helloandroid.finances


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.helloandroid.R
import java.util.function.Consumer

class AusgabenUebersichtFragment : Fragment() {

    private lateinit var ausgabenUebersichtViewModel: AusgabenUebersichtViewModel
    private lateinit var sharedPostenViewModel: SharedPostenViewModel
    private lateinit var gesamtausgaben: TextView
    private lateinit var btnNewPosten: Button
    lateinit var openFragmentCallback: PostenDetailsFragmentOpener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val roowView = inflater.inflate(R.layout.fragment_ausgaben_uebersicht, container, false)

        gesamtausgaben = roowView.findViewById(R.id.txt_finances_total_amount)
        btnNewPosten = roowView.findViewById(R.id.btn_open_new_posten_dialog)
        btnNewPosten.setOnClickListener { openNewPostenDialog() }

        activity?.let { fragmentActivity ->
            sharedPostenViewModel = ViewModelProviders.of(fragmentActivity).get(SharedPostenViewModel::class.java)
            ausgabenUebersichtViewModel =
                ViewModelProviders.of(fragmentActivity, FinancesViewModelFactory(fragmentActivity.application))
                    .get(AusgabenUebersichtViewModel::class.java)
            ausgabenUebersichtViewModel.initializeByFindingAllPosten()
            gesamtausgaben.text = ausgabenUebersichtViewModel.calculateGesamtausgaben().toString()

            observePostenChanges(fragmentActivity, roowView)

        }
        return roowView
    }

    private fun openNewPostenDialog() {
        activity?.let {
            val transaction = it.supportFragmentManager.beginTransaction()
            val prev = it.supportFragmentManager.findFragmentByTag("dialog")
            if (prev != null) {
                transaction.remove(prev)
            }
            transaction.addToBackStack(null)
            val addPostenDialog = AddPostenDialog()
            addPostenDialog.show(transaction, "dialog")
        }

    }

    private fun observePostenChanges(fragmentActivity: FragmentActivity, roowView: View) {
        val listView = roowView.findViewById<ListView>(R.id.testit)
        ausgabenUebersichtViewModel.allPosten.observe(fragmentActivity, Observer { posten ->
            listView.adapter =
                PostenArrayAdapter(fragmentActivity, posten, Consumer {
                    sharedPostenViewModel.currentPosten = it
                    openFragmentCallback.openPostenDetailsFragment()
                })
        })
    }

    interface PostenDetailsFragmentOpener {
        fun openPostenDetailsFragment()
    }


}
