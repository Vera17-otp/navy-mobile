package com.example.vera_navy

import android.content.Context
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
            val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
            val isLogin = sharedPref.getBoolean("isLogin", false)
            val isOnboardingFinished = sharedPref.getBoolean("onboarding_finished", false)

            if (isLogin) {
                startActivity(Intent(this, BaseActivity::class.java))
            } else if (!isOnboardingFinished) {
                startActivity(Intent(this, TutorialMessageActivity::class.java))
            } else {
                startActivity(Intent(this, AuthActivity::class.java))
            }
            finish()
        }, 2000)
    }
}