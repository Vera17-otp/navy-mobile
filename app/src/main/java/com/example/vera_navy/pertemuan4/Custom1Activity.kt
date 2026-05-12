package com.example.vera_navy.pertemuan4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vera_navy.databinding.ActivityCustom1Binding

class Custom1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityCustom1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCustom1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "" // Judul bisa dikosongkan jika menggunakan CollapsingToolbarLayout

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val judul = intent.getStringExtra("EXTRA_TITLE")
        val deskripsi = intent.getStringExtra("EXTRA_DESC")

        if (judul != null) {
            binding.tvTitleTransfer.text = judul
        }

        if (deskripsi != null) {
            binding.tvDescTransfer.text = deskripsi
        }
    }
}