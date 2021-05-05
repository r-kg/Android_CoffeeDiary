package com.teamds.coffeecounter.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.teamds.coffeecounter.data.local.entity.CoffeeEntity

@Dao
interface CoffeeDao {
    @Query("SELECT * FROM coffee")
    fun getAll(): List<CoffeeEntity>

    @Insert
    fun insert(item: CoffeeEntity)

    @Delete
    fun delete(item: CoffeeEntity)
}