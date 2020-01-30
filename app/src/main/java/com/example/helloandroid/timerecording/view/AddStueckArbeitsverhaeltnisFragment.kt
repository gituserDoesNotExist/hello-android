package com.example.helloandroid.timerecording.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.helloandroid.BaseActivity
import com.example.helloandroid.R
import com.example.helloandroid.timerecording.EventInfo
import com.example.helloandroid.timerecording.StueckArbeitsverhaeltnis
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer


class AddStueckArbeitsverhaeltnisFragment : UpsertStueckArbeitsverhaeltnisFragment() {


    private var addArbeitseinsatzDisposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = super.onCreateView(inflater, container, savedInstanceState)

        upsertStueckArbeitsverhaeltnisViewModel.isUpdateMode.set(false)
        upsertStueckArbeitsverhaeltnisViewModel.editable.set(true)

        appConfigurationViewModel.calendarConfig.observe(this, Observer {
            upsertStueckArbeitsverhaeltnisViewModel.setLeistungserbringer(it.appUser)
        })

        (activity as? BaseActivity)?.let {
            it.supportActionBar?.title = resources.getString(R.string.title_add_arbeitsverhaeltnis_fragment)
        }


        return rootView
    }


    override fun upsert() {
        addArbeitseinsatzDisposable = upsertStueckArbeitsverhaeltnisViewModel.addArbeitsverhaeltnis()//
            .subscribeOn(AndroidSchedulers.mainThread())//
            .subscribe(Consumer {
                ZeiterfassungNavigation.getNavigation(findNavController()).toUebersicht()
            })
    }

    override fun initializeArbeitsverhaeltnis() {
        upsertStueckArbeitsverhaeltnisViewModel.initEventInfoAndArbeitsverhaeltnis(EventInfo(),
            StueckArbeitsverhaeltnis())
    }

    override fun onStop() {
        super.onStop()
        addArbeitseinsatzDisposable?.dispose()
    }
}
