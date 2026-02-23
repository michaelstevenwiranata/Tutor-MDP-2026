package com.example.tutorw1

import java.util.Scanner

    fun main() {
        val scanner = Scanner(System.`in`)
        println("===============================================")
        println("🎓 APLIKASI TUTOR KOTLIN OOP UNTUK MAHASISWA 🎓")
        println("===============================================")
        println("Pilih modul yang ingin kamu pelajari:")
        println("1. OOP Basic (Belajar Class, ArrayList, CRUD santai)")
        println("2. OOP Advanced (Belajar Interface dengan Game Scavenge)")
        print("Masukkan pilihan (1/2): ")

        val pilihan = scanner.nextLine()

        // Syarat: "Pada main file, hanya panggil salah satu dari kedua class tersebut."
        // Kita panggil class secara dinamis berdasarkan input user
        if (pilihan == "1") {
            val basicTutor = OOPBasic()
            basicTutor.mulaiTutor()
        } else if (pilihan == "2") {
            val advancedTutor = OOPAdvanced()
            advancedTutor.mulaiTutor()
        } else {
            println("Pilihan tidak valid. Silakan jalankan ulang programnya.")
        }

        println("\nTerima kasih sudah belajar Kotlin OOP hari ini! Tetap semangat ngoding!")
    }
