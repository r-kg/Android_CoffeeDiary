package com.teamds.coffeecounter

import android.app.Application
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import androidx.room.Room
import com.teamds.coffeecounter.database.CoffeeDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val dbModule = module {
            single {
                Room.databaseBuilder(androidApplication(), CoffeeDatabase::class.java, "coffee-db").build()
            }
            single {
                get<CoffeeDatabase>().coffeeDao()
            }
        }

        startKoin {
            androidContext(this@MyApplication)
            modules(dbModule)
        }


    }
}