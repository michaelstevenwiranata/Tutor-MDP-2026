package com.example.tutor_m4.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tutor_m4.Mahasiswa
import com.example.tutor_m4.R
import com.example.tutor_m4.databinding.FragmentAddMahasiswaBinding

class AddMahasiswaFragment : Fragment() {
    lateinit var binding : FragmentAddMahasiswaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddMahasiswaBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun clearForm() {
        binding.etNama.text.clear()
        binding.etNRP.text.clear()
        binding.etJurusan.text.clear()
        findNavController().popBackStack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnTambah.setOnClickListener {

            val mahasiswa = Mahasiswa(
                binding.etNama.text.toString(),
                binding.etNRP.text.toString(),
                binding.etJurusan.text.toString(),
            )

            MockDatabase.addMahasiswa(mahasiswa)
            clearForm()
        }
    }
}