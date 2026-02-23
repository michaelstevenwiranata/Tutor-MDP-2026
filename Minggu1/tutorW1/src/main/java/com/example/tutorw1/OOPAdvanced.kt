package com.example.tutorw1

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Scanner

class OOPAdvanced {
    private val inventory = ArrayList<SpacePart>()
    private val history = ArrayList<ScavengeHistory>()
    private val scanner = Scanner(System.`in`)
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    fun mulaiTutor() {
        println("\n" + "=".repeat(50))
        println("🚀 SELAMAT DATANG DI TUTOR OOP ADVANCED 🚀")
        println("=".repeat(50))
        println("Di sini kita naik level! Kita buat game 'Scavenge Spaceship'.")
        println("Konsep utama: INTERFACE. Kita punya interface 'SpacePart'.")
        println("Mesin, Lambung, Kokpit, dan Sayap meng-implementasikan interface tersebut.")
        println("Misi kamu: Kumpulkan minimal 1 Mesin, 1 Lambung, 1 Kokpit, dan 1 Sayap untuk merakit Kapal!")

        var isRunning = true
        while (isRunning) {
            println("\n--- MAIN MENU: SPACESHIP SCAVENGER ---")
            println("1. Scavenge (Cari Part Random)")
            println("2. Lihat Inventory")
            println("3. Lihat History Scavenge")
            println("4. Rakit Spaceship (Cek Kondisi Menang)")
            println("5. Keluar ke Menu Utama")
            print("Command Komandan (1-5): ")

            when (scanner.nextLine()) {
                "1" -> lakukanScavenge()
                "2" -> lihatInventory()
                "3" -> lihatHistory()
                "4" -> {
                    if (cobaRakitKapal()) {
                        println("\n🎉 GAME SELESAI! KAMU MENANG! 🎉")
                        isRunning = false
                    }
                }
                "5" -> {
                    println("Meninggalkan hangar kapal...")
                    isRunning = false
                }
                else -> println("Perintah tidak dimengerti oleh sistem.")
            }
        }
    }

    private fun lakukanScavenge() {
        println("\nSedang mencari puing-puing part di luar angkasa...")
        Thread.sleep(1000) // pura-pura loading biar seru

        // Gacha part menggunakan list dan mengambil elemen random
        val gachaPool = listOf(MesinPart(), LambungPart(), KokpitPart(), SayapPart())
        val partDitemukan = gachaPool.random()

        // Simpan ke inventory
        inventory.add(partDitemukan)

        // Catat history beserta Timestamp
        val waktuSekarang = LocalDateTime.now().format(formatter)
        history.add(ScavengeHistory(partDitemukan, waktuSekarang))

        println("🌟 MANTAP! Kamu menemukan: ${partDitemukan.nama}")
        println("Fungsi: ${partDitemukan.getFungsiPart()}") // Polymorphism beraksi di sini!
    }

    private fun lihatInventory() {
        if (inventory.isEmpty()) {
            println("\nInventory kamu masih kosong. Ayo pergi Scavenge!")
            return
        }

        println("\n--- ISI INVENTORY ---")
        // Kelompokkan part berdasarkan class-nya untuk mempermudah melihat jumlah
        val hitungMesin = inventory.count { it is MesinPart }
        val hitungLambung = inventory.count { it is LambungPart }
        val hitungKokpit = inventory.count { it is KokpitPart }
        val hitungSayap = inventory.count { it is SayapPart }

        println("- Mesin   : $hitungMesin")
        println("- Lambung : $hitungLambung")
        println("- Kokpit  : $hitungKokpit")
        println("- Sayap   : $hitungSayap")
        println("Total Items: ${inventory.size}")

    }

    private fun lihatHistory() {
        if (history.isEmpty()) {
            println("\nBelum ada riwayat scavenge.")
            return
        }

        println("\n--- HISTORY PENDAPATAN PART ---")
        history.forEachIndexed { index, log ->
            println("${index + 1}. [${log.timestamp}] Mendapatkan ${log.part.nama}")
        }
    }

    private fun cobaRakitKapal(): Boolean {
        println("\nMengecek kelengkapan part...")
        val punyaMesin = inventory.any { it is MesinPart }
        val punyaLambung = inventory.any { it is LambungPart }
        val punyaKokpit = inventory.any { it is KokpitPart }
        val punyaSayap = inventory.any { it is SayapPart }

        if (punyaMesin && punyaLambung && punyaKokpit && punyaSayap) {
            println("==================================================")
            println("🚀 ENGINE START... HULL INTEGRITY 100%... 🚀")
            println("Spaceship berhasil dirakit dan siap untuk lepas landas!")
            println("Selamat, mahasiswa! Kamu telah menguasai penggunaan Interface!")
            println("==================================================")
            return true
        } else {
            println("Yah, part kamu belum lengkap. Kamu harus punya setidaknya:")
            println("1 Mesin (${if(punyaMesin) "OK" else "KURANG"})")
            println("1 Lambung (${if(punyaLambung) "OK" else "KURANG"})")
            println("1 Kokpit (${if(punyaKokpit) "OK" else "KURANG"})")
            println("1 Sayap (${if(punyaSayap) "OK" else "KURANG"})")
            println("Yuk Scavenge lagi!")
            return false
        }
    }
}
