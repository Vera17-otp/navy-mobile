package com.example.vera_navy.pertemuan4 // Sesuaikan package kamu

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vera_navy.databinding.ActivityCustom2Binding

class Custom2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityCustom2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inisialisasi Binding
        binding = ActivityCustom2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Ambil data dari Intent (Dashboard)
        val judul = intent.getStringExtra("EXTRA_TITLE") ?: "Status Perjalanan"
        val deskripsi = intent.getStringExtra("EXTRA_DESC") ?: "Sedang memuat data..."

        // 3. Set Teks (Pastikan ID di XML adalah tvTitleStatus dan tvDescStatus)
        binding.tvTitleStatus.text = judul
        binding.tvDescStatus.text = deskripsi

        // 4. Logika untuk tombol (Gunakan ID MaterialButton di XML kamu)
        // Jika di XML belum ada ID, tambahkan android:id="@+id/btnContact"
        /*
        binding.btnContact.setOnClickListener {
            Toast.makeText(this, "Menghubungi Driver...", Toast.LENGTH_SHORT).show()
        }
        */
    }
}