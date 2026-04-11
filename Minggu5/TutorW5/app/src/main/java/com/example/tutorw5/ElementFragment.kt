package com.example.tutorw5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tutorw5.databinding.FragmentElementBinding


class ElementFragment : Fragment() {

    private var _binding: FragmentElementBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var elementAdapter: ElementAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentElementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        elementAdapter = ElementAdapter()
        binding.rvElements.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = elementAdapter
        }

        viewModel.elements.observe(viewLifecycleOwner) { list ->
            elementAdapter.submitList(list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}