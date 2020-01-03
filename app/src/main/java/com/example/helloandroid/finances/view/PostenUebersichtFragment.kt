package com.example.helloandroid.finances.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders.of
import androidx.navigation.fragment.findNavController
import com.example.helloandroid.DialogOpener
import com.example.helloandroid.R
import com.example.helloandroid.databinding.FragmentPostenUebersichtBinding.inflate
import com.example.helloandroid.finances.PostenStub
import java.util.function.Consumer

class PostenUebersichtFragment : Fragment() {

    private lateinit var postenUebersichtViewModel: PostenUebersichtViewModel
    private lateinit var sharedPostenViewModel: SharedPostenViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = inflate(inflater,container,false)
        binding.lifecycleOwner = this

        val listView = binding.root.findViewById<ListView>(R.id.testit)
        (activity as? AppCompatActivity)?.let {
            it.supportActionBar?.title = resources.getString(R.string.title_fragment_posten_uebersicht)
            initializeViewModels(it)
            postenUebersichtViewModel.postenStubs.observe(this, Observer { stubs ->
                listView.adapter = createPostenArrayAdapter(it, stubs)
            })
        }

        binding.postenUebersichtViewModel = postenUebersichtViewModel
        binding.postenUebersichtFragment = this
        return binding.root
    }

    private fun initializeViewModels(activity: FragmentActivity) {
        sharedPostenViewModel = of(activity).get(SharedPostenViewModel::class.java)
        postenUebersichtViewModel =
            of(activity, FinancesViewModelFactory(activity.application)).get(PostenUebersichtViewModel::class.java)
    }

    fun openNewPostenDialog() {
        activity?.let {
            DialogOpener.openDialog(it, AddPostenDialog(), "dialog_add_posten")
        }
    }

    private fun createPostenArrayAdapter(activity: FragmentActivity, posten: List<PostenStub>): PostenArrayAdapter {
        return PostenArrayAdapter(activity, posten, editPostenConsumer())
    }

    private fun editPostenConsumer(): Consumer<PostenStub> {
        return Consumer {
            sharedPostenViewModel.currentPostenStub = it
            FinancesNavigation.getNavigation(findNavController()).fromUebersichtToDetails()
        }
    }

}
