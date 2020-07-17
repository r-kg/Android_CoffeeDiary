package com.teamds.coffeecounter

import android.app.Application
import androidx.room.Room
import com.teamds.coffeecounter.model.coffeedb.CoffeeDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val dbModule = module {
            single {
                val db =Room.databaseBuilder(androidApplication(), CoffeeDatabase::class.java, "coffee-db").build()
            }
        }

        startKoin {
            androidContext(this@MyApplication)
            modules(dbModule)
        }


    }
}