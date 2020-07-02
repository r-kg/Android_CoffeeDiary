package com.teamds.coffeecounter.presenter

import com.teamds.coffeecounter.model.CoffeeDao
import com.teamds.coffeecounter.model.CoffeeDatabase
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainPresenter(v: View) : KoinComponent {
    val coffeeDB : CoffeeDatabase by inject()
    val coffeeDao : CoffeeDao by inject()
    val view : View = v

    fun test(){
    }

    interface View{
    }

}