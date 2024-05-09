package com.steppi.steppifitness.ui.common

import android.annotation.SuppressLint
import android.os.Build
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import com.steppi.steppifitness.R
import com.steppi.steppifitness.app.STConstants
import com.steppi.steppifitness.ui.base.STBaseActivity
import com.steppi.steppifitness.utils.STPreference
import kotlinx.android.synthetic.main.activity_web_view.*
import kotlinx.android.synthetic.main.header_layout.*

class STWebViewActivity : STBaseActivity() {
    private var header: String? = null
    private lateinit var url: String

    override fun getLayoutId(): Int {
        return R.layout.activity_web_view
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun initViews() {
        getDataIntent()
        animateLayout(headerContainer, webView)
        setHeaderName(header ?: "")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val cookieManager: CookieManager = CookieManager.getInstance()
            cookieManager.setAcceptThirdPartyCookies(webView, true)
        }
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        val extraHeaders =
            HashMap<String, String>()
        extraHeaders[STConstants.HEADER_KEY_LANGUAGE] =
            "${STPreference.getLanguageCode(activityContext)}"
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url, extraHeaders)
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
            }
        }
        webView.loadUrl(url, extraHeaders)
    }

    private fun getDataIntent() {
        header = intent.getStringExtra(STConstants.WEB_HEADER)
        intent.getStringExtra(STConstants.WEB_URL)?.let {
            url = it
        }
    }
}
