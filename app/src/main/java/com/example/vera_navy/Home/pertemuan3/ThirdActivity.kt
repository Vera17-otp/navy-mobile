package com.example.vera_navy.Home.pertemuan3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.vera_navy.databinding.ActivityThirdBinding
import com.example.vera_navy.Home.pertemuan4.DashboardActivity

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inisialisasi View Binding
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Fungsi Tombol Back
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.buttonSubmit.setOnClickListener {
            // ========== MENGAMBIL DATA DARI FORM AIRPORT TRANSFER ==========
            val passengerName = binding.inputNama.text.toString()
            val pickupLocation = binding.inputPickup.text.toString()
            val destination = binding.inputDestination.text.toString()

            // ========== VALIDASI: PASTIKAN SEMUA FIELD TERISI ==========
            if (passengerName.isNotEmpty() && pickupLocation.isNotEmpty() && destination.isNotEmpty()) {
                // Menampilkan notifikasi sukses
                Toast.makeText(this, "Booking confirmed for $passengerName!", Toast.LENGTH_SHORT).show()

                // Berpindah ke halaman konfirmasi booking (ThirdResultActivity)
                val intent = Intent(this, DashboardActivity::class.java)

                // ========== MENGIRIM DATA KE HALAMAN BERIKUTNYA ==========
                intent.putExtra("PASSENGER_NAME", passengerName)      // Mengirim nama penumpang
                intent.putExtra("PICKUP_LOCATION", pickupLocation)    // Mengirim lokasi penjemputan
                intent.putExtra("DESTINATION", destination)           // Mengirim tujuan

                startActivity(intent)
            } else {
                // ========== VALIDASI GAGAL: ADA FIELD YANG KOSONG ==========
                Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}