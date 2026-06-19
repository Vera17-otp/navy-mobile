package com.example.vera_navy.Message

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.example.vera_navy.R
import com.example.vera_navy.databinding.FragmentMessageBinding
import com.example.vera_navy.tutorial.TutorialMessageActivity

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

        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Message"

        setupMenu()

        // Langkah 5: Definisikan list data message
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

    private fun setupMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_message, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_tutorial -> {
                        val intent = Intent(requireContext(), TutorialMessageActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}