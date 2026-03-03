package com.example.minggu3

/**
 * Student — Data class yang merepresentasikan seorang mahasiswa.
 *
 * Menggunakan `data class` agar Kotlin otomatis men-generate:
 *   - equals() & hashCode()
 *   - toString()
 *   - copy()
 *   - componentN() functions (destructuring)
 *
 * OOP Concepts:
 *   - Encapsulation: properties bersifat val (immutable dari luar)
 *   - Companion Object: factory method & validation logic
 */
data class Student(
    val name: String,
    val nrp: String,
    val major: String
) {
    /**
     * Computed property — Mengambil inisial dari nama mahasiswa.
     * Contoh: "Yoga Pramana" → "YP"
     */
    val initials: String
        get() = name.split(" ")
            .filter { it.isNotBlank() }
            .joinToString("") { it.first().uppercase() }

    /**
     * Companion Object — berisi static-like methods.
     * Bisa diakses tanpa membuat instance: Student.create(...)
     */
    companion object {
        /**
         * Factory method — membuat Student dengan validasi.
         * @return Student jika valid, null jika ada field kosong.
         */
        fun create(name: String, nrp: String, major: String): Student? {
            if (name.isBlank() || nrp.isBlank() || major.isBlank()) {
                return null
            }
            return Student(
                name = name.trim(),
                nrp = nrp.trim(),
                major = major.trim()
            )
        }
    }
}
