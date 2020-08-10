package com.teamds.coffeecounter.model.coffeedb

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

    @Query("DELETE FROM CoffeeData")
    fun reset()

    @Query("SELECT * FROM CoffeeData WHERE Date LIKE :currentDate")
    fun getCoffeeDataByDate(currentDate : String) : List<CoffeeData>
}