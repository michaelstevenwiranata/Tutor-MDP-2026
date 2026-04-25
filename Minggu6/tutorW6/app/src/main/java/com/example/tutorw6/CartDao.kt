package com.example.tutorw6

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Query("SELECT * FROM cart")
    fun getAllCartItems(): Flow<List<CartItem>>

    @Query("SELECT * FROM cart WHERE breadId = :breadId LIMIT 1")
    suspend fun getCartItemByBreadId(breadId: Int): CartItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartItem: CartItem)

    @Update
    suspend fun update(cartItem: CartItem)

    @Delete
    suspend fun delete(cartItem: CartItem)

    @Query("DELETE FROM cart")
    suspend fun clearCart()
}