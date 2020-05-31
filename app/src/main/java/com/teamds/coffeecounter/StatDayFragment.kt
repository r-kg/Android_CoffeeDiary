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
import com.teamds.coffeecounter.databinding.FragmentStatChildBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass.
 */
class StatDayFragment : Fragment() {

    private lateinit var binding : FragmentStatChildBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStatChildBinding.inflate(layoutInflater)
        binding.textView2.text = "Day Fragment"


        val barChart = binding.barChart

        val entryCups = mutableListOf<BarEntry>()
        //test-code
        entryCups.add(BarEntry(1f,1f))
        entryCups.add(BarEntry(2f,5f))
        entryCups.add(BarEntry(3f,0f))
        entryCups.add(BarEntry(4f,5f))

        //test-code

        val entrySetCups = BarDataSet(entryCups, "잔")
        entrySetCups.axisDependency = YAxis.AxisDependency.LEFT


        val barData = BarData(entrySetCups)
        barData.barWidth = 0.2f


        barChart.data = barData

        barChart.setScaleEnabled(false)
        barChart.setPinchZoom(false)
        barChart.axisRight.axisMinimum = 0f
        barChart.axisLeft.axisMinimum = 0f
        barChart.description=null

        barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        barChart.xAxis.setDrawGridLines(false)
        barChart.xAxis.labelCount = entryCups.size

        barChart.axisRight.setDrawLabels(false)

        barChart.xAxis.valueFormatter = object : ValueFormatter() {
            private val mFormat: SimpleDateFormat = SimpleDateFormat("MMM dd", Locale.KOREA)
            override fun getFormattedValue(value: Float): String {
                val millis: Long = TimeUnit.HOURS.toMillis(value.toLong())
                return mFormat.format(Date(millis))
            }
        }


        barChart.animateXY(0,800)
        barChart.invalidate()

        barChart.legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        barChart.legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT


        return binding.root
    }
}


