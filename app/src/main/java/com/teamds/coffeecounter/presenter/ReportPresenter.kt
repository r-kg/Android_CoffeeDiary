package com.teamds.coffeecounter.presenter

import android.content.Context
import android.util.Log
import androidx.preference.Preference
import androidx.preference.PreferenceManager
import com.github.mikephil.charting.data.Entry
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.model.dailydb.DailyDatabase
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.absoluteValue
import kotlin.time.seconds

open class ReportPresenter(v : View) {

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

        if(type == "Caffeine"){
            val pref = PreferenceManager.getDefaultSharedPreferences(context)
            val v = view as View.Caffeine
            val recommendedCaf = when(pref.getString("list_caffeine","")){
                "0" -> 400
                "1" -> 150
                "2" -> 200
                else -> 400
            }

            val result = recommendedCaf - avg

            if(result >= 0){
                v.updateAvgRecommend(result,"낮음",recommendedCaf, R.color.white_green)
            }
            else{
                val indicateColor : Int = if(result.absoluteValue <=100) R.color.white_yellow
                else R.color.white_red
                v.updateAvgRecommend(result.absoluteValue,"높음",recommendedCaf,indicateColor)
            }

        }
    }


    fun updateMaxAvgDayText(context: Context){
        val dailyDataList = DailyDatabase.getInstance(context)?.dailyDao()?.getAll()!!
        val dayAvg : HashMap<Int, Float> = HashMap()
        val dayCount = arrayOf(0,0,0,0,0,0,0)

        for(dailyData in dailyDataList){
            val day = dailyData.date.dayOfWeek.ordinal
            var value = dayAvg.getOrDefault(day, 0f)

            value+=dailyData.coffee
            dayCount[day]++;

            dayAvg[day] = value/dayCount[day]
        }

        val resultIndex = dayAvg.toList().sortedWith(compareByDescending{it.second}).toMap().keys.stream().findFirst().get()
        val result = when(resultIndex){
            0 -> "월"
            1 -> "화"
            2 -> "수"
            3 -> "목"
            4 -> "금"
            5 -> "토"
            6 -> "일"
            else -> "-"
        }

        val v = view as View.Cup
        v.updateMaxDay(result, dayAvg[resultIndex]!!)
    }


    interface View{

        fun updateAVG(avg : Float)

        interface Cup : View {
            fun updateMaxDay(day : String, avg : Float)
        }

        interface Caffeine : View {
            fun updateAvgRecommend(value: Float, desc: String, recommend : Int, indicator: Int)
        }
    }
}