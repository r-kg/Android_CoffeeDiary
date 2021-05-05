package com.teamds.coffeecounter.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.teamds.coffeecounter.data.local.dao.CoffeeDao
import com.teamds.coffeecounter.data.local.dao.PageDao
import com.teamds.coffeecounter.data.local.dao.StoreDao
import com.teamds.coffeecounter.data.local.entity.PageEntity
import com.teamds.coffeecounter.data.local.entity.StoreEntity

@Database(entities = [PageEntity::class, StoreEntity::class, PageEntity::class],  version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pageDao() : PageDao
    abstract fun storeDao() : StoreDao
    abstract fun coffeeDao() : CoffeeDao

    companion object{
        private var instance : AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context) : AppDatabase?{
            if (instance == null){
                instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }

            return instance
        }
    }
}