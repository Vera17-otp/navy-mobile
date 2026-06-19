package com.example.vera_navy.tutorial

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TutorialFragmentAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf(
        TutorialFragment.newInstance(
            "Selamat Datang di Fitur Pesan!",
            "Dengan fitur ini, kamu bisa melihat daftar pesan masuk dari pengguna lain. Semua pesan ditampilkan lengkap dengan nama pengirim, avatar, dan isi pesan",
            "#64B5F6"
        ),
        TutorialFragment.newInstance(
            "Tap untuk Melihat Detail!",
            "Ketuk salah satu pesan untuk melihat detailnya. Kamu juga bisa menambahkan aksi lain seperti membalas, menyimpan, atau membagikan pesan tersebut",
            "#81C784"
        ),
        TutorialFragment.newInstance(
            "Mulai Gunakan Sekarang!",
            "Jelajahi fitur pesan dan temukan informasi penting dari komunitas pengguna. Fitur ini dirancang simpel, cepat, dan mudah digunakan",
            "#4DB6AC"
        )
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}