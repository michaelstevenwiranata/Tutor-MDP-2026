package com.example.tutorw5

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tutorw5.R
import com.example.tutorw5.Reaction
import com.example.tutorw5.databinding.FragmentReactionBinding
import com.example.tutorw5.ReactionAdapter
import com.example.tutorw5.MainViewModel

class ReactionFragment : Fragment() {

    private var _binding: FragmentReactionBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()
    private lateinit var reactionAdapter: ReactionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()

        binding.fabAdd.setOnClickListener {
            // Navigasi ke form fragment, passing -1 untuk Add
            val bundle = Bundle().apply { putInt("reactionId", -1) }
            findNavController().navigate(R.id.action_nav_reaction_to_form, bundle)
        }
    }

    private fun setupRecyclerView() {
        reactionAdapter = ReactionAdapter(
            onEditClick = { reaction ->
                // Navigasi ke form fragment, passing ID untuk Edit
                val bundle = Bundle().apply { putInt("reactionId", reaction.id) }
                findNavController().navigate(R.id.action_nav_reaction_to_form, bundle)
            },
            onDeleteClick = { reaction -> viewModel.deleteReaction(reaction.id) }
        )
        binding.rvReactions.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = reactionAdapter
        }
    }

    private fun setupObservers() {
        // UI Murni mengamati LiveData List Reaction
        viewModel.reactions.observe(viewLifecycleOwner) { list ->
            reactionAdapter.submitList(list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}