package com.example.tutorw3

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tutorw3.databinding.ActivityCoverBinding

class CoverActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoverBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi MockDB
        MockDB.initData()

        binding.btnMulai.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish() // Tutup cover agar tidak bisa di-back
        }
    }
}