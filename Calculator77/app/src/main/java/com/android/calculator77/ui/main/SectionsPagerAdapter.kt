package com.android.calculator77.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.logging.Logger
import android.os.Bundle
import android.R

private val TAB_TITLES = arrayOf(
    com.android.calculator77.R.string.tab_text_1,
    com.android.calculator77.R.string.tab_text_2
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Logger.getLogger(SectionsPagerAdapter::class.java.name).warning(String.format("pos = %d", position))

        return if (position == 0)
            PlaceholderFragment.newInstance(position + 1)
        else
            AnimationFragment.newInstance(position + 1)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}