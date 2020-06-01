package com.teamds.coffeecounter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.teamds.coffeecounter.databinding.FragmentStatCoffeePieBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

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

        //pieChart.setUsePercentValues(true)
        pieChart.description=null

        val entry = mutableListOf<PieEntry>()

        //test-code//
        entry.add(PieEntry(5f,"아메리카노"))
        entry.add(PieEntry(2f,"라떼"))
        entry.add(PieEntry(2f,"카푸치노"))

        //test-code//

        pieChart.animateY(1000, Easing.EaseOutCubic)

        val pieDataSet = PieDataSet(entry,"")
        val chartColors = ColorTemplate.LIBERTY_COLORS.toList()
        pieDataSet.sliceSpace = 2f
        pieDataSet.selectionShift = 5f
        pieDataSet.colors = chartColors


        val pieData = PieData(pieDataSet)
        pieData.setValueTextSize(10f)

        pieChart.data = pieData
        pieChart.invalidate()



        return binding.root
    }


}
