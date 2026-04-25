package com.example.tutorw6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.tutorw6.databinding.FragmentDetailBinding
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase
    private var breadId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.getDatabase(requireContext())
        breadId = arguments?.getInt("BREAD_ID") ?: -1

        if (breadId != -1) {
            // Mengambil detail roti menggunakan Select Where by ID
            viewLifecycleOwner.lifecycleScope.launch {
                val bread = db.breadDao().getBreadById(breadId)
                bread?.let {
                    binding.tvEmojiDetail.text = it.emoji
                    binding.tvNameDetail.text = it.name
                    binding.tvDescDetail.text = it.detail
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}