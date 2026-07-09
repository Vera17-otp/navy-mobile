package com.example.vera_navy.Note

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.vera_navy.BaseActivity
import com.example.vera_navy.Data.AppDatabase
import com.example.vera_navy.Data.entity.NoteEntity
import com.example.vera_navy.databinding.ActivityNoteFormBinding
import com.example.vera_navy.utils.NotificationHelper
import com.example.vera_navy.utils.PermissionHelper
import kotlinx.coroutines.launch

class NoteFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteFormBinding
    private lateinit var db: AppDatabase

    // Launcher untuk meminta izin notifikasi jika belum diberikan
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(this, "Izin Notifikasi Diberikan", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Izin Notifikasi Ditolak, notifikasi tidak akan muncul", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }

        db = AppDatabase.getInstance(this)

        // Cek izin notifikasi saat halaman form dibuka
        checkNotificationPermission()

        binding.btnSaveNote.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            val content = binding.etContent.text.toString().trim()

            if (title.isNotBlank() && content.isNotBlank()) {
                lifecycleScope.launch {
                    val note = NoteEntity(
                        title = title,
                        content = content,
                        createdAt = System.currentTimeMillis()
                    )
                    db.noteDao().insert(note)

                    val intent = Intent(this@NoteFormActivity, BaseActivity::class.java).apply {
                        putExtra("OPEN_FRAGMENT", "NOTE")
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }

                    // Hanya kirim notifikasi jika izin sudah diberikan
                    if (PermissionHelper.hasPermission(this@NoteFormActivity, Manifest.permission.POST_NOTIFICATIONS)) {
                        NotificationHelper.showNotification(
                            this@NoteFormActivity,
                            "Catatan Berhasil Disimpan",
                            "Halo, catatan '$title' telah berhasil disimpan.",
                            intent
                        )
                    }

                    Toast.makeText(this@NoteFormActivity, "Catatan Berhasil Disimpan", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } else {
                Toast.makeText(this, "Isi semua kolom!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkNotificationPermission() {
        if (PermissionHelper.isNotificationPermissionRequired()) {
            if (!PermissionHelper.hasPermission(this, Manifest.permission.POST_NOTIFICATIONS)) {
                PermissionHelper.requestPermission(requestPermissionLauncher, Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
}
