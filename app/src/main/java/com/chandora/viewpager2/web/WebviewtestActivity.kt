package com.chandora.viewpager2.web

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import androidx.appcompat.app.AppCompatActivity
import com.chandora.viewpager2.R
import kotlinx.android.synthetic.main.activity_webviewtest.*

class WebviewtestActivity : AppCompatActivity() {

    val checkSavedState : String = "checkSavedState"
    var isStateSaved : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webviewtest)

        initViews(savedInstanceState)

        }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val bundle = Bundle()
        webviewTest.saveState(bundle)
        outState.putBundle("webViewState", bundle)
        outState.putBoolean(checkSavedState,isStateSaved)
    }

    private fun initViews(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            webviewTest.settings.javaScriptEnabled = true
            webviewTest.loadUrl("https://www.youtube.com/embed/mHvp6DDkhrE")
            webviewTest.webChromeClient = VideoWebChromeClient(this)
        }

        if (savedInstanceState != null) {
            val message = savedInstanceState.getBoolean(checkSavedState)
            webviewTest.restoreState(savedInstanceState.getBundle("webViewState"))

            if (!message) {
                rLTest.visibility = View.GONE
                isStateSaved = true
            }else{
                isStateSaved=false
                rLTest.visibility = View.VISIBLE
            }
        }
    }
}

