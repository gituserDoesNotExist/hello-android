package com.example.helloandroid.timerecording.view


import android.os.Bundle
import android.view.*
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.helloandroid.BaseActivity
import com.example.helloandroid.ConfirmDeleteDialog
import com.example.helloandroid.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer


class EditZeitArbeitsverhaeltnisFragment : UpsertZeitArbeitsverhaeltnisFragment() {


    private var updateArbeitsverhaeltnisDisposable: Disposable? = null
    private lateinit var sharedZeitArbeitsverhaeltnisViewModel: SharedZeitArbeitsverhaeltnisViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        val rootView = super.onCreateView(inflater, container, savedInstanceState)
        upsertZeitArbeitsverhaeltnisViewModel.updateArbeitsverhaeltnis.set(true)
        upsertZeitArbeitsverhaeltnisViewModel.editable.set(false)

        (activity as? BaseActivity)?.let {
            it.supportActionBar?.title = resources.getString(R.string.title_edit_arbeitsverhaeltnis_fragment)
            initSharedArbeitsverhaeltnisViewModel(it)
            upsertZeitArbeitsverhaeltnisViewModel.initEventInfoAndArbeitsverhaeltnis(
                sharedZeitArbeitsverhaeltnisViewModel.eventInfo,
                sharedZeitArbeitsverhaeltnisViewModel.currentArbeitsverhaeltnis)
        }
        return rootView
    }

    private fun initSharedArbeitsverhaeltnisViewModel(it: BaseActivity) {
        sharedZeitArbeitsverhaeltnisViewModel =
            ViewModelProviders.of(it).get(SharedZeitArbeitsverhaeltnisViewModel::class.java)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_arbeitsverhaeltnis_details, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete_arbeitsverhaeltnis -> {
                ConfirmDeleteDialog(this.context) { confirmDeleteListener(findNavController()) }.show()
            }
            R.id.action_edit_arbeitsverhaeltnis -> {
                upsertZeitArbeitsverhaeltnisViewModel.editable.set(true)
            }
        }
        return false
    }

    private fun confirmDeleteListener(navController: NavController) {
        upsertZeitArbeitsverhaeltnisViewModel.deleteArbeitsverhaeltnis()
        ZeiterfassungNavigation.getNavigation(navController).fromUpdateZeitArbeitsverhaeltnisTouebersicht()
    }


    override fun performUpsertOperation() {
        updateArbeitsverhaeltnisDisposable = upsertZeitArbeitsverhaeltnisViewModel.updateArbeitsverhaeltnis()//
            .observeOn(AndroidSchedulers.mainThread())//
            .subscribe(Consumer<String> {
                ZeiterfassungNavigation.getNavigation(findNavController())
                    .fromUpdateZeitArbeitsverhaeltnisTouebersicht()
            })
    }


    override fun onStop() {
        super.onStop()
        updateArbeitsverhaeltnisDisposable?.dispose()
    }
}
