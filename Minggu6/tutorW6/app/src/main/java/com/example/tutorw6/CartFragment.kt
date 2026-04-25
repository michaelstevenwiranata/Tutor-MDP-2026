package com.example.tutorw6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tutorw6.databinding.FragmentCartBinding
import kotlinx.coroutines.launch

class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase
    private lateinit var adapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.getDatabase(requireContext())

        setupRecyclerView()

        // Clear Cart
        binding.btnClearCart.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                db.cartDao().clearCart()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            db.cartDao().getAllCartItems().collect { items ->
                adapter.submitList(items)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
        adapter = CartAdapter(
            onPlusClick = { item ->
                viewLifecycleOwner.lifecycleScope.launch {
                    db.cartDao().update(item.copy(quantity = item.quantity + 1))
                }
            },
            onMinusClick = { item ->
                viewLifecycleOwner.lifecycleScope.launch {
                    if (item.quantity > 1) {
                        db.cartDao().update(item.copy(quantity = item.quantity - 1))
                    } else {
                        db.cartDao().delete(item) // Hapus jika 0
                    }
                }
            }
        )
        binding.rvCart.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}