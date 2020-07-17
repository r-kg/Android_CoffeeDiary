package com.teamds.coffeecounter.model.dailydb

import androidx.room.*
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

}