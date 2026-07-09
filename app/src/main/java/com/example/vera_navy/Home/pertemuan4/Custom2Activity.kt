package com.example.vera_navy.Home.pertemuan4

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vera_navy.databinding.ActivityCustom2Binding

class Custom2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityCustom2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCustom2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tombol Kembali
        binding.btnBack.setOnClickListener {
            finish()
        }

        val judul = intent.getStringExtra("EXTRA_TITLE") ?: "Status Perjalanan"
        val deskripsi = intent.getStringExtra("EXTRA_DESC") ?: "Sedang memuat data..."

        binding.tvTitleStatus.text = judul
        binding.tvDescStatus.text = deskripsi

        binding.btnContact.setOnClickListener {
            Toast.makeText(this, "Menghubungi Driver...", Toast.LENGTH_SHORT).show()
        }
    }
}