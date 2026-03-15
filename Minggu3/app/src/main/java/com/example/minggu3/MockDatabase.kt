package com.example.minggu3

/**
 * MockDatabase — Simulasi database sederhana menggunakan Singleton pattern.
 *
 * Class ini mengelola data mahasiswa di dalam memory (MutableList).
 * Menyediakan operasi CRUD dasar: insert, getAll, search, dan sort.
 *
 * Menggunakan `object` keyword di Kotlin = Singleton (hanya ada 1 instance).
 */
object MockDatabase {

    // Data storage — menyimpan semua data mahasiswa di memory
    private val students: MutableList<Student> = mutableListOf()

    /**
     * Inisialisasi database dengan data awal (seed data).
     * Dipanggil sekali saat aplikasi pertama kali dibuka.
     */
    fun seed() {
        if (students.isNotEmpty()) return // Jangan seed ulang kalau sudah ada data

        students.addAll(
            listOf(
                Student("Yoga Pramana Syahputra Teguh", "222117068", "Software Technology"),
                Student("Michael Steven Wiranata", "222117047", "Intelligence System"),
                Student("William Sugiarto", "222117067", "Software Technology"),
                Student("Gregorius Kendick", "222117024", "Intelligence System"),
                Student("Darren Susanto", "223180572", "Computer Science"),
                Student("Albobus Kerenus", "222117003", "Intelligence System"),
                Student("Ryu Alvino Hartono", "222117060", "Software Technology"),
                Student("Albert Manzo", "225117147", "Informatics"),
                Student("Welly Chandra Sutrisno", "225117194", "Informatics"),
                Student("Yohanes Pembaptis Jason Tungary", "225117195", "Informatics"),
                Student("Trevis Artagrantdy Kurniawan", "223117114", "Computer Science"),
                Student("Vincentius Jason Tjendika", "223117115", "Computer Science"),
                Student("William Fabian Setiadi", "225180606", "Business Information System"),
            )
        )
    }

    /**
     * INSERT — Menambahkan mahasiswa baru ke database.
     * @return true jika berhasil, false jika NRP sudah ada (duplicate).
     */
    fun insert(student: Student): Boolean {
        // Cek apakah NRP sudah ada (NRP harus unik)
        if (students.any { it.nrp == student.nrp }) {
            return false
        }
        students.add(student)
        return true
    }

    /**
     * GET ALL — Mengambil semua data mahasiswa.
     * Mengembalikan salinan list agar data asli tidak bisa diubah dari luar.
     */
    fun getAll(): List<Student> {
        return students.toList()
    }

    /**
     * SEARCH — Mencari mahasiswa berdasarkan nama (case-insensitive).
     * Menggunakan filter + contains untuk partial matching.
     */
    fun searchByName(query: String): List<Student> {
        if (query.isBlank()) return getAll()
        return students.filter {
            it.name.lowercase().contains(query.lowercase())
        }
    }

    /**
     * SORT — Mengurutkan data mahasiswa.
     * @param field kolom yang ingin di-sort ("name" atau "nrp")
     * @param ascending true = A-Z / kecil-besar, false = Z-A / besar-kecil
     */
    fun sort(field: String, ascending: Boolean) {
        when (field) {
            "name" -> {
                if (ascending) students.sortBy { it.name.lowercase() }
                else students.sortByDescending { it.name.lowercase() }
            }
            "nrp" -> {
                if (ascending) students.sortBy { it.nrp }
                else students.sortByDescending { it.nrp }
            }
        }
    }

    /**
     * COUNT — Mengembalikan jumlah total mahasiswa di database.
     */
    fun count(): Int = students.size
}
