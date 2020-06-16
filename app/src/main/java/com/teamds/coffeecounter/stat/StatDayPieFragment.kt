package com.teamds.coffeecounter.stat

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.databinding.FragmentStatDayPieBinding

/**
 * A simple [Fragment] subclass.
 */
class StatDayPieFragment : Fragment() {

    private lateinit var binding : FragmentStatDayPieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStatDayPieBinding.inflate(layoutInflater)

        binding.textView2.text = "Daily Pie"

        val pieChart = binding.dayPiechart
        pieChart.apply {
            description=null
            holeRadius=55f
            centerText="이번달\n 요일별 커피 소비량"
            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
            setCenterTextSize(13f)
            setEntryLabelColor(Color.BLACK)
            animateY(1000, Easing.EaseOutCubic)
        }


        //pieChart.setUsePercentValues(true)

        val entry = mutableListOf<PieEntry>()

        //test-code//
        entry.add(PieEntry(5f,"월"))
        entry.add(PieEntry(2f,"화"))
        entry.add(PieEntry(2f,"수"))
        entry.add(PieEntry(7f,"목"))
        entry.add(PieEntry(1f,"금"))
        entry.add(PieEntry(2f,"토"))
        entry.add(PieEntry(3f,"일"))
        //test-code//


        val pieDataSet = PieDataSet(entry,"")
        val chartColors = ColorTemplate.JOYFUL_COLORS.toMutableList()

        chartColors.apply {
            add(ContextCompat.getColor(requireContext(),
                R.color.pastelBlue
            ))
            add(ContextCompat.getColor(requireContext(),
                R.color.pastelViolet
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
        pieChart.data = pieData
        pieChart.invalidate()

        return binding.root
    }
}
