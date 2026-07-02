package com.example.vera_navy.Note

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.vera_navy.BaseActivity
import com.example.vera_navy.databinding.FragmentNoteBinding
import com.example.vera_navy.utils.NotificationHelper
import com.example.vera_navy.utils.PermissionHelper

class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private val notificationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(requireContext(), "Izin notifikasi diberikan", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Izin notifikasi ditolak", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkNotificationPermission()

        binding.btnSaveNote.setOnClickListener {
            val noteText = binding.etNote.text.toString()
            val intent = Intent(requireContext(), BaseActivity::class.java)

            if (noteText.isNotEmpty()) {
                // Menggunakan NotificationHelper sesuai materi dosen
                NotificationHelper.showNotification(
                    requireContext(),
                    "Catatan Bina Desa",
                    "Halo, catatan '$noteText' telah berhasil disimpan",
                    intent
                )
                
                Toast.makeText(requireContext(), "Catatan berhasil disimpan!", Toast.LENGTH_SHORT).show()
                binding.etNote.text?.clear()
            } else {
                Toast.makeText(requireContext(), "Catatan tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkNotificationPermission() {
        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(requireContext(), permission)) {
                PermissionHelper.requestPermission(
                    notificationPermissionLauncher,
                    permission
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
