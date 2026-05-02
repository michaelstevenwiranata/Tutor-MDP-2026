package com.example.tutorw7

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tutorw7.databinding.ActivityFormBinding

class FormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormBinding
    private val viewModel: BucinViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Catat Pasangan Baru"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Setup Spinner Options (Tingkat Anomali)
        val anomalyOptions = arrayOf("Normal 😐", "Bucin 😍", "Whattt 🤯")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, anomalyOptions)
        binding.spinnerAnomali.adapter = spinnerAdapter

        // Cek apakah ini Mode Edit (ada data dikirim)
        val editData = intent.getSerializableExtra("BUCIN_DATA") as? Bucin
        val isEditMode = editData != null

        if (isEditMode) {
            supportActionBar?.title = "Edit Pasangan"
            binding.btnSimpan.text = "Update Pasangan"

            // Isi form dengan data lama
            binding.etPasangan1.setText(editData?.pasangan1)
            binding.etPasangan2.setText(editData?.pasangan2)
            binding.etDeskripsi.setText(editData?.deskripsi)

            val spinnerPosition = anomalyOptions.indexOf(editData?.tingkatAnomali)
            if (spinnerPosition >= 0) {
                binding.spinnerAnomali.setSelection(spinnerPosition)
            }
        } else {
            supportActionBar?.title = "Catat Pasangan Baru"
        }

        // Observer untuk ProgressBar
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBarForm.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.btnSimpan.isEnabled = !isLoading
        }

        binding.btnSimpan.setOnClickListener {
            val pasangan1 = binding.etPasangan1.text.toString()
            val pasangan2 = binding.etPasangan2.text.toString()
            val tingkatAnomali = binding.spinnerAnomali.selectedItem.toString()
            val deskripsi = binding.etDeskripsi.text.toString()

            if (pasangan1.isBlank() || pasangan2.isBlank() || deskripsi.isBlank()) {
                Toast.makeText(this, "Mohon isi semua field!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newBucin = Bucin(
                id = editData?.id ?: 0, // Gunakan ID lama jika mode edit, 0 jika baru
                pasangan1 = pasangan1,
                pasangan2 = pasangan2,
                tingkatAnomali = tingkatAnomali,
                deskripsi = deskripsi
            )

            if (isEditMode) {
                viewModel.updateBucin(newBucin.id, newBucin) {
                    Toast.makeText(this, "Berhasil diupdate!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } else {
                viewModel.addBucin(newBucin) {
                    Toast.makeText(this, "Berhasil disimpan!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}