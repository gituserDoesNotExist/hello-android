package com.example.helloandroid.timerecording.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


class AddArbeitsverhaeltnisViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> AddZeitArbeitsverhaeltnisFragment()
            1 -> AddStueckArbeitsverhaeltnisFragment()
            else -> BlankFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Zeit"
            1 -> "Stück"
            else -> ""
        }
    }
}