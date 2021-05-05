package com.teamds.coffeecounter.viewmodel

import android.content.Context
import androidx.preference.PreferenceManager
import com.github.mikephil.charting.data.Entry
import com.teamds.coffeecounter.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.collections.HashMap
import kotlin.math.absoluteValue

open class ReportPresenter(v : View) {

    private val view : View = v

    fun getChartEntry(context: Context, type: String) : List<Entry>{
        val entries = mutableListOf<Entry>()
        var date = LocalDate.now()

        for(i in 6 downTo 0){
            val dailyData = DailyDatabase.getInstance(context)?.dailyDao()?.getTodayData(date)
            val index = i.toFloat()

            dailyData?.let {
                when(type){
                    "coffee" -> entries.add(0,Entry(index,dailyData.coffee.toFloat()))
                    "caffeine" -> entries.add(0,Entry(index,dailyData.caffeine.toFloat()))
                }
            }?:{
                entries.add(0,Entry(index.toFloat(),0f))
            }()

            date = date.minusDays(1)
        }

        return entries
    }

    fun getChartLabel(count : Int) : List<String>{
        val labels = mutableListOf<String>("그저께","어제","오늘")
        val date = LocalDate.now()

        for(i in 3..count){
            val label = date.minusDays(i.toLong()).format(DateTimeFormatter.ofPattern("MM/dd"))
            labels.add(0,label)
        }

        return labels
    }

    fun updateAvgText(context: Context, type: String){

        val avg = when(type){
            "Coffee"-> DailyDatabase.getInstance(context)?.dailyDao()?.getAvgCoffee()
            "Caffeine"-> DailyDatabase.getInstance(context)?.dailyDao()?.getAvgCaffeine()
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


    fun setMaxAverageDay(context: Context){
        val dailyDataList = DailyDatabase.getInstance(context)?.dailyDao()?.getAll()!!
        val dayArray = arrayOf("월","화","수","목","금","토","일")
        val dayCount = arrayOf(0,0,0,0,0,0,0)
        val dayAvg : HashMap<Int, Float> = HashMap()

        for(dailyData in dailyDataList){
            val day = dailyData.date.dayOfWeek.ordinal
            var value = dayAvg.getOrDefault(day, 0f)

            value+=dailyData.coffee
            dayCount[day]++;

            dayAvg[day] = value/dayCount[day]
        }

        val index = dayAvg.toList().sortedWith(compareByDescending{it.second}).toMap().keys.stream().findFirst().get()

        //Update view
        val v = view as View.Cup
        v.updateMaxDay(dayArray[index], dayAvg[index]!!)
    }



    /*-------------------V - P Contract-------------------------*/
    interface View{

        // -> Both
        fun updateAVG(avg : Float)

        // -> ReportCupFragment
        interface Cup : View {
            fun updateMaxDay(day : String, avg : Float)
        }

        // -> ReportCaffeineFragment
        interface Caffeine : View {
            fun updateAvgRecommend(value: Float, desc: String, recommend : Int, indicator: Int)
        }
    }
}