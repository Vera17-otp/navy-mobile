package com.example.vera_navy

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vera_navy.Home.Pertemuan5.WebViewActivity
import com.example.vera_navy.Home.Pertemuan7.SeventhActivity
import com.example.vera_navy.Home.pertemuan2.PersegiPanjangActivity
import com.example.vera_navy.Home.pertemuan3.ThirdActivity
import com.example.vera_navy.Home.pertemuan4.DashboardActivity
import com.example.vera_navy.Home.pertemuan_10.TenthActivity
import com.example.vera_navy.Home.pertemuan_13.ThirteenthActivity
import com.example.vera_navy.api.ApiClient
import com.example.vera_navy.api.NewsAdapter
import com.example.vera_navy.databinding.FragmentHomeBinding
import com.example.vera_navy.model.NewsResponse
import com.example.vera_navy.utils.PermissionHelper
import com.example.vera_navy.utils.ReminderReceiver
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var newsAdapter: NewsAdapter

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(requireContext(), "Izin Notifikasi Diberikan", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Izin Notifikasi Ditolak", Toast.LENGTH_SHORT).show()
        }
    }

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

        setupRecyclerView()
        loadNewsData()
        setupClickListeners()
        checkNotificationPermission()
    }

    private fun setupClickListeners() {
        binding.btnPertemuan2.setOnClickListener {
            startActivity(Intent(requireActivity(), PersegiPanjangActivity::class.java))
        }

        binding.btnPertemuan3.setOnClickListener {
            startActivity(Intent(requireActivity(), ThirdActivity::class.java))
        }

        binding.btnPertemuan4.setOnClickListener {
            startActivity(Intent(requireActivity(), DashboardActivity::class.java))
        }

        binding.btnPertemuan5.setOnClickListener {
            startActivity(Intent(requireActivity(), WebViewActivity::class.java))
        }

        binding.btnPertemuan6.setOnClickListener {
            startActivity(Intent(requireActivity(), SeventhActivity::class.java))
        }

        binding.btnPertemuan10.setOnClickListener {
            startActivity(Intent(requireActivity(), TenthActivity::class.java))
        }

        binding.btnPertemuan13.setOnClickListener {
            startActivity(Intent(requireActivity(), ThirteenthActivity::class.java))
        }

        binding.btnSettings.setOnClickListener {
            startActivity(Intent(requireActivity(), SettingsActivity::class.java))
        }

        binding.btnSetReminder.setOnClickListener {
            val minutesStr = binding.etReminderMinutes.text.toString()
            if (minutesStr.isNotEmpty()) {
                val minutes = minutesStr.toInt()
                scheduleReminder(minutes)
            } else {
                Toast.makeText(requireContext(), "Masukkan jumlah menit", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkNotificationPermission() {
        if (PermissionHelper.isNotificationPermissionRequired()) {
            if (!PermissionHelper.hasPermission(requireContext(), Manifest.permission.POST_NOTIFICATIONS)) {
                PermissionHelper.requestPermission(requestPermissionLauncher, Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private fun scheduleReminder(minutes: Int) {
        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(requireContext(), ReminderReceiver::class.java).apply {
            putExtra("TITLE", "Pengingat Bina Desa")
            putExtra("MESSAGE", "Waktunya melakukan survei ke desa mitra!")
        }
        
        val pendingIntent = PendingIntent.getBroadcast(
            requireContext(),
            100,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val triggerTime = Calendar.getInstance().apply {
            add(Calendar.MINUTE, minutes)
        }.timeInMillis

        try {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                triggerTime,
                pendingIntent
            )
            Toast.makeText(requireContext(), "Reminder disetel dalam $minutes menit", Toast.LENGTH_SHORT).show()
        } catch (e: SecurityException) {
            // For Android 12+ if SCHEDULE_EXACT_ALARM is not granted
            alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                triggerTime,
                pendingIntent
            )
            Toast.makeText(requireContext(), "Reminder disetel dalam $minutes menit (Non-Exact)", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter(emptyList())
        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
            isNestedScrollingEnabled = false
        }
    }

    private fun loadNewsData() {
        binding.progressBar.visibility = View.VISIBLE
        ApiClient.instance.getTopHeadlines().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (_binding != null) {
                    binding.progressBar.visibility = View.GONE
                }
                if (response.isSuccessful) {
                    val articles = response.body()?.data ?: emptyList()
                    newsAdapter.updateData(articles)
                } else {
                    Log.e("API_ERROR", "Gagal Merespon Server: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                if (_binding != null) {
                    binding.progressBar.visibility = View.GONE
                }
                Log.e("API_ERROR", "Koneksi Gagal", t)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
