package com.dkgtech.webview

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.dkgtech.webview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener {
            val searchQuery = binding.edtSearch.text.toString()
            val googleSearchUrl = "https://www.google.com/search?q=$searchQuery"
            val yahooSearchUrl = "https://in.search.yahoo.com/search;?p=$searchQuery"
            binding.webView.loadUrl(yahooSearchUrl)
        }


//        load URL on webView
//        binding.webView.loadUrl("https://www.google.com")
//        load multiple webpages and manage web stacks
        binding.webView.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                binding.pgBar.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.pgBar.visibility = View.GONE
            }


        }
//        to enable javaScript
        binding.webView.settings.javaScriptEnabled = true
//        to zoom in webpages
        binding.webView.settings.setSupportZoom(true)


    }


    //    to pop out webpages from pressing back
    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            super.getOnBackPressedDispatcher().onBackPressed()
        }

    }
}