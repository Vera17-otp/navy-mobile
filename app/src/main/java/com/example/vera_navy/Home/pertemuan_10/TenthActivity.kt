package com.example.vera_navy.Home.pertemuan_10

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.vera_navy.R
import com.example.vera_navy.databinding.ActivityTenthBinding
import com.google.android.material.tabs.TabLayoutMediator

class TenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        // 1. Inisialisasi Adapter
        val tabsAdapter = TenthTabsAdapter(this)

        // 2. Set adapter ke ViewPager2
        binding.viewPager.adapter = tabsAdapter

        // 3. Hubungkan TabLayout & ViewPager2 menggunakan Adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            // Atur judul, icon, dan badge untuk setiap tab
            when (position) {
                0 -> {
                    tab.text = "Layanan"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_home)
                    // Hapus badge jika tidak diperlukan untuk tab pertama
                }
                1 -> {
                    tab.text = "Berkas"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_note)
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 2
                }
                2 -> {
                    tab.text = "Info"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_notification)
                }
            }
        }.attach()
    }
}