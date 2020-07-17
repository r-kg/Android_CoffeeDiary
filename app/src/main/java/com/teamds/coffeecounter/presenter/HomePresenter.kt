package com.teamds.coffeecounter.presenter

import android.content.Context
import com.teamds.coffeecounter.model.coffeedb.CoffeeData
import com.teamds.coffeecounter.model.coffeedb.CoffeeDatabase
import com.teamds.coffeecounter.model.dailydb.DailyData
import com.teamds.coffeecounter.model.dailydb.DailyDatabase
import java.time.LocalDate
import java.time.LocalDateTime


class HomePresenter(v: View){
    private val view : View = v
    private val currentData: LocalDate = LocalDate.now()

    fun updateCountText(context: Context){

        val todayData : DailyData? = DailyDatabase.getInstance(context)?.dailyDao()?.getTodayData(currentData)

        if(todayData == null){
            val index = DailyDatabase.getInstance(context)?.dailyDao()?.getCount()!!

            DailyDatabase.getInstance(context)?.dailyDao()?.insert(DailyData(currentData,index,0,0))
            view.setCountText(0,0)
        }
        else{
            view.setCountText(todayData.coffee, todayData.caffeine)
        }
    }

    fun InsertCoffeeData(context: Context, coffee : Int, size : Int, shot : Int){

        val currentDate = LocalDateTime.now()
        val todayData : DailyData = DailyDatabase.getInstance(context)?.dailyDao()?.getTodayData(currentData)!!

        var caffeine : Int = when(size){
            0 -> 75
            1 -> 150
            2 -> 225
            3 -> 300
            else -> 150
        }

        caffeine += 70 * shot

        CoffeeDatabase.getInstance(context)?.coffeeDao()?.insert(
            CoffeeData(
                0,
                currentDate,
                coffee,
                size,
                shot,
                caffeine
            )
        )

        DailyDatabase.getInstance(context)?.dailyDao()?.insert(DailyData(currentData, todayData.index, todayData.coffee+1, todayData.caffeine+caffeine))
        view.setCountText(todayData.coffee+1, todayData.caffeine+caffeine)
    }

    interface View{
        fun setCountText(coffee : Int, caffeine: Int)
    }

}