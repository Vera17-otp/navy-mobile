package com.example.vera_navy.Home.pertemuan2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vera_navy.databinding.ActivityTabungBinding

class TabungActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTabungBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabungBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHitungVolume.setOnClickListener {
            hitungVolumeTabung()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun hitungVolumeTabung() {
        val inputRadius = binding.etRadius.text.toString()
        val inputTinggi = binding.etTinggi.text.toString()

        if (inputRadius.isEmpty()) {
            binding.etRadius.error = "Jari-jari harus diisi"
            return
        }
        if (inputTinggi.isEmpty()) {
            binding.etTinggi.error = "Tinggi harus diisi"
            return
        }

        val radius = inputRadius.toDouble()
        val tinggi = inputTinggi.toDouble()

        val volume = Math.PI * (radius * radius) * tinggi

        binding.tvHasil.text = "Hasil Volume: %.2f".format(volume)
    }
}