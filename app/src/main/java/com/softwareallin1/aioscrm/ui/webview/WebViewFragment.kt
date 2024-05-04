package com.softwareallin1.aioscrm.ui.webview

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.webkit.WebSettings
import android.webkit.WebView
import com.softwareallin1.aioscrm.MainActivity
import com.softwareallin1.aioscrm.R
import com.softwareallin1.aioscrm.base.FragmentBase
import com.softwareallin1.aioscrm.base.ToolbarModel
import com.softwareallin1.aioscrm.base.ViewModelBase
import com.softwareallin1.aioscrm.databinding.FragmentWebViewBinding
import com.softwareallin1.aioscrm.utils.ArgumentsKey.WEB_TITLE
import com.softwareallin1.aioscrm.utils.ArgumentsKey.WEB_URL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewFragment : FragmentBase<ViewModelBase, FragmentWebViewBinding>() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        var webView: WebView? = null
    }

    override fun getLayoutId(): Int = R.layout.fragment_web_view

    override fun setupToolbar() {
        (activity as MainActivity).setStatusBarColor(R.color.white, true)
        viewModel.setToolbarItems(
            ToolbarModel(
                isVisible = true,
                title = arguments?.getString(WEB_TITLE).toString(),
                isBottomNavVisible = false,
                isBackButtonVisible = true
            )
        )
    }

    override fun initializeScreenVariables() {
        webView = getDataBinding().webView
        webView?.webViewClient = WebViewClient(viewModel, (activity as MainActivity))
        webView?.settings?.setSupportZoom(true)
        webView?.settings?.javaScriptEnabled = true
        webView?.settings?.allowFileAccess = true
        webView?.webViewClient = WebViewClient(viewModel, (activity as MainActivity))
        webView?.settings?.domStorageEnabled = true
        webView?.settings?.databaseEnabled = true
//        webView?.settings?.setAppCacheEnabled(true) // Deprecated
        webView?.settings?.cacheMode = WebSettings.LOAD_DEFAULT
        webView?.settings?.loadsImagesAutomatically = true
        webView?.settings?.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        viewModel.showProgressBar(true)
        loadWebView(arguments?.getString(WEB_URL, "").toString())
    }

    private fun loadWebView(actionUrl: String) {
        getDataBinding().webView.loadUrl(actionUrl)
    }

    override fun getViewModelClass(): Class<ViewModelBase> = ViewModelBase::class.java

    override fun getViewModelIsSharedViewModel(): Boolean = false


    class WebViewClient(val viewmodel: ViewModelBase, val activity: MainActivity) :
        android.webkit.WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }

        @Deprecated("Deprecated in Java")
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            if (url?.startsWith("tel:", true) == true) {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse(url))
                activity.startActivity(intent)
                return true
            }
            view?.loadUrl(url!!)
            return true
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            viewmodel.showProgressBar(false)
        }
    }

}