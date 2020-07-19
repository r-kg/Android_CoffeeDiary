package com.teamds.coffeecounter.presenter

import android.content.Context
import com.github.mikephil.charting.data.Entry
import com.teamds.coffeecounter.model.dailydb.DailyDatabase
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ReportPresenter(v : View) {

    private val view : View = v


    fun getChartEntry(context: Context, type: String) : List<Entry>{
        val entries = mutableListOf<Entry>()
        var targetDate = LocalDate.now()
        var entryNumber = 6

        /*
        var entryNumber = DailyDatabase.getInstance(context)?.dailyDao()?.getCount()!! - 1
        if(entryNumber > 6) entryNumber = 6
         */

        while(true){
            val dailyData = DailyDatabase.getInstance(context)?.dailyDao()?.getTodayData(targetDate)

            if(entryNumber < 0) break

            if(dailyData == null){
                entries.add(0,Entry(entryNumber.toFloat(),0f))
            }
            else
            {
                when(type){
                    "coffee" -> entries.add(0,Entry(entryNumber.toFloat(),dailyData.coffee.toFloat()))
                    "caffeine" -> entries.add(0,Entry(entryNumber.toFloat(),dailyData.caffeine.toFloat()))
                }
            }

            entryNumber--
            targetDate = targetDate.minusDays(1)
        }

        return entries
    }

    fun getChartLabel(count : Int) : List<String>{
        val targetDate = LocalDate.now()
        val labels = mutableListOf<String>("그저께","어제","오늘")
        val formatter = DateTimeFormatter.ofPattern("MM/dd")

        for(i in 3..count){
            val label = targetDate.minusDays(i.toLong()).format(formatter)
            labels.add(0,label)
        }

        return labels
    }

    fun updateAvgText(context: Context, type: String){

        val avg = when(type){
            "Coffee"->DailyDatabase.getInstance(context)?.dailyDao()?.getAvgCoffee()
            "Caffeine"->DailyDatabase.getInstance(context)?.dailyDao()?.getAvgCaffeine()
            else -> 0f
        }

        view.updateAVG(avg!!)
    }


    interface View{

        fun updateAVG(avg : Float)

        interface Cup : View {
        }

        interface Caffeine : View {
        }
    }
}