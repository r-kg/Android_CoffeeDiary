package com.teamds.coffeecounter.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = arrayOf(CoffeeData::class), version = 1)
@TypeConverters(Converters::class)
    abstract class CoffeeDatabase : RoomDatabase() {
        abstract fun coffeeDao(): CoffeeDao
    }