package com.teamds.coffeecounter.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.teamds.coffeecounter.data.local.entity.PageEntity

@Dao
interface PageDao {
    @Query("SELECT * FROM page LIMIT (:rowsPerPage) OFFSET (:offset)")
    suspend fun getPageList(offset: Int, rowsPerPage: Int): List<PageEntity>

    @Insert
    suspend fun insert(item: PageEntity)

    @Delete
    suspend fun delete(item: PageEntity)
}