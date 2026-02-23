package com.example.tutorw1

abstract class SpacePart (val nama: String, val deskripsi: String){
    fun getFungsiPart(): String{
        return "Fungsi mesin ini adalah "+ deskripsi;
    }
}
