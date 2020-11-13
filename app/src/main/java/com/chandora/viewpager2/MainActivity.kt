package com.chandora.viewpager2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.abs

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        parentVp.adapter = ParentPagerAdapter(this)
        parentVp.orientation = ViewPager2.ORIENTATION_VERTICAL

        parentVp.setPageTransformer(VerticalPageTransformer())
    }


    private inner class VerticalPageTransformer : ViewPager2.PageTransformer {
        override fun transformPage(
            view: View,
            position: Float
        ) {
            val pageWidth = view.height
            view.alpha = 1 - position

            if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.translationY = 0f
                view.scaleX = 1f
                view.scaleY = 1f

            } else if (position <= 1) { // (0,1]
                // Fade the page out.

                // Counteract the default slide transition
                view.translationY = pageWidth * -position

                // Scale the page down (between MIN_SCALE and 1)
                val MIN_SCALE = 0.75f
                val scaleFactor: Float = (MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - abs(position)))
                view.scaleX = scaleFactor
                view.scaleY = scaleFactor
            }
        }

    }
}