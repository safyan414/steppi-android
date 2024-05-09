package com.steppi.steppifitness.instagram

import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.*
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import com.steppi.steppifitness.R

class InstagramDialog(context: Context, private val mUrl: String,
                      private val mListener: OAuthDialogListener
) : Dialog(context) {
    private var mWebView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instagram_web_view)
        setUpWebView()
        window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT)
    }


    private fun setUpWebView() {
        mWebView = findViewById<View>(R.id.instagram_webview) as WebView
        mWebView!!.isVerticalScrollBarEnabled = false
        mWebView!!.isHorizontalScrollBarEnabled = true
        mWebView!!.webViewClient = OAuthWebViewClient()
        mWebView!!.settings.javaScriptEnabled = true
        mWebView!!.settings.domStorageEnabled = true
        mWebView!!.settings.loadWithOverviewMode = true
        mWebView!!.settings.useWideViewPort = true
        mWebView!!.settings.defaultZoom = WebSettings.ZoomDensity.FAR
        mWebView!!.setInitialScale(1)
//        mWebView!!.clearCache(true)
        mWebView!!.loadUrl(mUrl)
        mWebView!!.layoutParams = FILL

    }

    private inner class OAuthWebViewClient : WebViewClient() {

        @RequiresApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            val url = request?.url.toString()
            Log.d(TAG, "Redirecting URL $url")
            if (url.equals("https://www.instagram.com/", true)) {
//                Log.e("URL to print", "URL to print : $mUrl")
                mWebView!!.loadUrl(mUrl)
                return true
            } else if (url.startsWith(InstagramApp.mCallbackUrl)) {
                val urls = url.split("=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                mListener.onComplete(urls[1])
                this@InstagramDialog.dismiss()
                return true
            }
            return false
        }

        @SuppressWarnings("deprecation")
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            Log.d(TAG, "Redirecting URL $url")
            if (url.equals("https://www.instagram.com/", true)) {
//                Log.e("URL to print", "URL to print : $mUrl")
                mWebView!!.loadUrl(mUrl)
                return true
            } else if (url.startsWith(InstagramApp.mCallbackUrl)) {
                val urls = url.split("=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                mListener.onComplete(urls[1])
                this@InstagramDialog.dismiss()
                return true
            }
            return false
        }

        @RequiresApi(Build.VERSION_CODES.N)
        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
            Log.d(TAG, "Page error: ${error?.description}")
            super.onReceivedError(view, request, error)
            mListener.onError(error?.description.toString())
            this@InstagramDialog.dismiss()
        }

        @SuppressWarnings("deprecation")
        override fun onReceivedError(view: WebView, errorCode: Int,
                                     description: String, failingUrl: String) {
            Log.d(TAG, "Page error: $description")

            super.onReceivedError(view, errorCode, description, failingUrl)
            mListener.onError(description)
            this@InstagramDialog.dismiss()
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            Log.d(TAG, "Loading URL: $url")

            super.onPageStarted(view, url, favicon)
            findViewById<View>(R.id.instagram_progress).visibility = View.VISIBLE
            //            progressBar.show();
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            Log.d(TAG, "onPageFinished URL: $url")
            //            progressBar.dismiss();
            findViewById<View>(R.id.instagram_progress).visibility = View.GONE
        }
    }

    interface OAuthDialogListener {
        fun onComplete(accessToken: String)

        fun onError(error: String)
    }

    companion object {
        internal val FILL = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
        //    private AlertDialog progressBar;

        private val TAG = "Instagram-WebView"
    }

}