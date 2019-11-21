package com.example.helloandroid.finances.view


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
import androidx.lifecycle.ViewModelProviders.*
import com.example.helloandroid.R
import com.example.helloandroid.databinding.FragmentPostenUebersichtBinding
import com.example.helloandroid.finances.Posten
import com.example.helloandroid.finances.PostenStub
import java.util.function.Consumer

class PostenUebersichtFragment : Fragment() {

    private lateinit var postenUebersichtViewModel: PostenUebersichtViewModel
    private lateinit var sharedPostenViewModel: SharedPostenViewModel
    private lateinit var textViewGesamtausgaben: TextView
    private lateinit var btnNewPosten: Button
    lateinit var openFragmentCallback: PostenDetailsFragmentOpener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentPostenUebersichtBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.postenUebersichtViewModel


        val roowView = inflater.inflate(R.layout.fragment_posten_uebersicht, container, false)

//        textViewGesamtausgaben = roowView.findViewById(R.id.txt_finances_total_amount)
        btnNewPosten = roowView.findViewById(R.id.btn_open_new_posten_dialog)
        btnNewPosten.setOnClickListener { openNewPostenDialog() }
        val listView = roowView.findViewById<ListView>(R.id.testit)

        activity?.let {
            initializeViewModels(it)
            postenUebersichtViewModel.findPostenStubs().observe(this,Observer {postenStubs ->
                    listView.adapter = createPostenArrayAdapter(it, postenStubs)
                })
            }
//            postenUebersichtViewModel.calculateGesamtausgaben().observe(this, Observer { gesamtausgaben ->
//                textViewGesamtausgaben.text = gesamtausgaben.toString()
//            })
        return roowView
    }

    private fun initializeViewModels(activity: FragmentActivity) {
        sharedPostenViewModel = of(activity).get(SharedPostenViewModel::class.java)
        postenUebersichtViewModel =
            of(activity, FinancesViewModelFactory(activity.application)).get(PostenUebersichtViewModel::class.java)
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

    private fun createPostenArrayAdapter(fragmentActivity: FragmentActivity, posten: List<PostenStub>): PostenArrayAdapter {
        return PostenArrayAdapter(fragmentActivity, posten, Consumer {
            sharedPostenViewModel.currentPostenStub = it
            openFragmentCallback.openPostenDetailsFragment()
        })
    }

    interface PostenDetailsFragmentOpener {
        fun openPostenDetailsFragment()
    }


}
