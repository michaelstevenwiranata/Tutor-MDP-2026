package com.example.tutorw7

import java.io.Serializable

data class Bucin(
    val id: Int = 0,
    val pasangan1: String,
    val pasangan2: String,
    val tingkatAnomali: String,
    val deskripsi: String
) : Serializable