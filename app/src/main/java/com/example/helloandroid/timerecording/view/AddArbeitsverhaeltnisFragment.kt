package com.example.helloandroid.timerecording.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.helloandroid.R
import kotlinx.android.synthetic.main.fragment_add_arbeitsverhaeltnis.view.*


class AddArbeitsverhaeltnisFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_add_arbeitsverhaeltnis, container, false)
        val viewPager = rootView.viewpager

        fragmentManager?.let {
            val adapter = AddArbeitsverhaeltnisViewPagerAdapter(it)
            viewPager.adapter = adapter
            val tabLayout = rootView.sliding_tabs
            tabLayout.setupWithViewPager(viewPager)

        }
        return rootView
    }


}
