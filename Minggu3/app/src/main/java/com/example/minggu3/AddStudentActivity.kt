package com.example.minggu3

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.minggu3.databinding.ActivityAddStudentBinding

class AddStudentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        binding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.addStudentRoot) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }

        binding.btnSave.setOnClickListener {
            saveStudent()
        }
    }

    private fun saveStudent() {
        val name = binding.etName.text?.toString().orEmpty()
        val nrp = binding.etNrp.text?.toString().orEmpty()
        val major = binding.etMajor.text?.toString().orEmpty()

        val student = Student.create(name, nrp, major)
        if (student == null) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val inserted = MockDatabase.insert(student)
        if (!inserted) {
            Toast.makeText(this, "NRP ${student.nrp} already exists!", Toast.LENGTH_SHORT).show()
            return
        }

        val result = Intent().apply {
            putExtra(EXTRA_ADDED_NAME, student.name)
        }
        setResult(RESULT_OK, result)
        finish()
    }

    companion object {
        const val EXTRA_ADDED_NAME = "extra_added_name"
    }
}

