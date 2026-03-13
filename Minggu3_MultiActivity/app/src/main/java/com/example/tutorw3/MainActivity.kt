package com.example.tutorw3

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tutorw3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ExcuseAdapter
    private var isGrid = false
    private var isAscending = true
    private var currentList = ArrayList<Excuse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentList.addAll(MockDB.excuseList) // Salin data dari DB
        setupRecyclerView()

        // --- FITUR SEARCH ---
        binding.btnSearch.setOnClickListener {
            val keyword = binding.etSearch.text.toString().trim()
            val filteredList = MockDB.excuseList.filter {
                it.judul.contains(keyword, ignoreCase = true)
            } as ArrayList
            currentList = filteredList
            sortList() // Terapkan sorting saat ini pada hasil pencarian
        }

        // --- FITUR SWITCH LAYOUT (LINEAR <-> GRID) ---
        binding.btnToggleView.setOnClickListener {
            isGrid = !isGrid
            if (isGrid) {
                binding.rvExcuse.layoutManager = GridLayoutManager(this, 2)
                binding.btnToggleView.text = "Mode List 📋"
            } else {
                binding.rvExcuse.layoutManager = LinearLayoutManager(this)
                binding.btnToggleView.text = "Mode Grid 🔲"
            }
        }

        // --- FITUR SORT (ASC <-> DESC) ---
        binding.btnSort.setOnClickListener {
            isAscending = !isAscending
            binding.btnSort.text = if (isAscending) "Sort A-Z ⬇️" else "Sort Z-A ⬆️"
            sortList()
        }

        // --- FITUR TAMBAH ---
        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, FormActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        // Reset list ke data terbaru di MockDB setiap kembali ke halaman ini
        currentList.clear()
        currentList.addAll(MockDB.excuseList)
        sortList()
    }

    private fun setupRecyclerView() {
        adapter = ExcuseAdapter(
            onEditClick = { excuse ->
                val intent = Intent(this, FormActivity::class.java)
                intent.putExtra("EXTRA_EXCUSE", excuse) // Kirim object
                startActivity(intent)
            },
            onDeleteClick = { excuse ->
                MockDB.excuseList.removeIf { it.id == excuse.id }
                currentList.remove(excuse)
                adapter.notifyDataSetChanged()
            },
            onItemClick = { excuse ->
                // Tampilkan Detail via AlertDialog Imut
                AlertDialog.Builder(this)
                    .setTitle("${excuse.emoji} ${excuse.judul}")
                    .setMessage("Risiko: ${excuse.tingkatRisiko}\n\nDetail:\n${excuse.deskripsi}")
                    .setPositiveButton("Tutup ✨", null)
                    .show()
            }
        )
        adapter.submitList(currentList)

        binding.rvExcuse.layoutManager = LinearLayoutManager(this)
        binding.rvExcuse.adapter = adapter
    }

    private fun sortList() {
        if (isAscending) {
            currentList.sortBy { it.judul.lowercase() }
        } else {
            currentList.sortByDescending { it.judul.lowercase() }
        }
        adapter.submitList(currentList)
    }
}
