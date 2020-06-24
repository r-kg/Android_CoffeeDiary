package com.teamds.coffeecounter.fragment.stat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.teamds.coffeecounter.databinding.FragmentStatWeeklyBinding

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
        entrySetCups.color = ColorTemplate.rgb("#ff7b22")


        val barData = BarData(entrySetCups)
        barData.barWidth = 0.35f

        barChart.apply {
            setScaleEnabled(false)
            setPinchZoom(false)
            animateXY(0,800)
            description=null
            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
            axisLeft.axisMinimum = 0f
            axisRight.axisMinimum = 0f
            axisRight.setDrawLabels(false)
            xAxis.setDrawGridLines(false)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.labelCount = entryCups.size
            xAxis.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return entryLabels[value.toInt()]
                }
            }
        }

        barChart.data = barData
        barChart.invalidate()

        return binding.root

    }
}


