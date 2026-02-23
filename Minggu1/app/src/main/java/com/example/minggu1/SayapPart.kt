package com.example.minggu1

class SayapPart (nama: String = "Sayap Aerodinamis",
                 deskripsi:String = "Menjaga kestabilan saat memasuki atmosfer planet."
) : SpacePart(nama, deskripsi), Body {
    override val material: String
        get() = "Emas"

    override fun giveDecal(decalName: String) {
        println("Berhasil menambahkan decal: "+ decalName)
    }
}