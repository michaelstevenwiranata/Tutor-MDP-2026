package com.example.tutor_m4

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Mahasiswa(
    val nama: String,
    val nrp: String,
    val jurusan: String,
    val foto: Int = R.drawable.yogay
) : Parcelable
