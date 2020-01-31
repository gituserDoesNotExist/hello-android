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


    override fun fragmentTitle(): String {
        return resources.getString(R.string.title_add_arbeitsverhaeltnis_fragment)
    }

    override fun initArbeitsverhaeltnis(activity: BaseActivity) {
        viewModel.initEventInfoAndArbeitsverhaeltnis(EventInfo(), StueckArbeitsverhaeltnis())
    }

    override fun prepareView(rootView: View, config: CalendarConfiguration) {
        viewModel.isUpdateMode.set(false)
        viewModel.editable.set(true)
            viewModel.setLeistungserbringer(config.appUser)
    }




    override fun upsert() {
        addArbeitseinsatzDisposable = viewModel.addArbeitsverhaeltnis()//
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
