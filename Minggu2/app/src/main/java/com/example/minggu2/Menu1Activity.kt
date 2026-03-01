package com.example.minggu2

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.minggu2.databinding.ActivityMenu1Binding

class Menu1Activity : AppCompatActivity() {

    private lateinit var binding: ActivityMenu1Binding
    private var statusPet = "is okay" // Status default

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenu1Binding.inflate(layoutInflater)
        setContentView(binding.root)

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        // 1. Menerima data dari Intent
        val namaPet = intent.getStringExtra("EXTRA_NAMA") ?: "Pet Tanpa Nama"
        binding.tvJudulPet.text = "Pet Kamu: $namaPet"

        // 2. Logika Tombol Warna (Ubah Status & Warna Pet)
        binding.btnHijau.setOnClickListener {
            binding.btnPet.setBackgroundColor(Color.GREEN)
            binding.btnPet.text = "\\(^o^)/"
            statusPet = "is happy"
        }

        binding.btnKuning.setOnClickListener {
            binding.btnPet.setBackgroundColor(Color.YELLOW)
            binding.btnPet.text = "(> '_' <)"
            statusPet = "is feeling normal"
        }

        binding.btnMerah.setOnClickListener {
            binding.btnPet.setBackgroundColor(Color.RED)
            binding.btnPet.text = "(> 0 <)!!"
            statusPet = "is angry"
        }

        // 3. Logika saat Pet ditekan (Menampilkan Toast)
        binding.btnPet.setOnClickListener {
            Toast.makeText(this, "$namaPet $statusPet", Toast.LENGTH_SHORT).show()
        }

        binding.ibBack.setOnClickListener {
            finish()
        }
    }
}