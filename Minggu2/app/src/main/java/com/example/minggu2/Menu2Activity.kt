package com.example.minggu2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.AlarmClock
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.minggu2.databinding.ActivityMenu2Binding

class Menu2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityMenu2Binding
    private var isSecretMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenu2Binding.inflate(layoutInflater)
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        val namaPet = intent.getStringExtra("EXTRA_NAMA") ?: "User"

        // Membuat ArrayList 2D
        // Baris 0 = Normal, Baris 1 = Rahasia
        // Kolom 0 = Judul, Kolom 1 = Pesan
        val dataArray2D = arrayListOf(
            arrayListOf("MODE NORMAL", "Halo, jaga $namaPet baik-baik ya!"),
            arrayListOf("MODE RAHASIA", "AGEN 007: $namaPet adalah alien yang menyamar.")
        )

        // Set tampilan awal
        tampilkanData(dataArray2D[0])

        // Logika Ganti Mode
        binding.btnGantiMode.setOnClickListener {
            isSecretMode = !isSecretMode // Toggle boolean
            if (isSecretMode) {
                tampilkanData(dataArray2D[1]) // Tampilkan baris indeks 1
            } else {
                tampilkanData(dataArray2D[0]) // Tampilkan baris indeks 0
            }
        }

        // ==========================================
        // IMPLICIT INTENTS (Membuka aplikasi sistem)
        // ==========================================

        // 1. Buka Website (Action View)
        binding.btnWeb.setOnClickListener {
            val intentWeb = Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com"))
            startActivity(intentWeb)
        }

        // 2. Kirim SMS (Action SendTo)
        binding.btnSms.setOnClickListener {
            val intentSms = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("smsto:081234567890") // Nomor tujuan
                putExtra("sms_body", "Tolong belikan makanan untuk $namaPet!")
            }
            startActivity(intentSms)
        }

        // 3. Pasang Alarm (Action Set Alarm)
        // Catatan: Ini butuh permission <uses-permission android:name="com.android.alarm.permission.SET_ALARM" /> di AndroidManifest.xml
        binding.btnAlarm.setOnClickListener {
            val intentAlarm = Intent(AlarmClock.ACTION_SET_ALARM).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, "Waktunya kasih makan $namaPet!")
                putExtra(AlarmClock.EXTRA_HOUR, 7)
                putExtra(AlarmClock.EXTRA_MINUTES, 30)
                putExtra(AlarmClock.EXTRA_SKIP_UI, true) // Langsung set tanpa buka app alarm
            }
            // Pengecekan apakah HP punya aplikasi alarm
            if (intentAlarm.resolveActivity(packageManager) != null) {
                startActivity(intentAlarm)
            }
        }

        binding.ibBack2.setOnClickListener {
            finish()
        }
    }

    // Fungsi bantuan untuk update UI dari ArrayList 2D
    private fun tampilkanData(dataBaris: ArrayList<String>) {
        val judul = dataBaris[0]
        val pesan = dataBaris[1]
        binding.tvTampilan.text = "$judul\n\n$pesan"

        if (judul == "MODE RAHASIA") {
            binding.tvTampilan.setBackgroundColor(android.graphics.Color.DKGRAY)
            binding.tvTampilan.setTextColor(android.graphics.Color.WHITE)
        } else {
            binding.tvTampilan.setBackgroundColor(android.graphics.Color.LTGRAY)
            binding.tvTampilan.setTextColor(android.graphics.Color.BLACK)
        }
    }

}