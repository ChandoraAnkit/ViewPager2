package com.chandora.viewpager2

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter


/**
 * Created by Ankit
 */


class ParentPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {

    override fun getItemCount() = 5

    override fun createFragment(position: Int): Fragment {
       return ParentFragment.newInstance()
    }
}