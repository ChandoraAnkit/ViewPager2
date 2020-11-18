package com.chandora.viewpager2.web

import android.content.pm.ActivityInfo
import android.util.Log
import android.view.View
import android.webkit.WebChromeClient
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity

var checkifRotated : Boolean = false
class VideoWebChromeClient(val activity: FragmentActivity): WebChromeClient() {

    private var mCustomView: View? = null
    private var mCustomViewCallback: CustomViewCallback? = null
    private var mOriginalSystemUiVisibility = 0

    override fun onShowCustomView( paramView: View, paramCustomViewCallback: CustomViewCallback) {
        if (mCustomView != null) {
            onHideCustomView()
            return
        }

        if (checkifRotated){
            checkifRotated = false
            onHideCustomView()
            return
        }

        mCustomView = paramView
        mOriginalSystemUiVisibility = activity.window.decorView.systemUiVisibility
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        mCustomViewCallback = paramCustomViewCallback
        checkifRotated = true

        (activity.window.decorView as FrameLayout).addView(mCustomView, FrameLayout.LayoutParams(-1,-1))
    }

    override fun onHideCustomView() {
        (activity.window.decorView as FrameLayout).removeView(mCustomView)
        mCustomView = null
        checkifRotated = false
        activity.window.decorView.systemUiVisibility = mOriginalSystemUiVisibility
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        mCustomViewCallback?.onCustomViewHidden()
        mCustomViewCallback = null
    }

}
