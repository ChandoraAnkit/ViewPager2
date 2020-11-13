package com.chandora.viewpager2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_parent.*


class ParentFragment : Fragment() {

    val mAdapter by lazy { ChildPagerAdapter(requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childVP.adapter = mAdapter
        childVP.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ParentFragment()
    }
}