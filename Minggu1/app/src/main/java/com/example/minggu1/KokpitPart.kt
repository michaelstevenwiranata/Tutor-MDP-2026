package com.example.minggu1

class KokpitPart(nama: String = "Kokpit Navigasi",
                 deskripsi:String = "Pusat kontrol dan kemudi pesawat."
) : SpacePart(nama, deskripsi), Body  {
    override val material: String
        get() = "Besi"

    override fun giveDecal(decalName: String) {
        println("Berhasil menambahkan decal: "+ decalName)
    }
}