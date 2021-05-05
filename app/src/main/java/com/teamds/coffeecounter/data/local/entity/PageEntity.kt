package com.teamds.coffeecounter.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "page")
data class PageEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @ColumnInfo
    val shopId: Int,
    @ColumnInfo
    val coffeeId: Int,
    @ColumnInfo
    val imageUrl: String,
    @ColumnInfo
    val timestamp: String,
    @ColumnInfo
    val star: Int,
    ){
}