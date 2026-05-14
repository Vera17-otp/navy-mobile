package com.example.vera_navy.Home.Pertemuan7

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.vera_navy.About.AboutFragment
import com.example.vera_navy.HomeFragment
import com.example.vera_navy.Profile.ProfileFragment
import com.example.vera_navy.R
import com.example.vera_navy.databinding.ActivitySeventhBinding

class SeventhActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySeventhBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeventhBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }

        // Kontrol Bottom Bar / Tab Atas untuk menukar Fragment utama
        binding.btnFrag1.setOnClickListener { replaceFragment(HomeFragment()) }
        binding.btnFrag2.setOnClickListener { replaceFragment(AboutFragment()) }
        binding.btnFrag3.setOnClickListener { replaceFragment(ProfileFragment()) }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}