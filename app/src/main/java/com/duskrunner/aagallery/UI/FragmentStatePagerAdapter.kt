package com.duskrunner.aagallery.UI

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class FragmentStatePagerAdapter(fm: FragmentManager, fragments: List<ItemFragment>): FragmentPagerAdapter(fm) {
    private val frags = fragments
    override fun getItem(position: Int): Fragment {
        return frags[position]
    }

    override fun getCount(): Int {
        return frags.size
    }
}