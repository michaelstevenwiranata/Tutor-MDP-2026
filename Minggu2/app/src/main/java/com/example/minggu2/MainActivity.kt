package com.example.minggu2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.minggu2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        binding.btnMenu1.setOnClickListener {
            val nama = binding.etNamaPet.text.toString()
            if (nama.isNotEmpty()) {
                val namaList = nama.split(",")
                Log.d("haloo", namaList.toString())
                if(namaList.size >= 5){
                    val intent = Intent(this, Menu1Activity::class.java)
                    intent.putExtra("EXTRA_NAMA", namaList.joinToString("")) // Mengirim data String
                    startActivity(intent)
                } else{
                    Toast.makeText(this, "Panjang nama minimal 5 ya!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Isi nama pet dulu ya!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnMenu2.setOnClickListener {
            val nama = binding.etNamaPet.text.toString()
            if (nama.isNotEmpty()) {
                val namaList = nama.split(",")
                if(namaList.size >= 5) {
                    val intent = Intent(this, Menu2Activity::class.java)
                    intent.putExtra("EXTRA_NAMA", namaList.joinToString(""))
                    startActivity(intent)
                } else{
                    Toast.makeText(this, "Panjang nama minimal 5 ya!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Isi nama pet dulu ya!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}