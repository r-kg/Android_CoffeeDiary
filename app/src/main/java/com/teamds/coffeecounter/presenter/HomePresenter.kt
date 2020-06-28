package com.teamds.coffeecounter.presenter

import com.teamds.coffeecounter.database.CoffeeDao
import com.teamds.coffeecounter.database.CoffeeDatabase
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomePresenter(v: View) : KoinComponent {
    val coffeeDB : CoffeeDatabase by inject()
    val coffeeDao : CoffeeDao by inject()
    val view : View = v

    fun test(){
        view.updateInfo("99","5040")
    }

    interface View{
        fun updateInfo(count: String, caffeine: String)
    }

}