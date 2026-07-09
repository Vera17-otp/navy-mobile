package com.example.vera_navy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.vera_navy.About.AboutFragment
import com.example.vera_navy.Message.MessageFragment
import com.example.vera_navy.Note.NoteFragment
import com.example.vera_navy.Profile.ProfileFragment
import com.example.vera_navy.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sembunyikan action bar bawaan agar tampilan kustom kita rapi
        supportActionBar?.hide()

        // Handle fragment yang harus dibuka (misal dari notifikasi)
        val openFragment = intent.getStringExtra("OPEN_FRAGMENT")
        if (openFragment == "NOTE") {
            replaceFragment(NoteFragment())
            binding.bottomNavigation.selectedItemId = R.id.note
        } else if (savedInstanceState == null) {
            // Default awal
            replaceFragment(HomeFragment())
        }

        // MATERI PERTEMUAN 6: Fitur Logout melalui btnLogout
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { _, _ ->
                    // Hapus data status login di SharedPreferences
                    val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    editor.remove("isLogin")
                    editor.remove("username")
                    editor.apply()

                    // Kembali ke halaman Login
                    val intent = Intent(this, AuthActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Tidak", null)
                .show()
        }

        // Mengatur aksi klik navigasi bawah yang berlaku secara global
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.note -> {
                    replaceFragment(NoteFragment())
                    true
                }
                R.id.message -> {
                    replaceFragment(MessageFragment())
                    true
                }
                R.id.about -> {
                    replaceFragment(AboutFragment())
                    true
                }
                R.id.profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    // Fungsi sakti untuk menukar fragment di dalam FrameLayout tanpa merusak Bottom Navigation
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
