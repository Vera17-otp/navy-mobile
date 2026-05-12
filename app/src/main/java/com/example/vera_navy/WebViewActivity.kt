package com.example.vera_navy

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.vera_navy.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbarWebView)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbarWebView.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Setup WebView
        binding.webView.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl("https://id.wikipedia.org/wiki/Bina_Desa") // URL Bina Desa (Contoh)
        }
    }
}