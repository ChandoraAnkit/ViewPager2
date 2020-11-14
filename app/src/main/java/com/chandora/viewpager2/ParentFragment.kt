package com.chandora.viewpager2

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import kotlinx.android.synthetic.main.fragment_parent.*


class ParentFragment() : Fragment() {

    val mAdapter by lazy { ChildPagerAdapter(requireActivity()) }

    lateinit var tv: ToggleVerticalViewPagerScrolling


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initchidvp()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        tv = context as ToggleVerticalViewPagerScrolling

    }

    fun initchidvp(){

        childVP.adapter = mAdapter
        childVP.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        childVP.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

                Log.d("Anu", position.toString())
                tv.trigger(position)
            }
        })
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ParentFragment()
    }
}

interface ToggleVerticalViewPagerScrolling {
    fun trigger(page: Int)
}