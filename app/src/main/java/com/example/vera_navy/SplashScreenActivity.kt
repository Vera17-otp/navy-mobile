package com.example.vera_navy

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.vera_navy.tutorial.TutorialMessageActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            // KEMBALI KE AWAL: Selalu tampilkan onboarding (Tutorial) terlebih dahulu
            val intent = Intent(this, TutorialMessageActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}
