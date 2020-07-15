package com.teamds.coffeecounter.presenter

import android.content.Context
import com.teamds.coffeecounter.model.CoffeeDao
import com.teamds.coffeecounter.model.CoffeeData
import com.teamds.coffeecounter.model.CoffeeDatabase
import org.koin.java.KoinJavaComponent.inject


import java.util.*

class MainPresenter(v: View){
    private val view : View = v

    fun InsertCoffeeData(context: Context, coffee : Int, size : Int, shot : Int){

        var currentDate = Calendar.getInstance().time
        var caffeine : Int = when(size){
            0 -> 75
            1 -> 150
            2 -> 225
            3 -> 300
            else -> 150
        }

        caffeine += 70 * shot

        CoffeeDatabase.getInstance(context)?.coffeeDao()?.insert(CoffeeData(0,currentDate , coffee, size, shot, caffeine))
    }

    interface View{
    }

}