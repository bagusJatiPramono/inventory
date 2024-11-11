package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/*
 * DAO merupakan interface yang berhubungan dengan database.
 * DAO membutuhkan @Dao agar dapat diliihat sebagai DAO
 * DAO memudahkan transaksi dengan database
 * DAO memiliki fungsi2 umum seperti Insert, Update, Delete
 * DAO juga dapat melakukan Query manual
 */

@Dao

interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>
}