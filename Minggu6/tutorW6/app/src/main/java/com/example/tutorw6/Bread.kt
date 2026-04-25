package com.example.tutorw6

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breads")
data class Bread(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val emoji: String,
    val detail: String
)