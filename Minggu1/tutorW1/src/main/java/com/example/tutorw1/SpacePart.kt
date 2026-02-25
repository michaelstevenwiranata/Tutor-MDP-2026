package com.example.tutorw1

abstract class SpacePart (val nama: String, val deskripsi: String){
    fun getFungsiPart(): String{
        return "Fungsi mesin ini adalah "+ deskripsi;
    }

    companion object Factory {
        fun createSayap(name: String): SayapPart {
            // Can access private constructors/members of User
            return SayapPart(name, "Menjaga kestabilan saat memasuki atmosfer planet.")
        }
        const val DEFAULT_NAME = "Nama" // Example of a constant
    }
}
