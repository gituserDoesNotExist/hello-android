package com.example.helloandroid.finances.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders.of
import com.example.helloandroid.DialogOpener
import com.example.helloandroid.R
import com.example.helloandroid.finances.PostenStub
import com.example.helloandroid.view.BigDecimalConverter
import java.util.function.Consumer

class PostenUebersichtFragment : Fragment() {

    private lateinit var postenUebersichtViewModel: PostenUebersichtViewModel
    private lateinit var sharedPostenViewModel: SharedPostenViewModel
    lateinit var openFragmentCallback: PostenDetailsFragmentOpener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_posten_uebersicht, container, false)

        val textViewGesamtausgaben = rootView.findViewById<TextView>(R.id.txt_finances_total_amount)
        val btnNewPosten = rootView.findViewById<Button>(R.id.btn_open_new_posten_dialog)
        btnNewPosten.setOnClickListener { openNewPostenDialog() }
        val listView = rootView.findViewById<ListView>(R.id.testit)
        activity?.let {
            initializeViewModels(it)
            postenUebersichtViewModel.postenStubs.observe(this, Observer { stubs ->
                listView.adapter = createPostenArrayAdapter(it, stubs)
                textViewGesamtausgaben.text =
                    BigDecimalConverter.bigDecimalToString(postenUebersichtViewModel.calculateGesamtausgaben())
            })
        }
        return rootView
    }

    private fun initializeViewModels(activity: FragmentActivity) {
        sharedPostenViewModel = of(activity).get(SharedPostenViewModel::class.java)
        postenUebersichtViewModel =
            of(activity, FinancesViewModelFactory(activity.application)).get(PostenUebersichtViewModel::class.java)
    }

    private fun openNewPostenDialog() {
        activity?.let {
            DialogOpener.openDialog(it, AddPostenDialog(), "dialog_add_posten")
        }
    }

    private fun createPostenArrayAdapter(activity: FragmentActivity, posten: List<PostenStub>): PostenArrayAdapter {
        return PostenArrayAdapter(activity, posten, editPostenConsumer(), deletePostenConsumer())
    }

    private fun editPostenConsumer(): Consumer<PostenStub> {
        return Consumer {
            sharedPostenViewModel.currentPostenStub = it
            openFragmentCallback.openPostenDetailsFragment()
        }
    }

    private fun deletePostenConsumer(): Consumer<PostenStub> {
        return Consumer { postenUebersichtViewModel.deletePosten(it) }
    }

    interface PostenDetailsFragmentOpener {
        fun openPostenDetailsFragment()
    }


}
