package com.example.tutorw5

data class Reaction(
    val id: Int,
    val name: String,
    val element1: Element,
    val element2: Element,
    val description: String
)