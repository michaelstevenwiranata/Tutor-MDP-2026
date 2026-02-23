package com.example.minggu1

class Buku(var judul: String, var penulis: String, var tahunTerbit: Int) {
    // Fungsi ini buat nampilin info buku biar rapi
    fun infoBuku(): String {
        return "[$tahunTerbit] $judul - karya $penulis"
    }
}
