package com.example.vera_navy.Home.pertemuan2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vera_navy.databinding.ActivityPersegiPanjangBinding

class PersegiPanjangActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersegiPanjangBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersegiPanjangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHitungLuas.setOnClickListener {
            hitungLuas()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun hitungLuas() {
        val inputPanjang = binding.etPanjang.text.toString()
        val inputLebar = binding.etLebar.text.toString()

        if (inputPanjang.isEmpty()) {
            binding.etPanjang.error = "Panjang tidak boleh kosong"
            return
        }
        if (inputLebar.isEmpty()) {
            binding.etLebar.error = "Lebar tidak boleh kosong"
            return
        }

        val panjang = inputPanjang.toDouble()
        val lebar = inputLebar.toDouble()
        val luas = panjang * lebar

        binding.tvHasil.text = "Hasil Luas: $luas"
    }
}