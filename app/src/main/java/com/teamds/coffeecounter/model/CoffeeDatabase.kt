package com.teamds.coffeecounter.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = arrayOf(CoffeeData::class), version = 1)
@TypeConverters(Converters::class)
    abstract class CoffeeDatabase : RoomDatabase() {
        abstract fun coffeeDao(): CoffeeDao

    companion object {

        private var instance: CoffeeDatabase? = null

        @Synchronized
        fun getInstance(context: Context): CoffeeDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext,
                    CoffeeDatabase::class.java, "coffee_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}