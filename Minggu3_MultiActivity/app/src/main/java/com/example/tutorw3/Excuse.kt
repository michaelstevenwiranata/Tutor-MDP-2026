package com.example.tutorw3

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Excuse(
    var id: String,
    var judul: String,
    var emoji: String,
    var deskripsi: String,
    var tingkatRisiko: String // Aman, Bahaya, Kritis
) : Parcelable
