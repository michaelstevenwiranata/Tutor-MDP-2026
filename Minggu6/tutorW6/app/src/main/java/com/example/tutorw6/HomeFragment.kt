package com.example.tutorw6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tutorw6.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase
    private lateinit var adapter: BreadAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = (requireActivity().application as App).database

        setupRecyclerView()

        binding.fabAdd.setOnClickListener {
            // Menggunakan Safe Args Direction
            val action = HomeFragmentDirections.actionHomeFragmentToFormFragment(-1)
            findNavController().navigate(action)
        }

        // Observasi data dari database
        viewLifecycleOwner.lifecycleScope.launch {
            db.breadDao().getAllBreads().collect { breads ->
                adapter.submitList(breads)
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = BreadAdapter(
            onBreadClick = { id ->
                // Menggunakan Safe Args Direction
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(id)
                findNavController().navigate(action)
            },
            onAddCartClick = { bread ->
                addToCart(bread)
            },
            onEditClick = { id ->
                // Menggunakan Safe Args Direction
                val action = HomeFragmentDirections.actionHomeFragmentToFormFragment(id)
                findNavController().navigate(action)
            },
            onDeleteClick = { bread ->
                viewLifecycleOwner.lifecycleScope.launch {
                    db.breadDao().delete(bread)
                }
            }
        )
        binding.rvBreads.adapter = adapter
    }

    private fun addToCart(bread: Bread) {
        viewLifecycleOwner.lifecycleScope.launch {
            val existingItem = db.cartDao().getCartItemByBreadId(bread.id)
            if (existingItem != null) {
                db.cartDao().update(existingItem.copy(quantity = existingItem.quantity + 1))
            } else {
                db.cartDao().insert(
                    CartItem(breadId = bread.id, name = bread.name, emoji = bread.emoji, quantity = 1)
                )
            }
            Toast.makeText(requireContext(), "${bread.name} masuk keranjang!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}