package com.example.helloandroid.timerecording.view


import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
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
    private lateinit var sharedArbeitsverhaeltnisViewModel: SharedZeitArbeitsverhaeltnisViewModel
    private var deleteArbeitsverhaeltnisDisposable: Disposable? = null

    override fun fragmentTitle(): String {
        return resources.getString(R.string.title_edit_arbeitsverhaeltnis_fragment)
    }

    override fun initArbeitsverhaeltnis(activity: BaseActivity) {
        sharedArbeitsverhaeltnisViewModel = activity.provideViewModel(SharedZeitArbeitsverhaeltnisViewModel::class.java)
        val eventInfo = sharedArbeitsverhaeltnisViewModel.eventInfo
        val arbeitsverhaeltnis = sharedArbeitsverhaeltnisViewModel.currentArbeitsverhaeltnis
        upsertViewModel.initEventInfoAndArbeitsverhaeltnis(eventInfo, arbeitsverhaeltnis)
    }

    override fun prepareView(rootView: View, config: CalendarConfiguration) {
        setHasOptionsMenu(true)
        upsertViewModel.isUpdateMode.set(true)
        upsertViewModel.editable.set(false)
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
                upsertViewModel.editable.set(true)
            }
        }
        return false
    }

    private fun confirmDeleteListener(navController: NavController) {
        deleteArbeitsverhaeltnisDisposable = upsertViewModel.deleteArbeitsverhaeltnis()//
            .subscribe(Consumer<String> {
                ZeiterfassungNavigation.getNavigation(navController).toUebersicht()
            })
    }


    override fun upsert() {
        updateArbeitsverhaeltnisDisposable = upsertViewModel.updateArbeitsverhaeltnis()//
            .observeOn(AndroidSchedulers.mainThread())//
            .subscribe(Consumer<String> {
                ZeiterfassungNavigation.getNavigation(findNavController()).toUebersicht()
            })
    }


    override fun onStop() {
        super.onStop()
        updateArbeitsverhaeltnisDisposable?.dispose()
        deleteArbeitsverhaeltnisDisposable?.dispose()
    }
}
