package com.example.tutorw7

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tutorw7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: BucinViewModel by viewModels()
    private lateinit var adapter: BucinAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Adapter
        adapter = BucinAdapter { bucin ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("BUCIN_DATA", bucin)
            startActivity(intent)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // Setup Observers
        viewModel.bucinList.observe(this) { list ->
            adapter.submitList(list)
        }

        // Loading
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        // Setup FAB
        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, FormActivity::class.java))
        }

        viewModel.fetchBucinList()
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchBucinList()
    }
}