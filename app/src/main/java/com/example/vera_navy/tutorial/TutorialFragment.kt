package com.example.vera_navy.tutorial

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.vera_navy.databinding.FragmentTutorialBinding

class TutorialFragment : Fragment() {

    private var _binding: FragmentTutorialBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTutorialBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = arguments?.getString(ARG_TITLE)
        val description = arguments?.getString(ARG_DESCRIPTION)
        val backgroundColor = arguments?.getString(ARG_COLOR)

        binding.tvTitle.text = title
        binding.tvDescription.text = description
        binding.tutorialLayout.setBackgroundColor(Color.parseColor(backgroundColor))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_TITLE = "title"
        private const val ARG_DESCRIPTION = "description"
        private const val ARG_COLOR = "color"

        fun newInstance(title: String, description: String, color: String): TutorialFragment {
            val fragment = TutorialFragment()
            val args = Bundle()
            args.putString(ARG_TITLE, title)
            args.putString(ARG_DESCRIPTION, description)
            args.putString(ARG_COLOR, color)
            fragment.arguments = args
            return fragment
        }
    }
}