package com.example.minggu3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minggu3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // View Binding untuk mengakses komponen UI tanpa findViewById
    private lateinit var binding: ActivityMainBinding

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

        val students = getStudentList()

        binding.tvCount.text = "Showing ${students.size} students"

        // Instance StudentAdapter dengan mengirimkan data list mahasiswa.
        val adapter = StudentAdapter(students) { student ->
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
    }

    private fun getStudentList(): List<Student> {
        return listOf(
            Student("Yoga Pramana", "222117068", "Software Technology"),
            Student("Michael Steven", "222117047", "Intelligence System"),
            Student("William Sugiarto", "222117067", "Software Technology"),
            Student("Gregorius Kendick", "222117024", "Intelligence System"),
            Student("Darren Susanto", "225117195", "Computer Science"),
            Student("Albobus Kerenus", "222117003", "Intelligence System"),
            Student("Ryu Alvino", "222117060", "Software Technology"),
            Student("Albert Manzo", "225117147", "Informatics"),
            Student("Welly Chandra", "225117194", "Informatics"),
            Student("Jason Tungary", "225117195", "Informatics"),
        )
    }
}