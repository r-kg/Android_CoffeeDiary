package com.teamds.coffeecounter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
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

        //pieChart.setUsePercentValues(true)
        pieChart.description=null

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

        pieChart.animateY(1000, Easing.EaseOutCubic)

        val pieDataSet = PieDataSet(entry,"")
        val chartColors = ColorTemplate.COLORFUL_COLORS.toMutableList()
        chartColors.add(R.color.pastelBlue)
        chartColors.add(R.color.pastelViolet)
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
