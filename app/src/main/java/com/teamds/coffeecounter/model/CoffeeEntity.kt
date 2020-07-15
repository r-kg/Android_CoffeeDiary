package com.teamds.coffeecounter.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.*

@Entity
data class CoffeeData (
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "Date") var date: Date,
    @ColumnInfo(name = "Type") var coffee : Int,
    @ColumnInfo(name = "Size") var size : Int,
    @ColumnInfo(name = "Shot") var shot : Int,
    @ColumnInfo(name = "Caffeine") var caffeine : Int
)


class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}
