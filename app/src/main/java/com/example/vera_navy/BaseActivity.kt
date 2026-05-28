package com.example.vera_navy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.vera_navy.About.AboutFragment
import com.example.vera_navy.Message.MessageFragment
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

        // Munculkan HomeFragment secara default saat pertama kali masuk BaseActivity
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        // Mengatur aksi klik navigasi bawah yang berlaku secara global
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
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