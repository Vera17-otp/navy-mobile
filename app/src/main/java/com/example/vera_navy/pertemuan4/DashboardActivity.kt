package com.example.vera_navy.pertemuan4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vera_navy.databinding.ActivityDashboardBinding
// Pastikan import di bawah ini sesuai dengan lokasi folder file kamu
import com.example.vera_navy.pertemuan4.TabungActivity
import com.example.vera_navy.pertemuan4.PersegiPanjangActivity
import com.example.vera_navy.pertemuan4.Custom1Activity
import com.example.vera_navy.pertemuan4.Custom2Activity
import com.example.vera_navy.pertemuan3.ThirdActivity // Ini untuk Login/Logout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Klik untuk halaman Tabung
        binding.cardTabung.setOnClickListener {
            val intent = Intent(this, TabungActivity::class.java)
            startActivity(intent)
        }

        // 2. Klik untuk halaman Persegi Panjang
        binding.cardPersegi.setOnClickListener {
            val intent = Intent(this, PersegiPanjangActivity::class.java)
            startActivity(intent)
        }

        // 3. Klik Custom 1 (Airport Transfer / Katalog Armada)
        binding.cardCustom1.setOnClickListener {
            val intent = Intent(this, Custom1Activity::class.java)
            intent.putExtra("EXTRA_TITLE", "Premium Fleet")
            intent.putExtra("EXTRA_DESC", "Pilihan armada eksekutif untuk kenyamanan Anda.")
            startActivity(intent)
        }

        // 4. Klik Custom 2 (Tracking Driver)
        binding.cardCustom2.setOnClickListener {
            val intent = Intent(this, Custom2Activity::class.java)
            intent.putExtra("EXTRA_TITLE", "Airport Live Tracking")
            intent.putExtra("EXTRA_DESC", "Driver sedang dalam perjalanan menuju lokasi Anda.")
            startActivity(intent)
        }

        // 5. Tombol Logout dengan konfirmasi
        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { _, _ ->
                    // Kembali ke halaman Login (ThirdActivity)
                    val intent = Intent(this, ThirdActivity::class.java)
                    startActivity(intent)
                    finishAffinity() // Menghapus tumpukan activity agar tidak bisa back
                }
                .setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                    Snackbar.make(binding.root, "Logout dibatalkan", Snackbar.LENGTH_SHORT).show()
                }
                .show()
        }
    }
}