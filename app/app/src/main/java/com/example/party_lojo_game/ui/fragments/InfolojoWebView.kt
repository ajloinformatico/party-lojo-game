package com.example.party_lojo_game.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.party_lojo_game.R
import com.example.party_lojo_game.data.constants.Constants

class InfolojoWebView : Fragment() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_infolojo_web_view, container, false).apply {
        (findViewById(R.id.infolojoWebViewContainer) as? WebView)?.let { webView ->
            webView.settings.javaScriptEnabled = true
            webView.webViewClient = WebViewClient()
            webView.loadUrl(Constants.INFOLOJO_WEB_SITE)
        }
    }
}
