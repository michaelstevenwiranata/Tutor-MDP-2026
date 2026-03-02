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

    // Master list (semua data mahasiswa, termasuk yang baru di-insert)
    private val masterList = mutableListOf<Student>()

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

        // Isi master list dengan data awal
        masterList.addAll(getStudentList())

        // Instance StudentAdapter dengan mengirimkan data list mahasiswa.
        adapter = StudentAdapter(masterList.toMutableList()) { student ->
            Toast.makeText(
                this,
                "${student.name} — ${student.major}",
                Toast.LENGTH_SHORT
            ).show()
        }

        // LayoutManager bertanggung jawab untuk mengatur posisi item di dalam RecyclerView.
        // LinearLayoutManager akan menyusun item secara vertikal (default) atau horizontal.
        binding.rvStudents.layoutManager = LinearLayoutManager(this)

        // Hubungkan Adapter ke RecyclerView
        binding.rvStudents.adapter = adapter

        updateCount(masterList.size)

        // ========================
        // INSERT — Menambahkan mahasiswa baru ke list
        // ========================
        binding.btnInsert.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val nrp = binding.etNrp.text.toString().trim()
            val major = binding.etMajor.text.toString().trim()

            if (name.isEmpty() || nrp.isEmpty() || major.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newStudent = Student(name, nrp, major)
            masterList.add(newStudent)

            // Bersihkan input fields
            binding.etName.text?.clear()
            binding.etNrp.text?.clear()
            binding.etMajor.text?.clear()

            // Refresh tampilan (terapkan ulang search jika ada)
            applySearchFilter()

            Toast.makeText(this, "$name added!", Toast.LENGTH_SHORT).show()
        }

        // ========================
        // SEARCH — Filter mahasiswa berdasarkan nama secara real-time
        // ========================
        binding.etSearch.addTextChangedListener { _ ->
            applySearchFilter()
        }

        // ========================
        // SORT — Mengurutkan list yang sedang ditampilkan
        // ========================
        binding.btnSortNameAsc.setOnClickListener {
            masterList.sortBy { it.name.lowercase() }
            applySearchFilter()
        }

        binding.btnSortNameDesc.setOnClickListener {
            masterList.sortByDescending { it.name.lowercase() }
            applySearchFilter()
        }

        binding.btnSortNrpAsc.setOnClickListener {
            masterList.sortBy { it.nrp }
            applySearchFilter()
        }

        binding.btnSortNrpDesc.setOnClickListener {
            masterList.sortByDescending { it.nrp }
            applySearchFilter()
        }
    }

    /**
     * Filter masterList berdasarkan query di search bar,
     * lalu update adapter dan count label.
     */
    private fun applySearchFilter() {
        val query = binding.etSearch.text.toString().trim().lowercase()
        val filtered = if (query.isEmpty()) {
            masterList.toList()
        } else {
            masterList.filter { it.name.lowercase().contains(query) }
        }
        adapter.updateList(filtered)
        updateCount(filtered.size)
    }

    private fun updateCount(count: Int) {
        binding.tvCount.text = "Showing $count students"
    }

    private fun getStudentList(): List<Student> {
        return listOf(
            Student("Yoga Pramana", "222117068", "Software Technology"),
            Student("Michael Steven", "222117047", "Intelligence System"),
            Student("William Sugiarto", "222117067", "Software Technology"),
            Student("Gregorius Kendick", "222117024", "Intelligence System"),
            Student("Darren Susanto", "225180195", "Computer Science"),
            Student("Albobus Kerenus", "222117003", "Intelligence System"),
            Student("Ryu Alvino", "222117060", "Software Technology"),
            Student("Albert Manzo", "225117147", "Informatics"),
            Student("Welly Chandra", "225117194", "Informatics"),
            Student("Jason Tungary", "225117195", "Informatics"),
        )
    }
}