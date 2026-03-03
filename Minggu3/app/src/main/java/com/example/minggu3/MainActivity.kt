package com.example.minggu3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minggu3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // View Binding untuk mengakses komponen UI tanpa findViewById
    private lateinit var binding: ActivityMainBinding

    // Adapter untuk RecyclerView
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // Inisialisasi Data Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Padding otomatis agar konten tidak tertutup status bar atau navigation bar
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Seed MockDatabase dengan data awal
        MockDatabase.seed()

        // Instance StudentAdapter dengan data dari MockDatabase
        adapter = StudentAdapter(MockDatabase.getAll().toMutableList()) { student ->
            Toast.makeText(
                this,
                "${student.name} (${student.initials}) — ${student.major}",
                Toast.LENGTH_SHORT
            ).show()
        }

        // LayoutManager bertanggung jawab untuk mengatur posisi item di dalam RecyclerView.
        // LinearLayoutManager akan menyusun item secara vertikal (default) atau horizontal.
        binding.rvStudents.layoutManager = LinearLayoutManager(this)

        // Hubungkan Adapter ke RecyclerView
        binding.rvStudents.adapter = adapter

        updateCount(MockDatabase.count())

        // ===========================================
        // INSERT — Menambahkan mahasiswa baru ke MockDatabase
        // ===========================================
        binding.btnInsert.setOnClickListener {
            val name = binding.etName.text.toString()
            val nrp = binding.etNrp.text.toString()
            val major = binding.etMajor.text.toString()

            // Gunakan factory method dari Student companion object (OOP)
            val newStudent = Student.create(name, nrp, major)

            if (newStudent == null) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Insert ke MockDatabase
            val success = MockDatabase.insert(newStudent)

            if (!success) {
                Toast.makeText(this, "NRP ${newStudent.nrp} already exists!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Bersihkan input fields
            binding.etName.text?.clear()
            binding.etNrp.text?.clear()
            binding.etMajor.text?.clear()

            // Refresh tampilan (terapkan ulang search jika ada)
            applySearchFilter()

            Toast.makeText(this, "${newStudent.name} added!", Toast.LENGTH_SHORT).show()
        }

        // ===========================================================
        // SEARCH — Filter mahasiswa berdasarkan nama secara real-time
        // ===========================================================
        binding.etSearch.addTextChangedListener { _ ->
            applySearchFilter()
        }

        // ===============================================
        // SORT — Mengurutkan data di MockDatabase
        // ===============================================
        binding.btnSortNameAsc.setOnClickListener {
            MockDatabase.sort("name", ascending = true)
            applySearchFilter()
        }

        binding.btnSortNameDesc.setOnClickListener {
            MockDatabase.sort("name", ascending = false)
            applySearchFilter()
        }

        binding.btnSortNrpAsc.setOnClickListener {
            MockDatabase.sort("nrp", ascending = true)
            applySearchFilter()
        }

        binding.btnSortNrpDesc.setOnClickListener {
            MockDatabase.sort("nrp", ascending = false)
            applySearchFilter()
        }
    }

    /**
     * Mengambil data dari MockDatabase, lalu filter berdasarkan query search.
     * Hasilnya di-pass ke adapter untuk ditampilkan di RecyclerView.
     */
    private fun applySearchFilter() {
        val query = binding.etSearch.text.toString().trim()
        val filtered = MockDatabase.searchByName(query)
        adapter.updateList(filtered)
        updateCount(filtered.size)
    }

    private fun updateCount(count: Int) {
        binding.tvCount.text = "Showing $count students"
    }
}