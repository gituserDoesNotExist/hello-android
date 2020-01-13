package com.example.helloandroid.timerecording.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.helloandroid.R
import com.google.android.material.tabs.TabLayout


class AddArbeitsverhaeltnisFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_add_arbeitsverhaeltnis, container, false)
        val viewPager = rootView.findViewById<ViewPager>(R.id.viewpager)

        fragmentManager?.let {
            val adapter = AddArbeitsverhaeltnisViewPagerAdapter(it)
            viewPager.adapter = adapter
            val tabLayout = rootView.findViewById<TabLayout>(R.id.sliding_tabs)
            tabLayout.setupWithViewPager(viewPager)

        }
        return rootView
    }


}
