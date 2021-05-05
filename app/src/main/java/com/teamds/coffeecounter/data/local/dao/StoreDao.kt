package com.teamds.coffeecounter.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.teamds.coffeecounter.data.local.entity.StoreEntity

@Dao
interface StoreDao {
    @Query("SELECT * FROM store")
    fun getAll(): List<StoreEntity>

    @Insert
    fun insert(item: StoreEntity)

    @Delete
    fun delete(item: StoreEntity)
}