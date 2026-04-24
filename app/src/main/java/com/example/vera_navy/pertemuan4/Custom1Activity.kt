package com.example.vera_navy.pertemuan4 // Sesuaikan dengan package project kamu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vera_navy.databinding.ActivityCustom1Binding

class Custom1Activity : AppCompatActivity() {

    // Inisialisasi View Binding
    private lateinit var binding: ActivityCustom1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Inflate layout menggunakan View Binding
        binding = ActivityCustom1Binding.inflate(layoutInflater)
        setContentView(binding.root)



        // Menerima data Intent dari DashboardActivity
        // "EXTRA_TITLE" dan "EXTRA_DESC" adalah kunci (key) yang dikirim dari Dashboard
        val judul = intent.getStringExtra("EXTRA_TITLE")
        val deskripsi = intent.getStringExtra("EXTRA_DESC")

        // Memasukkan data ke dalam TextView yang ada di XML
        // tvTitleTransfer dan tvDescTransfer adalah ID di XML kamu
        if (judul != null) {
            binding.tvTitleTransfer.text = judul
        }

        if (deskripsi != null) {
            binding.tvDescTransfer.text = deskripsi
        }

        // Opsional: Jika kamu ingin menambahkan fungsi klik pada kartu armada
        // binding.cardArmada.setOnClickListener {
        //    Toast.makeText(this, "Armada dipilih", Toast.LENGTH_SHORT).show()
        // }
    }
}