package com.example.tutorw3

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tutorw3.databinding.ActivityFormBinding
import java.util.UUID

class FormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormBinding
    private var excuseToEdit: Excuse? = null

    // Pilihan dropdown untuk Spinner
    private val pilihanEmoji = arrayOf("🌧️ Cuaca", "🏍️ Kendaraan", "🐈 Hewan", "🚽 Sakit", "👽 Lainnya")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Spinner
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, pilihanEmoji)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerTipe.adapter = spinnerAdapter

        // Cek apakah ini Mode EDIT (terima Intent)
        if (intent.hasExtra("EXTRA_EXCUSE")) {
            excuseToEdit = intent.getParcelableExtra("EXTRA_EXCUSE")
            setupEditMode()
        }

        binding.btnSimpan.setOnClickListener {
            simpanData()
        }
    }

    private fun setupEditMode() {
        binding.tvFormJudul.text = "Edit Alasan ✏️"
        binding.etJudul.setText(excuseToEdit?.judul)
        binding.etDeskripsi.setText(excuseToEdit?.deskripsi)

        // Set Spinner ke value yang benar (cari Emoji-nya)
        val indexSpinner = pilihanEmoji.indexOfFirst { it.contains(excuseToEdit?.emoji ?: "") }
        if (indexSpinner >= 0) binding.spinnerTipe.setSelection(indexSpinner)

        // Set RadioButton
        when (excuseToEdit?.tingkatRisiko) {
            "Aman" -> binding.rbAman.isChecked = true
            "Bahaya" -> binding.rbBahaya.isChecked = true
            "Kritis" -> binding.rbKritis.isChecked = true
        }
    }

    private fun simpanData() {
        val judul = binding.etJudul.text.toString()
        val deskripsi = binding.etDeskripsi.text.toString()

        // Ambil Emoji dari tulisan di Spinner (ambil 2 karakter pertama misal "🌧️")
        val emojiLengkap = binding.spinnerTipe.selectedItem.toString()
        val emoji = emojiLengkap.substring(0, 2).trim()

        // Ambil data RadioButton
        val risiko = when (binding.rgRisiko.checkedRadioButtonId) {
            R.id.rbAman -> "Aman"
            R.id.rbBahaya -> "Bahaya"
            R.id.rbKritis -> "Kritis"
            else -> "Aman" // Default
        }

        if (judul.isEmpty() || deskripsi.isEmpty()) {
            Toast.makeText(this, "Isi data dengan lengkap ya 🥺", Toast.LENGTH_SHORT).show()
            return
        }

        if (excuseToEdit == null) {
            // MODE TAMBAH
            val newId = UUID.randomUUID().toString()
            val newExcuse = Excuse(newId, judul, emoji, deskripsi, risiko)
            MockDB.excuseList.add(newExcuse)
            Toast.makeText(this, "Alasan berhasil ditambah! ✨", Toast.LENGTH_SHORT).show()
        } else {
            // MODE EDIT
            val index = MockDB.excuseList.indexOfFirst { it.id == excuseToEdit?.id }
            if (index != -1) {
                MockDB.excuseList[index].judul = judul
                MockDB.excuseList[index].emoji = emoji
                MockDB.excuseList[index].deskripsi = deskripsi
                MockDB.excuseList[index].tingkatRisiko = risiko
                Toast.makeText(this, "Alasan berhasil diupdate! ✏️", Toast.LENGTH_SHORT).show()
            }
        }

        // Selesai, tutup halaman dan kembali ke List
        finish()
    }
}
