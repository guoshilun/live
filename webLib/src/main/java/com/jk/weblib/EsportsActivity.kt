package com.jk.weblib

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import com.just.agentweb.AgentWeb
import com.just.agentweb.WebChromeClient
import com.just.agentweb.WebViewClient
import kotlinx.android.synthetic.main.fragment_esport.*

/**
 *@author  fansan
 *@version 2020/11/5
 */

class EsportsActivity : Activity() {

    private var isFirstLoadPage = true
    private var webError = false
    private var type = 1
    private lateinit var mWebclient: WebViewClient
    lateinit var mAgentWeb: AgentWeb

    var mWebChromeClient = object : WebChromeClient() {

        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            if (newProgress >= 80) {
                loading.visibility = View.GONE
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_esport)

        mWebclient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                if (isFirstLoadPage) {
                    loading.visibility = View.VISIBLE
                    isFirstLoadPage = false
                }
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                //view?.loadUrl("javascript:${mViewModel.setJs()}")
                loading.visibility = View.GONE
                if (refreshLayout.isRefreshing) refreshLayout.isRefreshing = false
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?, request: WebResourceRequest?
            ): Boolean {
                return false
            }

            override fun onReceivedHttpError(
                view: WebView?, request: WebResourceRequest?, errorResponse: WebResourceResponse?
            ) {
                super.onReceivedHttpError(view, request, errorResponse)
                webError = true
            }

            override fun onReceivedError(
                view: WebView?, request: WebResourceRequest?, error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                webError = true
            }
        }

        type = intent.getIntExtra("type",1)
        loading.visibility = View.VISIBLE
        val sp = getSharedPreferences("esportsUrl", Context.MODE_PRIVATE)
        val url = sp?.getString(type.toString(), "")
        mAgentWeb = AgentWeb.with(this).setAgentWebParent(
            refreshLayout, ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
        ).useDefaultIndicator(Color.parseColor("#F891F0")).setWebViewClient(mWebclient)
            .setWebChromeClient(mWebChromeClient).interceptUnkownUrl().createAgentWeb().ready()
            .go(url)


        refreshLayout.setOnRefreshListener {
            mAgentWeb.urlLoader.reload()
        }

        refreshLayout.setOnChildScrollUpCallback { _, _ ->
            mAgentWeb.webCreator.webView.scrollY > 0
        }
    }

    override fun onBackPressed() {
        if (::mAgentWeb.isInitialized) {
            if (!mAgentWeb.back()) {
                super.onBackPressed()
            }
        }
    }

    override fun onPause() {
        if (::mAgentWeb.isInitialized) {
            mAgentWeb.webLifeCycle.onPause()
        }
        super.onPause()
    }

    override fun onResume() {
        if (::mAgentWeb.isInitialized) {
            mAgentWeb.webLifeCycle.onResume()
            if (!isFirstLoadPage && webError) {
                mAgentWeb.urlLoader.reload()
            }
        }
        super.onResume()
    }

    override fun onDestroy() {
        if (::mAgentWeb.isInitialized) {
            mAgentWeb.webLifeCycle.onDestroy()
        }
        super.onDestroy()

    }

    companion object {

        @JvmStatic
        fun start(c: Context, type: Int) {
            c.startActivity(Intent(c, EsportsActivity::class.java).putExtra("type", type))
        }
    }
}