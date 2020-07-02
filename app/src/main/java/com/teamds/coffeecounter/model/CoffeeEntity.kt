package com.teamds.coffeecounter.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.*

@Entity
data class CoffeeData (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val date: Date,
    val type : Int,
    val caffeine : Int
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