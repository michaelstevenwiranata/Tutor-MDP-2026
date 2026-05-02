package com.example.tutorw7

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tutorw7.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: BucinViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail Bucin"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bucinData = intent.getSerializableExtra("BUCIN_DATA") as? Bucin

        bucinData?.let { bucin ->
            val emojiOnly = bucin.tingkatAnomali.split(" ").lastOrNull() ?: "😐"

            binding.tvDetailEmoji.text = emojiOnly
            binding.tvDetailPasangan.text = "${bucin.pasangan1} ❤️ ${bucin.pasangan2}"
            binding.tvDetailAnomali.text = "Status: ${bucin.tingkatAnomali}"
            binding.tvDetailDeskripsi.text = bucin.deskripsi

            // Navigasi ke FormActivity dengan mode Edit
            binding.btnEdit.setOnClickListener {
                val intent = Intent(this, FormActivity::class.java)
                intent.putExtra("BUCIN_DATA", bucin)
                startActivity(intent)
                finish()
            }

            // Delete
            binding.btnDelete.setOnClickListener {
                viewModel.deleteBucin(bucin.id) {
                    Toast.makeText(this, "Data berhasil dihapus!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}