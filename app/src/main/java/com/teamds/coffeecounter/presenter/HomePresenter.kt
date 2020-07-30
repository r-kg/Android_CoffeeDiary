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
    private val localDateNow: LocalDate = LocalDate.now()

    fun updateCountText(context: Context){

        val todayData : DailyData? = DailyDatabase.getInstance(context)?.dailyDao()?.getTodayData(localDateNow)

        if(todayData == null){
            val index = DailyDatabase.getInstance(context)?.dailyDao()?.getCount()!!

            DailyDatabase.getInstance(context)?.dailyDao()?.insert(DailyData(localDateNow,index,0,0))
            view.setCountText(0,0)
        }
        else{
            view.setCountText(todayData.coffee, todayData.caffeine)
        }
    }

    fun insertCoffeeData(context: Context, coffee : Int, size : Int, shot : Int){

        val localDateTimeNow = LocalDateTime.now()
        val dailyDataToday : DailyData = DailyDatabase.getInstance(context)?.dailyDao()?.getTodayData(localDateNow)!!

        var caffeine : Int = when(coffee){

        }

        caffeine += when(size){
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
                localDateTimeNow,
                coffee,
                size,
                shot,
                caffeine
            )
        )

        DailyDatabase.getInstance(context)?.dailyDao()?.insert(DailyData(localDateNow, dailyDataToday.index, dailyDataToday.coffee+1, dailyDataToday.caffeine+caffeine))
        view.setCountText(dailyDataToday.coffee+1, dailyDataToday.caffeine+caffeine)
    }

    interface View{
        fun setCountText(coffee : Int, caffeine: Int)
    }

}