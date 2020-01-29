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
    private var deleteArbeitsverhaeltnisDisposable: Disposable? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        val rootView = super.onCreateView(inflater, container, savedInstanceState)
        upsertZeitArbeitsverhaeltnisViewModel.isUpdateMode.set(true)
        upsertZeitArbeitsverhaeltnisViewModel.editable.set(false)

        (activity as? BaseActivity)?.let {
            it.supportActionBar?.title = resources.getString(R.string.title_edit_arbeitsverhaeltnis_fragment)
        }
        return rootView
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
        deleteArbeitsverhaeltnisDisposable = upsertZeitArbeitsverhaeltnisViewModel.deleteArbeitsverhaeltnis()//
            .subscribe(Consumer<String> {
                ZeiterfassungNavigation.getNavigation(navController).toUebersicht()
            })
    }


    override fun upsert() {
        updateArbeitsverhaeltnisDisposable = upsertZeitArbeitsverhaeltnisViewModel.updateArbeitsverhaeltnis()//
            .observeOn(AndroidSchedulers.mainThread())//
            .subscribe(Consumer<String> {
                ZeiterfassungNavigation.getNavigation(findNavController()).toUebersicht()
            })
    }

    override fun initializeArbeitsverhaeltnis() {
        (activity as? BaseActivity)?.let {
            initSharedArbeitsverhaeltnisViewModel(it)
            upsertZeitArbeitsverhaeltnisViewModel.initEventInfoAndArbeitsverhaeltnis(
                sharedZeitArbeitsverhaeltnisViewModel.eventInfo,
                sharedZeitArbeitsverhaeltnisViewModel.currentArbeitsverhaeltnis)
        }
    }

    private fun initSharedArbeitsverhaeltnisViewModel(activity: BaseActivity) {
        sharedZeitArbeitsverhaeltnisViewModel =
            ViewModelProviders.of(activity).get(SharedZeitArbeitsverhaeltnisViewModel::class.java)
    }


    override fun onStop() {
        super.onStop()
        updateArbeitsverhaeltnisDisposable?.dispose()
        deleteArbeitsverhaeltnisDisposable?.dispose()
    }
}
