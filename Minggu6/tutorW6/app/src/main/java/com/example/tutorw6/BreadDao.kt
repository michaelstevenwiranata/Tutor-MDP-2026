package com.example.tutorw6

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BreadDao {
    @Query("SELECT * FROM breads")
    fun getAllBreads(): Flow<List<Bread>>

    @Query("SELECT * FROM breads WHERE id = :id LIMIT 1")
    suspend fun getBreadById(id: Int): Bread?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bread: Bread)

    @Update
    suspend fun update(bread: Bread)

    @Delete
    suspend fun delete(bread: Bread)
}