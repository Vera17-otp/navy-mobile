package com.example.vera_navy.Home.Pertemuan5

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Bundle
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
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
        supportActionBar?.apply {
            title = "Bina Desa Pertanahan"
            setDisplayHomeAsUpEnabled(true)
        }

        binding.toolbarWebView.setNavigationOnClickListener {
            handleBackNavigation()
        }

        setupWebView()

        // Handle tombol back fisik/gesture
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                handleBackNavigation()
            }
        })
    }

    private fun setupWebView() {
        binding.webView.apply {
            // 1. WebViewClient untuk menangani navigasi dan error
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    // Tampilkan progress bar saat loading (jika ada di XML kamu)
                    // binding.progressBar.visibility = View.VISIBLE
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    // Sembunyikan progress bar
                    // binding.progressBar.visibility = View.GONE
                }

                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    // Jika terjadi error, kita beri tahu user atau tampilkan halaman custom
                    Toast.makeText(this@WebViewActivity, "Gagal memuat halaman: ${error?.description}", Toast.LENGTH_SHORT).show()
                    super.onReceivedError(view, request, error)
                }

                override fun onReceivedSslError(
                    view: WebView?,
                    handler: SslErrorHandler?,
                    error: SslError?
                ) {
                    // Paksa lanjut jika ada masalah sertifikat SSL (berguna untuk testing emulator)
                    handler?.proceed()
                }
            }

            // 2. WebChromeClient untuk fitur browser seperti progress bar dan alert javascript
            webChromeClient = WebChromeClient()

            // 3. Pengaturan WebSettings yang optimal
            settings.apply {
                javaScriptEnabled = true            // Aktifkan JS
                domStorageEnabled = true           // WAJIB untuk web modern/login
                databaseEnabled = true
                allowContentAccess = true
                allowFileAccess = true

                // Pengaturan tampilan
                useWideViewPort = true
                loadWithOverviewMode = true
                setSupportZoom(true)
                builtInZoomControls = true
                displayZoomControls = false

                // Cache & Mixed Content
                cacheMode = WebSettings.LOAD_DEFAULT
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW

                // User Agent agar terdeteksi sebagai Chrome Mobile asli
                userAgentString = "Mozilla/5.0 (Linux; Android 14) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.6099.210 Mobile Safari/537.36"
            }

            // 4. Load URL
            loadUrl("https://zakiavr.alwaysdata.net/login")
        }
    }

    private fun handleBackNavigation() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        handleBackNavigation()
        return true
    }

    // Pastikan WebView dihancurkan saat activity selesai untuk mencegah memory leak
    override fun onDestroy() {
        binding.webView.stopLoading()
        binding.webView.destroy()
        super.onDestroy()
    }
}