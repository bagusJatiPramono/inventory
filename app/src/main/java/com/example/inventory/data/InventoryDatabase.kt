package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*
 * Database menyimpan entitas
 * saat inisialisasi Database, harus memasukkan entitas yang dimasukkan, versi, dan exportschema agar tidak menyimpan skema lama
 * companion objek berfungsi menyediakan akses operasi database menggunakan classname
 * instance agar hanya 1 objek database yang ada.
 * syncronize berfungsi untuk menangani race condition
 * database builder untuk membangun database, harus dibangun dengan .build
 */

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
    companion object{
        @Volatile
        private var Instance: InventoryDatabase? = null

        fun getDatabase(context: Context): InventoryDatabase{
            return Instance?:synchronized(this){
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database").build().also { Instance = it }
            }
        }
    }
}