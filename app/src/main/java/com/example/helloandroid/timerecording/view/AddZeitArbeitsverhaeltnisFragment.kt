package com.example.helloandroid.timerecording.view


import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.helloandroid.BaseActivity
import com.example.helloandroid.R
import com.example.helloandroid.timerecording.EventInfo
import com.example.helloandroid.timerecording.ZeitArbeitsverhaeltnis
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer


class AddZeitArbeitsverhaeltnisFragment : UpsertZeitArbeitsverhaeltnisFragment() {


    private var addArbeitseinsatzDisposable: Disposable? = null

    override fun fragmentTitle(): String {
        return resources.getString(R.string.title_add_arbeitsverhaeltnis_fragment)
    }

    override fun initArbeitsverhaeltnis(activity: BaseActivity) {
        upsertViewModel.initEventInfoAndArbeitsverhaeltnis(EventInfo(), ZeitArbeitsverhaeltnis())
    }

    override fun prepareView(rootView: View, config: CalendarConfiguration) {
        upsertViewModel.isUpdateMode.set(false)
        upsertViewModel.editable.set(true)
        upsertViewModel.setLeistungserbringer(config.appUser)
    }


    override fun upsert() {
        addArbeitseinsatzDisposable = upsertViewModel.addArbeitsverhaeltnis()//
            .subscribeOn(AndroidSchedulers.mainThread())//
            .subscribe(Consumer {
                ZeiterfassungNavigation.getNavigation(findNavController()).toUebersicht()
            })
    }


    override fun onStop() {
        super.onStop()
        addArbeitseinsatzDisposable?.dispose()
    }
}
