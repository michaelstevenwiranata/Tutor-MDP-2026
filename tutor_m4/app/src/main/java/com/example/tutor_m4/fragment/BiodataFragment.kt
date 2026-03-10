package com.example.tutor_m4.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tutor_m4.R
import com.example.tutor_m4.databinding.FragmentBiodataBinding

class BiodataFragment : Fragment() {
    lateinit var binding: FragmentBiodataBinding
    private val args: BiodataFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBiodataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mahasiswa = args.mahasiswa

        binding.txtNama.text = mahasiswa.nama
        binding.txtNRP.text = mahasiswa.nrp
        binding.txtJurusan.text = mahasiswa.jurusan
        binding.imgProfile.setImageResource(mahasiswa.foto)
        binding.btnBack.setOnClickListener {

            findNavController().popBackStack()

        }
    }
}