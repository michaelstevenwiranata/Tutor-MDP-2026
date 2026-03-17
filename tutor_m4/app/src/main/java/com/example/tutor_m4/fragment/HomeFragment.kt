package com.example.tutor_m4.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tutor_m4.MahasiswaAdapter
import com.example.tutor_m4.R
import com.example.tutor_m4.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: MahasiswaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         adapter = MahasiswaAdapter ({ mahasiswa ->

            val action = HomeFragmentDirections
                .actionHomeFragmentToBiodataFragment(mahasiswa)

            findNavController().navigate(action)

        },
            { mahasiswa ->
                val action = HomeFragmentDirections.actionHomeFragmentToAddMahasiswaFragment(mahasiswa)

                findNavController().navigate(action)
            },
            { mahasiswa ->
                MockDatabase.deleteMahasiswa(mahasiswa)
                adapter.notifyDataSetChanged()})
        binding.rvMahasiswa.adapter = adapter
        binding.rvMahasiswa.layoutManager = LinearLayoutManager(requireContext())

        adapter.submitList(MockDatabase.getMahasiswa())
    }

}