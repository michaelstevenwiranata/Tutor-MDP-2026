package com.example.tutorw6

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartItem(
    @PrimaryKey
    val breadId: Int,
    val name: String,
    val emoji: String,
    val quantity: Int
)