package com.teamds.coffeecounter.stat

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.databinding.FragmentStatCoffeePieBinding

/**
 * A simple [Fragment] subclass.
 */
class StatCoffeePieFragment : Fragment() {

    private lateinit var binding : FragmentStatCoffeePieBinding
    private lateinit var pieChart : PieChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStatCoffeePieBinding.inflate(layoutInflater)
        binding.textView2.text = "Daily Pie"

        pieChart = binding.dayPiechart


        pieChart.apply{
            description=null
            holeRadius=55f
            centerText="이번달\n 종류별 커피 소비량"
            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
            setEntryLabelTextSize(11f)
            setCenterTextSize(13f)
            setEntryLabelColor(Color.BLACK)
            animateY(1000, Easing.EaseOutCubic)
        }

        val entry = mutableListOf<PieEntry>()

        //test-code//
        entry.add(PieEntry(5f,"아메리카노"))
        entry.add(PieEntry(2f,"라떼"))
        entry.add(PieEntry(2f,"카푸치노"))
        entry.add(PieEntry(2f,"마키아또"))
        entry.add(PieEntry(2f,"드링크"))
        entry.add(PieEntry(2f,"홍차류"))
        //test-code//


        val pieDataSet = PieDataSet(entry,"")
        val chartColors = ColorTemplate.COLORFUL_COLORS.toMutableList()

        chartColors.apply {
            add(ContextCompat.getColor(requireContext(),
                R.color.blue
            ))
        }

        pieDataSet.apply {
            sliceSpace = 2f
            selectionShift = 5f
            colors = chartColors
            yValuePosition=PieDataSet.ValuePosition.OUTSIDE_SLICE
            valueTextSize = 11f
            valueFormatter = object : ValueFormatter(){
                override fun getFormattedValue(value: Float): String {
                    return value.toInt().toString() + "잔"
                }
            }
        }


        val pieData = PieData(pieDataSet)
        pieData.setValueTextSize(10f)
        pieData.setValueFormatter(object : ValueFormatter(){
            override fun getFormattedValue(value: Float): String {
                return value.toInt().toString() + "잔"
            }
        })

        pieChart.data = pieData
        pieChart.invalidate()

        return binding.root
    }


}
