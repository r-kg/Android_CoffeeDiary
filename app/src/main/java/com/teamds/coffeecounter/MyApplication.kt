package com.teamds.coffeecounter

import android.app.Application
import com.teamds.coffeecounter.data.local.AppDatabase
import com.teamds.coffeecounter.repository.*
import com.teamds.coffeecounter.viewmodel.CameraViewModel
import com.teamds.coffeecounter.viewmodel.HomeViewModel
import com.teamds.coffeecounter.viewmodel.SettingViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@MyApplication)
            modules(viewModelModule, repositoryModule)
        }
    }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { CameraViewModel(get(), get(), get()) }
}

val repositoryModule = module {
    single<PageRepository> { PageRepositoryImpl(AppDatabase.getInstance(androidContext())!!.pageDao()) }
    single<CoffeeRepository> { CoffeeRepositoryImpl(AppDatabase.getInstance(androidContext())!!.coffeeDao()) }
    single<StoreRepository> { StoreRepositoryImpl(AppDatabase.getInstance(androidContext())!!.storeDao()) }
}