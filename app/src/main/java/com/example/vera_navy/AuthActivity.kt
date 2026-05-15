package com.example.vera_navy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vera_navy.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            // Reset Errors
            binding.tilUsername.error = null
            binding.tilPassword.error = null

            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            // Ambil data dari SharedPreferences hasil registrasi
            val savedUsername = sharedPref.getString("reg_username", "")
            val savedPassword = sharedPref.getString("reg_password", "")

            if (username.isEmpty()) {
                binding.tilUsername.error = "Username tidak boleh kosong"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.tilPassword.error = "Password tidak boleh kosong"
                return@setOnClickListener
            }

            // Rule 1: username == password
            // Rule 2: username & password sesuai data SharedPreferences
            val isLoginSuccess = (username == password) || 
                                 (username == savedUsername && password == savedPassword && savedUsername != "")

            if (isLoginSuccess) {
                // Simpan status login
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.apply()

                // Pindah ke BaseActivity
                val intent = Intent(this, BaseActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Tampilkan error pada field input sesuai permintaan (bukan Dialog)
                binding.tilUsername.error = "Username atau Password salah"
                binding.tilPassword.error = "Username atau Password salah"
            }
        }

        binding.tvToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}