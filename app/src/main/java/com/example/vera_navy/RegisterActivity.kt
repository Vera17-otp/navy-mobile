package com.example.vera_navy

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.example.vera_navy.databinding.ActivityRegisterBinding
import java.util.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupDatePicker()


        setupReligionSpinner()

        binding.btnRegister.setOnClickListener {
            validateAndRegister()
        }

        binding.tvBackToLogin.setOnClickListener {
            finish()
        }
    }

    private fun setupDatePicker() {
        val calendar = Calendar.getInstance()
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val myFormat = "dd/MM/yyyy"
            val sdf = java.text.SimpleDateFormat(myFormat, Locale.US)
            binding.etBirthDate.setText(sdf.format(calendar.time))
            binding.tilBirthDate.error = null
        }

        binding.etBirthDate.setOnClickListener {
            DatePickerDialog(
                this,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun setupReligionSpinner() {
        val religions = arrayOf("Islam", "Kristen Protestan", "Katolik", "Hindu", "Buddha", "Khonghucu")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, religions)
        binding.actvReligion.setAdapter(adapter)
        binding.actvReligion.setOnItemClickListener { _, _, _, _ ->
            binding.tilReligion.error = null
        }
    }

    private fun validateAndRegister() {
        // Reset Errors
        binding.tilName.error = null
        binding.tilBirthDate.error = null
        binding.tvGenderError.visibility = View.GONE
        binding.tilReligion.error = null
        binding.tilRegUsername.error = null
        binding.tilRegPassword.error = null
        binding.tilConfirmPassword.error = null

        val name = binding.etName.text.toString().trim()
        val birthDate = binding.etBirthDate.text.toString().trim()
        val username = binding.etRegUsername.text.toString().trim()
        val password = binding.etRegPassword.text.toString().trim()
        val confirmPassword = binding.etConfirmPassword.text.toString().trim()
        val religion = binding.actvReligion.text.toString().trim()
        val genderId = binding.rgGender.checkedRadioButtonId

        var isValid = true

        if (name.isEmpty()) {
            binding.tilName.error = "Nama tidak boleh kosong"
            isValid = false
        }

        if (birthDate.isEmpty()) {
            binding.tilBirthDate.error = "Tanggal lahir harus dipilih"
            isValid = false
        }

        if (genderId == -1) {
            binding.tvGenderError.text = "Pilih jenis kelamin"
            binding.tvGenderError.visibility = View.VISIBLE
            isValid = false
        }

        if (religion.isEmpty()) {
            binding.tilReligion.error = "Pilih agama"
            isValid = false
        }

        if (username.isEmpty()) {
            binding.tilRegUsername.error = "Username tidak boleh kosong"
            isValid = false
        } else if (username.length < 4) {
            binding.tilRegUsername.error = "Username minimal 4 karakter"
            isValid = false
        }

        if (password.isEmpty()) {
            binding.tilRegPassword.error = "Password tidak boleh kosong"
            isValid = false
        } else if (password.length < 6) {
            binding.tilRegPassword.error = "Password minimal 6 karakter"
            isValid = false
        }

        if (confirmPassword.isEmpty()) {
            binding.tilConfirmPassword.error = "Konfirmasi password tidak boleh kosong"
            isValid = false
        } else if (password != confirmPassword) {
            binding.tilConfirmPassword.error = "Password tidak cocok"
            isValid = false
        }

        if (isValid) {
            saveToSharedPref(name, birthDate, genderId, religion, username, password)
        }
    }

    private fun saveToSharedPref(name: String, birthDate: String, genderId: Int, religion: String, user: String, pass: String) {
        val gender = findViewById<RadioButton>(genderId).text.toString()
        val sharedPref = getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        
        editor.putString("reg_name", name)
        editor.putString("reg_birthdate", birthDate)
        editor.putString("reg_gender", gender)
        editor.putString("reg_religion", religion)
        editor.putString("reg_username", user)
        editor.putString("reg_password", pass)
        // Set flag registered agar AuthActivity tahu ada data
        editor.putBoolean("is_registered", true)
        
        editor.apply()

        // Kirim pesan sukses lewat intent atau cukup finish
        // Karena dilarang pakai Toast/Dialog untuk validasi, 
        // kita langsung finish saja setelah berhasil.
        finish()
    }
}