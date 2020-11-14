package com.chandora.viewpager2

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.abs

class MainActivity : AppCompatActivity(), ToggleVerticalViewPagerScrolling{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewPager()

    }

    fun initViewPager(){
        parentVp.adapter = ParentPagerAdapter(this)
        parentVp.orientation = ViewPager2.ORIENTATION_VERTICAL
        parentVp.setPageTransformer(VerticalPageTransformer())
    }

    private inner class VerticalPageTransformer : ViewPager2.PageTransformer {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun transformPage(
            view: View,
            position: Float
        ) {
            val pageWidth = view.height

            if(position < -1){
                view.alpha = 0f
            }

           else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.translationY = 0f
                view.translationZ = 0f
                view.scaleX = 1f
                view.scaleY = 1f
                view.alpha = 1f

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.alpha = 1-position
                // Counteract the default slide transition
                view.translationY = pageWidth * -position
                view.translationZ = -1f

                // Scale the page down (between MIN_SCALE and 1)
                val MIN_SCALE = 0.75f
                val scaleFactor: Float = (MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - abs(position)))
                view.scaleX = scaleFactor
                view.scaleY = scaleFactor
            }
            else{
                view.alpha = 0f
            }
        }

    }

    override fun trigger(page: Int) {
        when (page){
            0 ->    parentVp.isUserInputEnabled = true
            else -> parentVp.isUserInputEnabled= false
        }
    }

}