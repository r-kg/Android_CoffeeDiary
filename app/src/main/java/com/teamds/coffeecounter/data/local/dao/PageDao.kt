package com.teamds.coffeecounter.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.teamds.coffeecounter.data.local.entity.PageEntity

@Dao
interface PageDao {
    @Query("SELECT * FROM page")
    fun getAll(): List<PageEntity>

    @Insert
    fun insert(item: PageEntity)

    @Delete
    fun delete(item: PageEntity)
}