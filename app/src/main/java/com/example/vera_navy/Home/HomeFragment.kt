package com.example.vera_navy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.vera_navy.Home.Pertemuan5.WebViewActivity
import com.example.vera_navy.Home.Pertemuan7.SeventhActivity
import com.example.vera_navy.Home.pertemuan3.ThirdActivity
import com.example.vera_navy.Home.pertemuan4.DashboardActivity
import com.example.vera_navy.databinding.FragmentHomeBinding
import com.example.vera_navy.pertemuan4.PersegiPanjangActivity

// SILAHKAN IMPORT ACTIVITY PERTEMUAN LAMA KAMU DI SINI JIKA BERBEDA PACKAGE
// Contoh:
// import com.example.vera_navy.Home.pertemuan2.Pertemuan2Activity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mengatur judul Toolbar melalui Activity induk jika diperlukan (Sesuai image_9462d1.png)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Home"
        }

        // Tampilkan nama user dari SharedPreferences ke dashboard
        val sharedPref = requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "User")
        binding.tvWelcomeHome.text = "Selamat Datang, $username!"

        // --- MASUKKAN LOGIKA NAVIGASI BUTTON DI SINI ---

        // 1. Tombol Pertemuan 2
        binding.btnPertemuan2.setOnClickListener {
            // Ubah 'Pertemuan2Activity' sesuai nama file asli di folder pertemuan2 kamu
            val intent = Intent(requireActivity(), PersegiPanjangActivity::class.java)
            startActivity(intent)
        }

        // 2. Tombol Pertemuan 3
        binding.btnPertemuan3.setOnClickListener {
            // Ubah 'Pertemuan3Activity' sesuai nama file asli di folder pertemuan3 kamu
            val intent = Intent(requireActivity(), ThirdActivity::class.java)
            startActivity(intent)
        }

        // 3. Tombol Pertemuan 4
        binding.btnPertemuan4.setOnClickListener {
            // Ubah 'Pertemuan4Activity' sesuai nama file asli di folder pertemuan4 kamu
            val intent = Intent(requireActivity(), DashboardActivity::class.java)
            startActivity(intent)
        }

        // 4. Tombol Pertemuan 5 (Membuka WebViewActivity)
        binding.btnPertemuan5.setOnClickListener {
            val intent = Intent(requireActivity(), WebViewActivity::class.java)
            startActivity(intent)
        }

        // 5. Tombol Pertemuan 6
        binding.btnPertemuan6.setOnClickListener {
            // Ubah sesuai kebutuhan tugas Pertemuan 6 kamu
            val intent = Intent(requireActivity(), SeventhActivity::class.java)
             startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Bersihkan binding untuk menghindari memory leak
        _binding = null
    }
}