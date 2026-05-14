package com.example.vera_navy.pertemuan4 // Pastikan package ini sesuai dengan project kamu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vera_navy.databinding.ActivityTabungBinding // Sesuaikan dengan nama layout XML kamu (activity_tabung.xml)
import java.util.*

class TabungActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTabungBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inisialisasi View Binding
        binding = ActivityTabungBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Tombol Hitung Volume
        binding.btnHitungVolume.setOnClickListener {
            hitungVolumeTabung()
        }

        // 3. Tombol Kembali
        binding.btnBack.setOnClickListener {
            finish() // Menutup activity dan kembali ke Dashboard
        }
    }

    private fun hitungVolumeTabung() {
        val inputRadius = binding.etRadius.text.toString()
        val inputTinggi = binding.etTinggi.text.toString()

        // Validasi input agar tidak kosong
        if (inputRadius.isEmpty()) {
            binding.etRadius.error = "Jari-jari harus diisi"
            return
        }
        if (inputTinggi.isEmpty()) {
            binding.etTinggi.error = "Tinggi harus diisi"
            return
        }

        // Konversi string ke double
        val radius = inputRadius.toDouble()
        val tinggi = inputTinggi.toDouble()

        // Rumus Volume Tabung: PI * r^2 * t
        val volume = Math.PI * (radius * radius) * tinggi

        // Menampilkan hasil dengan format 2 angka di belakang koma
        binding.tvHasil.text = "Hasil Volume: %.2f".format(volume)
    }
}