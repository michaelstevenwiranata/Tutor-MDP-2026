package com.example.minggu1

import kotlin.random.Random

class MesinPart(nama: String = "Mesin Ion",
                deskripsi:String = "Memberikan tenaga dorong utama (Propulsion)."
) : SpacePart(nama, deskripsi), Engine {
    override fun testEngine(): String {
        return "Engine bekerja dengan baik!"
    }

    override val age: Int = Random.nextInt(0, 100)
}
