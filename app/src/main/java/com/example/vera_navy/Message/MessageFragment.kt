package com.example.vera_navy.Message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.vera_navy.Message.MessageAdapter
import com.example.vera_navy.Message.MessageModel
import com.example.vera_navy.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Langkah 5: Definisikan list data message dengan URL Avatar yang Realistis
        val listData = listOf(
            MessageModel("Alya", "Halo! Apa kabar?", "https://i.pravatar.cc/150?u=alya"),
            MessageModel("Budi", "Sudah makan?", "https://i.pravatar.cc/150?u=budi"),
            MessageModel("Citra", "Jangan lupa tugasnya ya!", "https://i.pravatar.cc/150?u=citra"),
            MessageModel("Dika", "Besok kita rapat jam 9", "https://i.pravatar.cc/150?u=dika"),
            MessageModel("Eka", "Nice job kemarin!", "https://i.pravatar.cc/150?u=eka"),
            MessageModel("Fajar", "Data sudah saya kirim", "https://i.pravatar.cc/150?u=fajar")
        )

        // Langkah 7: Terapkan MessageAdapter
        val adapter = MessageAdapter(requireContext(), listData)
        binding.lvMessages.adapter = adapter

        // Langkah 8: Terapkan OnClick pada setiap item
        binding.lvMessages.setOnItemClickListener { _, _, position, _ ->
            val selectedMessage = listData[position]
            Toast.makeText(requireContext(), "Membuka pesan dari ${selectedMessage.name}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}