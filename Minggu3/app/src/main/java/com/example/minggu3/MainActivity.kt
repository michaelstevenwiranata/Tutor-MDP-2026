package com.example.minggu3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minggu3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: StudentAdapter

    private var sortField = "name"
    private var isAscending = true

    private val addStudentLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                applySortAndFilter()
                val addedName = result.data?.getStringExtra(AddStudentActivity.EXTRA_ADDED_NAME)
                if (!addedName.isNullOrBlank()) {
                    Toast.makeText(this, "$addedName added!", Toast.LENGTH_SHORT).show()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        MockDatabase.seed()

        adapter = StudentAdapter { student ->
            Toast.makeText(
                this,
                "${student.name} (${student.initials}) - ${student.major}",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.rvStudents.layoutManager = LinearLayoutManager(this)
        binding.rvStudents.adapter = adapter

        setupSortControls()
        setupActions()
        applySortAndFilter()
    }

    private fun setupSortControls() {
        val sortByOptions = listOf("Name", "NRP")
        val sortOrderOptions = listOf("Ascending", "Descending")

        binding.spSortBy.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, sortByOptions)
        binding.spSortOrder.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, sortOrderOptions)

        val listener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                sortField = if (binding.spSortBy.selectedItemPosition == 0) "name" else "nrp"
                isAscending = binding.spSortOrder.selectedItemPosition == 0
                applySortAndFilter()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        }

        binding.spSortBy.onItemSelectedListener = listener
        binding.spSortOrder.onItemSelectedListener = listener

        binding.spSortBy.setSelection(0)
        binding.spSortOrder.setSelection(0)
    }

    private fun setupActions() {
        binding.etSearch.addTextChangedListener {
            applySortAndFilter()
        }

        binding.fabAdd.setOnClickListener {
            addStudentLauncher.launch(Intent(this, AddStudentActivity::class.java))
        }
    }

    private fun applySortAndFilter() {
        MockDatabase.sort(field = sortField, ascending = isAscending)

        val query = binding.etSearch.text?.toString().orEmpty().trim()
        val filtered = MockDatabase.searchByName(query)

        adapter.submitList(filtered)
        updateCount(filtered.size)
    }

    private fun updateCount(count: Int) {
        binding.tvCount.text = getString(R.string.showing_students, count)
    }
}