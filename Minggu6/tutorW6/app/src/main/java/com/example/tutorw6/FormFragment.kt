package com.example.tutorw6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tutorw6.databinding.FragmentFormBinding
import kotlinx.coroutines.launch

class FormFragment : Fragment() {

    private var _binding: FragmentFormBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase
    private var breadId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.getDatabase(requireContext())
        breadId = arguments?.getInt("BREAD_ID") ?: -1

        // Jika mode edit, populate data
        if (breadId != -1) {
            binding.tvTitleForm.text = "Edit Roti"
            viewLifecycleOwner.lifecycleScope.launch {
                val bread = db.breadDao().getBreadById(breadId)
                bread?.let {
                    binding.etEmoji.setText(it.emoji)
                    binding.etName.setText(it.name)
                    binding.etDetail.setText(it.detail)
                }
            }
        }

        binding.btnSave.setOnClickListener {
            val emoji = binding.etEmoji.text.toString()
            val name = binding.etName.text.toString()
            val detail = binding.etDetail.text.toString()

            if (emoji.isNotBlank() && name.isNotBlank() && detail.isNotBlank()) {
                val newBread = Bread(
                    id = if (breadId == -1) 0 else breadId,
                    name = name,
                    emoji = emoji,
                    detail = detail
                )

                viewLifecycleOwner.lifecycleScope.launch {
                    if (breadId == -1) {
                        db.breadDao().insert(newBread)
                    } else {
                        db.breadDao().update(newBread)
                    }
                    findNavController().popBackStack()
                }
            } else {
                Toast.makeText(requireContext(), "Harap isi semua field", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}