package com.teamds.coffeecounter.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coffee")
data class CoffeeEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val count: Int
) {
}