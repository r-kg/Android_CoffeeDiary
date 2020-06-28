package com.teamds.coffeecounter.presenter

import com.teamds.coffeecounter.database.CoffeeDao
import com.teamds.coffeecounter.database.CoffeeDatabase
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomePresenter(view: View) : KoinComponent {
    val coffeeDB : CoffeeDatabase by inject()
    val coffeeDao : CoffeeDao by inject()
    lateinit var view : View

    fun test(){
        view.updateInfo("2","500")
    }

    interface View{
        fun updateInfo(count: String, caffeine: String)
    }

}