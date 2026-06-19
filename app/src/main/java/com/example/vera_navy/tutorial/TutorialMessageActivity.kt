package com.example.vera_navy.tutorial

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.vera_navy.AuthActivity
import com.example.vera_navy.databinding.ActivityTutorialMessageBinding

class TutorialMessageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTutorialMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = TutorialFragmentAdapter(this)
        binding.viewPager.adapter = adapter

        binding.dotsIndicator.attachTo(binding.viewPager)

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == adapter.itemCount - 1) {
                    binding.btnStart.visibility = View.VISIBLE
                    binding.dotsIndicator.visibility = View.GONE
                } else {
                    binding.btnStart.visibility = View.GONE
                    binding.dotsIndicator.visibility = View.VISIBLE
                }
            }
        })

        binding.btnStart.setOnClickListener {
            // Save that onboarding is finished
            val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            sharedPref.edit().putBoolean("onboarding_finished", true).apply()

            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }
    }
}