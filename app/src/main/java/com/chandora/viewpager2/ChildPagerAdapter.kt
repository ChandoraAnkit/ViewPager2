package com.chandora.viewpager2

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


/**
 * Created by Ankit
 */


class ChildPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
       return 6
    }

    override fun createFragment(position: Int): Fragment {
       return ChildFragment.newInstance()
    }
}