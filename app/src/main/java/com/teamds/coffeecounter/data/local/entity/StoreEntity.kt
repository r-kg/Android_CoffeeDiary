package com.teamds.coffeecounter.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "store")
data class StoreEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val count: Int
) {
}