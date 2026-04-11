package com.example.tutorw5

class MockDB {
    companion object {
        // Mock data PIN
        const val CURRENT_USER_PIN = "1234"

        // Mendefinisikan element satu per satu agar mudah di-assign ke Reaction
        val anemo = Element(1, "Anemo", "Elemen angin, beresonansi dengan kebebasan.", R.drawable.anemo_element)
        val geo = Element(2, "Geo", "Elemen batu, melambangkan kontrak dan keteguhan.", R.drawable.geo_element)
        val electro = Element(3, "Electro", "Elemen petir, lambang keabadian.", R.drawable.electro_element)
        val dendro = Element(4, "Dendro", "Elemen alam dan tumbuhan, kebijaksanaan.", R.drawable.dendro_element)
        val hydro = Element(5, "Hydro", "Elemen air, keadilan yang murni.", R.drawable.hydro_element)
        val pyro = Element(6, "Pyro", "Elemen api, semangat peperangan.", R.drawable.pyro_element)
        val cryo = Element(7, "Cryo", "Elemen es, cinta yang membeku.", R.drawable.cryo_element)

        // Mock data Elements (Genshin Elements)
        val elements = listOf(anemo, geo, electro, dendro, hydro, pyro, cryo)

        // Mock data Reactions (Bisa ditambah/edit/hapus)
        val reactions = mutableListOf(
            Reaction(1, "Vaporize", hydro, pyro, "Meningkatkan damage serangan."),
            Reaction(2, "Melt", cryo, pyro, "Meningkatkan damage serangan secara masif."),
            Reaction(3, "Overloaded", electro, pyro, "Menghasilkan ledakan area AoE Pyro damage.")
        )
    }
}