package com.example.tutorw1

class LambungPart(nama: String = "Lambung Titanium",
                  deskripsi:String = "Melindungi kapal dari radiasi dan tabrakan meteor."
) : SpacePart(nama, deskripsi), Body {
    override val material: String
        get() = "Titanium"

    override fun giveDecal(decalName: String) {
        println("Berhasil menambahkan decal: "+ decalName)
    }
}