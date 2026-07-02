package com.example.vera_navy.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.vera_navy.BaseActivity

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val title = intent.getStringExtra("TITLE") ?: "Pengingat Bina Desa"
        val message = intent.getStringExtra("MESSAGE") ?: "Waktunya melakukan kegiatan bina desa!"
        
        // Intent to open when notification is clicked
        val notificationIntent = Intent(context, BaseActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        
        NotificationHelper.showNotification(
            context,
            title,
            message,
            notificationIntent
        )
    }
}
