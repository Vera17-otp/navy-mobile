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
import com.example.vera_navy.Home.pertemuan2.PersegiPanjangActivity
import com.example.vera_navy.Home.pertemuan3.ThirdActivity
import com.example.vera_navy.Home.pertemuan4.DashboardActivity
import com.example.vera_navy.Home.pertemuan_10.TenthActivity
import com.example.vera_navy.databinding.FragmentHomeBinding

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

        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Home"
        }

        val sharedPref = requireActivity().getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "User")
        binding.tvWelcomeHome.text = "Selamat Datang, $username!"

        binding.btnPertemuan2.setOnClickListener {
            val intent = Intent(requireActivity(), PersegiPanjangActivity::class.java)
            startActivity(intent)
        }

        binding.btnPertemuan3.setOnClickListener {
            val intent = Intent(requireActivity(), ThirdActivity::class.java)
            startActivity(intent)
        }

        binding.btnPertemuan4.setOnClickListener {
            val intent = Intent(requireActivity(), DashboardActivity::class.java)
            startActivity(intent)
        }

        binding.btnPertemuan5.setOnClickListener {
            val intent = Intent(requireActivity(), WebViewActivity::class.java)
            startActivity(intent)
        }

        binding.btnPertemuan6.setOnClickListener {
            val intent = Intent(requireActivity(), SeventhActivity::class.java)
             startActivity(intent)
        }

        binding.btnPertemuan10.setOnClickListener {
            val intent = Intent(requireActivity(), TenthActivity::class.java)
            startActivity(intent)
        }

        // Fitur Baru: Navigasi ke SettingsActivity
        binding.btnSettings.setOnClickListener {
            val intent = Intent(requireActivity(), SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}