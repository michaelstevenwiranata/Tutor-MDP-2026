import com.example.tutor_m4.Mahasiswa
import com.example.tutor_m4.R

object MockDatabase {

    val mahasiswaList = mutableListOf(
        Mahasiswa(
            "Yoga Pramana Syahputra Teguh",
            "222117068",
            "Informatika",
            foto = R.drawable.yogay
        ),
        Mahasiswa(
            "Michael Steven",
            "222117055",
            "Sistem Informasi",
            foto = R.drawable.yogay
        ),
        Mahasiswa(
            "Ivan Santoso",
            "222118595",
            "Informatika",
            foto = R.drawable.yogay
        )
    )

    fun getMahasiswa(): List<Mahasiswa> {
        return mahasiswaList
    }

    fun addMahasiswa(mahasiswa: Mahasiswa) {
        mahasiswaList.add(mahasiswa)
    }
}