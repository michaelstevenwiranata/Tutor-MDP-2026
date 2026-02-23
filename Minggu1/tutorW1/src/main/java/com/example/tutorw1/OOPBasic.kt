package com.example.tutorw1

import java.util.Scanner

class OOPBasic {
    private val daftarBuku = ArrayList<Buku>()
    private val scanner = Scanner(System.`in`)

    private fun lihatBuku() {
        if (daftarBuku.isEmpty()) {
            println("\nWah, rak bukunya masih kosong nih. Tambah dulu yuk!")
            return
        }
        println("\n--- DAFTAR BUKU SAAT INI ---")
        daftarBuku.forEachIndexed { index, buku ->
            println("${index + 1}. ${buku.infoBuku()}")
        }
    }

    private fun tambahBuku() {
        println("\n--- TAMBAH BUKU ---")
        print("Masukkan Judul Buku: ")
        val judul = scanner.nextLine()
        print("Masukkan Nama Penulis: ")
        val penulis = scanner.nextLine()
        print("Masukkan Tahun Terbit: ")
        val tahunStr = scanner.nextLine()
        val tahun = tahunStr.toIntOrNull() ?: 0

        val bukuBaru = Buku(judul, penulis, tahun)
        daftarBuku.add(bukuBaru)
        println("Mantap! Buku '$judul' berhasil ditambah ke ArrayList.")
    }

    private fun editBuku() {
        lihatBuku()
        if (daftarBuku.isEmpty()) return

        print("\nMasukkan nomor buku yang mau di-edit: ")
        val nomor = scanner.nextLine().toIntOrNull() ?: 0

        if (nomor > 0 && nomor <= daftarBuku.size) {
            val bukuTarget = daftarBuku[nomor - 1]
            println("Kamu mau edit: ${bukuTarget.infoBuku()}")

            print("Judul baru (kosongkan jika tidak ingin ubah): ")
            val judulBaru = scanner.nextLine()
            if (judulBaru.isNotBlank()) bukuTarget.judul = judulBaru

            print("Penulis baru (kosongkan jika tidak ingin ubah): ")
            val penulisBaru = scanner.nextLine()
            if (penulisBaru.isNotBlank()) bukuTarget.penulis = penulisBaru

            print("Tahun Terbit baru (kosongkan jika tidak ingin ubah): ")
            val tahunBaru = scanner.nextLine()
            if (tahunBaru.isNotBlank()) {
                bukuTarget.tahunTerbit = tahunBaru.toIntOrNull() ?: bukuTarget.tahunTerbit
            }
            println("Berhasil! Data buku sudah diupdate jadi: ${bukuTarget.infoBuku()}")
        } else {
            println("Nomor bukunya nggak ketemu bro.")
        }
    }

    private fun hapusBuku() {
        lihatBuku()
        if (daftarBuku.isEmpty()) return

        print("\nMasukkan nomor buku yang mau dihapus: ")
        val nomor = scanner.nextLine().toIntOrNull() ?: 0

        if (nomor > 0 && nomor <= daftarBuku.size) {
            val bukuDihapus = daftarBuku.removeAt(nomor - 1)
            println("Buku '${bukuDihapus.judul}' udah berhasil dihapus pakai method removeAt(). Bye bye buku!")
        } else {
            println("Nomornya salah bro, gagal hapus.")
        }
    }


    fun mulaiTutor() {
        println("\n" + "=".repeat(50))
        println("📚 SELAMAT DATANG DI TUTOR OOP BASIC 📚")
        println("=".repeat(50))
        println("Halo sob! Di sesi ini kita santai aja ya.")
        println("Kita bakal belajar bikin Class biasa. Bayangin Class 'Buku' itu kayak")
        println("cetakan kue, nah objek buku-bukunya itu kuenya yang udah jadi.")
        println("Kita juga pake ArrayList buat nyimpen buku-buku ini biar gampang diatur (CRUD).")

        var isRunning = true
        while (isRunning) {
            println("\n--- MENU PERPUSTAKAAN MINI ---")
            println("1. Lihat Daftar Buku")
            println("2. Tambah Buku")
            println("3. Edit Buku")
            println("4. Hapus Buku")
            println("5. Keluar ke Menu Utama")
            print("Pilih menu (1-5): ")

            when (scanner.nextLine()) {
                "1" -> lihatBuku()
                "2" -> tambahBuku()
                "3" -> editBuku()
                "4" -> hapusBuku()
                "5" -> {
                    println("Sip, keluar dari tutor Basic...")
                    isRunning = false
                }

                else -> println("Pilihan nggak valid bro. Coba lagi ya!")
            }
        }
    }
}
