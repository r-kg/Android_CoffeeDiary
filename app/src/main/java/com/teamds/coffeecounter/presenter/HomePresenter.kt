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
        val index = coffee - 2131362045

        var caffeine : Int = when(index){
            0 -> 150
            1 -> 90
            2 -> 75
            3 -> 200
            4 -> 0
            5 -> 80
            else -> 150
        }

        caffeine += when(size){
            3,4 -> 75
            else -> 0
        }

        caffeine += 75 * shot

        CoffeeDatabase.getInstance(context)?.coffeeDao()?.insert(
            CoffeeData(
                0,
                localDateTimeNow,
                index,
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