package com.teamds.coffeecounter.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.time.LocalDateTime

@Entity
data class CoffeeData (
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "Date") var date: LocalDateTime,
    @ColumnInfo(name = "Type") var coffee : Int,
    @ColumnInfo(name = "Size") var size : Int,
    @ColumnInfo(name = "Shot") var shot : Int,
    @ColumnInfo(name = "Caffeine") var caffeine : Int
)


class Converters {
    @TypeConverter
    fun fromTimestamp(value: String?): LocalDateTime? {
        return value?.let {  LocalDateTime.parse(value) }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): String? {
        return date.toString()
    }
}
