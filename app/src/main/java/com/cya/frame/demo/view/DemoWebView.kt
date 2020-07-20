package com.cya.frame.demo.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.webkit.*
import com.orhanobut.logger.Logger

class DemoWebView : WebView {

    interface ICallback {
        fun loadPageFinish()
    }

    var iCallback: ICallback? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        initSettings()
        webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }

        }
        webChromeClient = object : WebChromeClient() {

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                Logger.e("加载进度:${newProgress}")
                if (newProgress >=100){
                    iCallback?.loadPageFinish()
                }
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initSettings() {
        settings.apply {
            domStorageEnabled = true//主要是这句
            javaScriptEnabled = true//启用js
            blockNetworkImage = false//解决图片不显示
            loadsImagesAutomatically = true;
            javaScriptCanOpenWindowsAutomatically = true
            allowFileAccess = true
            layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
            setSupportZoom(true)
            builtInZoomControls = false
            setSupportMultipleWindows(false)
            setAppCacheEnabled(true)
            databaseEnabled = true
            setGeolocationEnabled(true)
            setAppCacheMaxSize(java.lang.Long.MAX_VALUE)
            pluginState = WebSettings.PluginState.ON_DEMAND
            cacheMode = WebSettings.LOAD_DEFAULT
            mediaPlaybackRequiresUserGesture = false
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mixedContentMode = android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }
        }
    }
}