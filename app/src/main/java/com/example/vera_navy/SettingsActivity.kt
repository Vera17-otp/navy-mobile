package com.example.vera_navy

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vera_navy.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tombol Kembali ke Home
        binding.btnBackSettings.setOnClickListener {
            finish()
        }

        // 1. Setup ListView dengan ArrayAdapter
        setupListView()

        // 2. Setup MaterialButton Listener
        binding.btnSendFeedback.setOnClickListener {
            val feedback = binding.etFeedback.text.toString()
            if (feedback.isNotEmpty()) {
                Toast.makeText(this, "Terima kasih atas masukan Anda!", Toast.LENGTH_SHORT).show()
                binding.etFeedback.text?.clear()
            } else {
                binding.tilFeedback.error = "Masukan tidak boleh kosong"
            }
        }

        // 3. Setup ChipGroup Listener
        binding.chipGroupThemes.setOnCheckedStateChangeListener { group, checkedIds ->
            if (checkedIds.isNotEmpty()) {
                Toast.makeText(this, "Tema berhasil diubah", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupListView() {
        val menuItems = arrayOf(
            "Kebijakan Privasi",
            "Syarat dan Ketentuan",
            "Pusat Bantuan",
            "Tentang Pengembang",
            "Versi Aplikasi v1.0.0"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            menuItems
        )

        binding.listViewSettings.adapter = adapter

        binding.listViewSettings.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = menuItems[position]
            Toast.makeText(this, "Membuka: $selectedItem", Toast.LENGTH_SHORT).show()
        }
    }
}