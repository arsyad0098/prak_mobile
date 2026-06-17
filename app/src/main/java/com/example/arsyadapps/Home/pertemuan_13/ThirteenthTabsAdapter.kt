package com.example.arsyadapps.Home.pertemuan_13

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ThirteenthTabsAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabScanFragment()
            1 -> TabQrcodeFragment()
            2 -> TabCaptureFragment()
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}