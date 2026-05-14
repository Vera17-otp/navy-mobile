package com.example.vera_navy.pertemuan4 // Sesuaikan dengan package kamu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vera_navy.databinding.ActivityPersegiPanjangBinding // Pastikan nama layout XML-mu activity_persegi_panjang.xml

class PersegiPanjangActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersegiPanjangBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inisialisasi View Binding
        binding = ActivityPersegiPanjangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Logika Tombol Hitung
        binding.btnHitungLuas.setOnClickListener {
            hitungLuas()
        }

        // 3. Logika Tombol Kembali (Navigasi)
        binding.btnBack.setOnClickListener {
            finish() // Menutup activity ini dan kembali ke Dashboard
        }
    }

    private fun hitungLuas() {
        // Mengambil input dari EditText
        val inputPanjang = binding.etPanjang.text.toString()
        val inputLebar = binding.etLebar.text.toString()

        // Validasi jika input kosong
        if (inputPanjang.isEmpty()) {
            binding.etPanjang.error = "Panjang tidak boleh kosong"
            return
        }
        if (inputLebar.isEmpty()) {
            binding.etLebar.error = "Lebar tidak boleh kosong"
            return
        }

        // Konversi string ke double untuk perhitungan
        val panjang = inputPanjang.toDouble()
        val lebar = inputLebar.toDouble()

        // Rumus Luas: Panjang x Lebar
        val luas = panjang * lebar

        // Menampilkan hasil ke TextView
        binding.tvHasil.text = "Hasil Luas: $luas"
    }
}