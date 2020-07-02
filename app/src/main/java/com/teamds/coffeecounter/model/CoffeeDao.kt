package com.teamds.coffeecounter.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CoffeeDao {
    @Query("SELECT * FROM CoffeeData")
    fun getAll(): List<CoffeeData>

    @Insert
    fun insert(coffeeData: CoffeeData)

    @Delete
    fun delete(coffeeData: CoffeeData)
}