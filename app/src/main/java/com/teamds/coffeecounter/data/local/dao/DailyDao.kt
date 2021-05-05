package com.teamds.coffeecounter.data.local.dao

import androidx.room.*
import com.teamds.coffeecounter.model.dailydb.DailyData
import java.time.LocalDate

@Dao
interface DailyDao {
    @Query("SELECT * FROM DailyData")
    fun getAll(): List<DailyData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dailyData: DailyData)

    @Delete
    fun delete(dailyData: DailyData)

    @Query("SELECT * FROM DailyData WHERE date = :currentDate")
    fun getTodayData(currentDate : LocalDate): DailyData

    @Query("SELECT COUNT(Id) FROM DailyData")
    fun getCount(): Int

    @Query("SELECT AVG(Coffee) FROM DailyData")
    fun getAvgCoffee() : Float

    @Query("SELECT AVG(Caffeine) FROM DailyData")
    fun getAvgCaffeine() : Float

    @Query("DELETE FROM DailyData")
    fun reset()

}