package com.example.tutorw3

// Mock Database untuk menyimpan List sementara di Memory
class MockDB {
    companion object {
        var excuseList = arrayListOf<Excuse>()

        // Fungsi untuk mengisi data awal (Dummy Data)
        fun initData() {
            if (excuseList.isEmpty()) {
                excuseList.add(Excuse("1", "Ban Bocor di Jalan", "🏍️", "Klasik, tapi butuh foto ban orang lain sebagai bukti.", "Aman"))
                excuseList.add(Excuse("2", "Hujan Badai", "🌧️", "Hanya berlaku jika dosennya juga kehujanan.", "Aman"))
                excuseList.add(Excuse("3", "Kucing Melahirkan", "🐈", "Dosen pecinta hewan pasti luluh dengan alasan ini.", "Aman"))
                excuseList.add(Excuse("4", "Panggilan Alam (Diare)", "🚽", "Tidak ada yang berani mempertanyakan penderitaan ini.", "Bahaya"))
                excuseList.add(Excuse("5", "Diculik Alien", "👽", "Hanya gunakan jika kamu siap di-DO atau dikirim ke psikiater.", "Kritis"))
            }
        }
    }
}