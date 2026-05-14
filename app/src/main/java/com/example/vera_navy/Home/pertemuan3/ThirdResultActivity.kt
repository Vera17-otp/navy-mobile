package com.example.vera_navy.Home.pertemuan3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vera_navy.R
import com.example.vera_navy.databinding.ActivityThirdResultBinding

class ThirdResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityThirdResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ========== MENERIMA DATA DARI ThirdActivity ==========
        val passengerName = intent.getStringExtra("PASSENGER_NAME") ?: "Budi Santoso"
        val pickupLocation = intent.getStringExtra("PICKUP_LOCATION") ?: "Soekarno-Hatta Airport"
        val destination = intent.getStringExtra("DESTINATION") ?: "Grand Hotel"

        // ========== MENAMPILKAN DATA KE UI ==========

        // 1. Menampilkan nama penumpang ke tvPassengerName (ID sudah ada)
        binding.tvPassengerName.text = passengerName

        // 2. Menampilkan lokasi pickup ke Card Pickup (mengakses child view)
        val pickupCard = binding.tripInfoLayout.getChildAt(0) as CardView
        val pickupLinearLayout = pickupCard.getChildAt(0) as LinearLayout

        // Update teks airport name (TextView ke-4 di LinearLayout, index 3)
        val airportNameTv = pickupLinearLayout.getChildAt(3) as TextView
        airportNameTv.text = pickupLocation

        // 3. Menampilkan lokasi destination ke Card Destination
        val destCard = binding.tripInfoLayout.getChildAt(1) as CardView
        val destLinearLayout = destCard.getChildAt(0) as LinearLayout

        // Update teks hotel name (TextView ke-3 di LinearLayout, index 2)
        val hotelNameTv = destLinearLayout.getChildAt(2) as TextView
        hotelNameTv.text = destination

        // ========== MENAMPILKAN TOAST KONFIRMASI ==========
        Toast.makeText(this, "Booking confirmed for $passengerName!", Toast.LENGTH_SHORT).show()

        // ========== IMPLEMENTASI TOMBOL SHARE ==========
        // Mencari tombol Share di dalam Status Card (CardView terakhir)
        // Cara: cari semua Button yang ada di layout
        val shareButton = findShareButton()
        shareButton?.setOnClickListener {
            val shareMessage = """
                ✈️ AIRPORT TRANSFER BOOKING CONFIRMED ✈️
                
                Passenger: $passengerName
                Pickup: $pickupLocation
                Destination: $destination
                Vehicle: Toyota Alphard (Premium)
                Date: Monday, 16 April 2026 • 14:30
                Driver: Ahmad Supriadi ★ 4.9
                Total Price: Rp 350,000
                Status: CONFIRMED
                Booking ID: TRF-2412-001
                
                Thank you for using Airport Transfer!
            """.trimIndent()

            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, shareMessage)
            }
            startActivity(Intent.createChooser(shareIntent, "Share Booking Confirmation"))
        }
    }

    private fun findShareButton(): Button? {
        // Mencari tombol dengan teks "Share" di seluruh layout
        val rootView = binding.root
        return findButtonRecursively(rootView, "Share")
    }

    private fun findButtonRecursively(view: View, text: String): Button? {
        if (view is Button && view.text.toString().equals(text, ignoreCase = true)) {
            return view
        }
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val result = findButtonRecursively(view.getChildAt(i), text)
                if (result != null) return result
            }
        }
        return null
    }
}