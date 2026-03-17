package com.example.tutor_m4.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tutor_m4.Mahasiswa
import com.example.tutor_m4.R
import com.example.tutor_m4.databinding.FragmentAddMahasiswaBinding
import kotlin.getValue

class AddMahasiswaFragment : Fragment() {
    lateinit var binding : FragmentAddMahasiswaBinding
    private val args: AddMahasiswaFragmentArgs by navArgs()

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
        val mahasiswaArgs = args.mahasiswa
        if (mahasiswaArgs != null) {
            binding.etNama.setText(mahasiswaArgs.nama)
            binding.etNRP.setText(mahasiswaArgs.nrp)
            binding.etJurusan.setText(mahasiswaArgs.jurusan)
            binding.btnTambah.text = "Update"
            binding.etNRP.isEnabled = false
        }

        binding.btnTambah.setOnClickListener {
            val nama = binding.etNama.text.toString()
            val nrp = binding.etNRP.text.toString()
            val jurusan = binding.etJurusan.text.toString()

            if (mahasiswaArgs != null) {
                val mahasiswaUpdate = Mahasiswa(nama, nrp, jurusan)
                MockDatabase.editMahasiswa(mahasiswaUpdate)
            } else {
                val mahasiswaBaru = Mahasiswa(nama, nrp, jurusan)
                MockDatabase.addMahasiswa(mahasiswaBaru)
            }

            clearForm()
        }
    }
}