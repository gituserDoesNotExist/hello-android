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


class EditStueckArbeitsverhaeltnisFragment : UpsertStueckArbeitsverhaeltnisFragment() {


    private var updateArbeitsverhaeltnisDisposable: Disposable? = null
    private lateinit var sharedStueckArbeitsverhaeltnisViewModel: SharedStueckArbeitsverhaeltnisViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        val rootView = super.onCreateView(inflater, container, savedInstanceState)
        upsertStueckArbeitsverhaeltnisViewModel.updateArbeitsverhaeltnis.set(true)
        upsertStueckArbeitsverhaeltnisViewModel.editable.set(false)

        (activity as? BaseActivity)?.let {
            it.supportActionBar?.title = resources.getString(R.string.title_edit_arbeitsverhaeltnis_fragment)
            initSharedArbeitsverhaeltnisViewModel(it)
            upsertStueckArbeitsverhaeltnisViewModel.initEventInfoAndArbeitsverhaeltnis(
                sharedStueckArbeitsverhaeltnisViewModel.eventInfo,
                sharedStueckArbeitsverhaeltnisViewModel.currentArbeitsverhaeltnis)
        }
        return rootView
    }

    private fun initSharedArbeitsverhaeltnisViewModel(it: BaseActivity) {
        sharedStueckArbeitsverhaeltnisViewModel =
            ViewModelProviders.of(it).get(SharedStueckArbeitsverhaeltnisViewModel::class.java)
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
                upsertStueckArbeitsverhaeltnisViewModel.editable.set(true)
            }
        }
        return false
    }

    private fun confirmDeleteListener(navController: NavController) {
        upsertStueckArbeitsverhaeltnisViewModel.deleteArbeitsverhaeltnis()
        ZeiterfassungNavigation.getNavigation(navController).fromUpdateZeitArbeitsverhaeltnisTouebersicht()
    }


    override fun upsert() {
        updateArbeitsverhaeltnisDisposable = upsertStueckArbeitsverhaeltnisViewModel.updateArbeitsverhaeltnis()//
            .observeOn(AndroidSchedulers.mainThread())//
            .subscribe(Consumer<String> {
                ZeiterfassungNavigation.getNavigation(findNavController())
                    .fromEditStueckArbeitsverhaeltnisToUebersicht()
            })
    }


    override fun onStop() {
        super.onStop()
        updateArbeitsverhaeltnisDisposable?.dispose()
    }
}
