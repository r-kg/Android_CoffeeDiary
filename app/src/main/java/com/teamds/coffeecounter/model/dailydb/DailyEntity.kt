package com.teamds.coffeecounter.model.dailydb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.time.LocalDate

@Entity
data class DailyData (
    @PrimaryKey var date: LocalDate,
    @ColumnInfo(name = "Id") var index : Int,
    @ColumnInfo(name = "Coffee") var coffee : Int,
    @ColumnInfo(name = "Caffeine") var caffeine : Int
)


class Converters {
    @TypeConverter
    fun toDate(dateString: String?): LocalDate? {
        return if (dateString == null) {
            null
        } else {
            LocalDate.parse(dateString)
        }
    }

    @TypeConverter
    fun toDateString(date: LocalDate?): String? {
        return date?.toString()
    }
}
