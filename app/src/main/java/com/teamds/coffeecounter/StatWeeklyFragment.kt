package com.teamds.coffeecounter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.teamds.coffeecounter.databinding.FragmentStatWeeklyBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass.
 */
class StatWeeklyFragment : Fragment() {

    private lateinit var binding : FragmentStatWeeklyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStatWeeklyBinding.inflate(layoutInflater)
        binding.textView2.text = "Weekly"

        val barChart = binding.barChart

        val entryCups = mutableListOf<BarEntry>()
        val entryLabels = mutableListOf<String>("6일전","5일전","4일전","3일전","그저께","어제","오늘")
        //test-code
        entryCups.add(BarEntry(0f,1f))
        entryCups.add(BarEntry(1f,5f))
        entryCups.add(BarEntry(2f,0f))
        entryCups.add(BarEntry(3f,2f))
        entryCups.add(BarEntry(4f,3f))
        entryCups.add(BarEntry(5f,6f))
        entryCups.add(BarEntry(6f,1f))

        //test-code

        val entrySetCups = BarDataSet(entryCups, "잔")
        entrySetCups.color = ColorTemplate.rgb("#9bc01c")


        val barData = BarData(entrySetCups)
        barData.barWidth = 0.35f


        barChart.data = barData

        barChart.setScaleEnabled(false)
        barChart.setPinchZoom(false)
        //barChart.setFitBars(true)
        barChart.axisRight.axisMinimum = 0f
        barChart.axisLeft.axisMinimum = 0f
        barChart.description=null

        barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        barChart.xAxis.setDrawGridLines(false)
        barChart.xAxis.labelCount = entryCups.size

        barChart.xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return entryLabels[value.toInt()]
            }
        }


        barChart.axisRight.setDrawLabels(false)



        barChart.animateXY(0,800)
        barChart.invalidate()

        barChart.legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        barChart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT


        return binding.root


    }

}


