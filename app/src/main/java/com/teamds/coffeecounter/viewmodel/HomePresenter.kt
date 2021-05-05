package com.teamds.coffeecounter.viewmodel

import android.content.Context
import com.teamds.coffeecounter.model.coffeedb.CoffeeData
import com.teamds.coffeecounter.data.local.CoffeeDatabase
import com.teamds.coffeecounter.model.dailydb.DailyData
import com.teamds.coffeecounter.data.local.DailyDatabase
import java.time.LocalDate
import java.time.LocalDateTime


class HomePresenter(v: View){
    private val view : View = v

    fun setCount(context: Context){

        val currentDate: LocalDate = LocalDate.now()
        val todayData : DailyData? = DailyDatabase.getInstance(context)?.dailyDao()?.getTodayData(currentDate)

        todayData?.let{
            view.setCountText(todayData.coffee, todayData.caffeine)
        }?:{
            val index = DailyDatabase.getInstance(context)?.dailyDao()?.getCount()!!

            DailyDatabase.getInstance(context)?.dailyDao()?.insert(DailyData(currentDate,index,0,0))
            view.setCountText(0,0)
        }()
    }

    fun insertCoffeeData(context: Context, coffee : Int, size : Int, shot : Int){

        val currentDateTime = LocalDateTime.now()
        val todayData : DailyData = DailyDatabase.getInstance(context)?.dailyDao()?.getTodayData(currentDateTime.toLocalDate())!!
        val index = coffee - 2131362043

        //커피 종류별 카페인
        var caffeine : Int = when(index){
            0 -> 150
            1 -> 90
            2 -> 75
            3 -> 200
            4 -> 0
            5 -> 80
            else -> 150
        }

        //커피 사이즈별 카페인
        caffeine += when(size){
            3,4 -> 75
            else -> 0
        }

        //커피 샷별 카페인
        caffeine += 75 * shot

        CoffeeDatabase.getInstance(context)?.coffeeDao()?.insert(CoffeeData(0, currentDateTime, index, size, shot, caffeine))
        DailyDatabase.getInstance(context)?.dailyDao()?.insert(DailyData(currentDateTime.toLocalDate(), todayData.index, todayData.coffee+1, todayData.caffeine+caffeine))

        //Update View
        view.setCountText(todayData.coffee+1, todayData.caffeine+caffeine)
    }


    /*------------------ V - P Contract-------------------*/
    interface View{
        fun setCountText(coffee : Int, caffeine: Int)
    }
}