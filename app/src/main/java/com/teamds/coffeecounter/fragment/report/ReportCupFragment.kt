package com.teamds.coffeecounter.fragment.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.databinding.FragmentReportCupBinding
import com.teamds.coffeecounter.presenter.ReportPresenter


class ReportCupFragment : Fragment(), ReportPresenter.View {

    lateinit var binding : FragmentReportCupBinding
    lateinit var lineChart : LineChart
    private lateinit var presenter: ReportPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReportCupBinding.inflate(layoutInflater)
        presenter = ReportPresenter(this)

        //*--------------------hook----------------------*/
        lineChart = binding.linechartCup

        //*------------------chart------------------*/
        val entries =  presenter.getChartEntry(this.requireContext(),"coffee")
        val labels = presenter.getChartLabel(entries.size-1)

        val dataSet = LineDataSet(entries,"잔 수")
        dataSet.color = ContextCompat.getColor(requireContext(),R.color.colorAccent)
        dataSet.lineWidth=2f
        dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        val lineData = LineData(dataSet)
        lineData.setValueTextSize(11.5f)
        lineData.setValueFormatter(object : ValueFormatter(){
            override fun getFormattedValue(value: Float): String {
                return value.toInt().toString()
            }
        })

        lineChart.data = lineData
        lineChart.run{
            animateXY(0,350)
            setScaleEnabled(false)
            setPinchZoom(false)
            description=null
            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP       //Legend는 차트 데이터의 범례를 의미합니다.
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
            //axisLeft.axisMinimum = 0f
            //axisRight.axisMinimum = 0f
            axisRight.setDrawLabels(false)
            xAxis.setDrawGridLines(false)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setLabelCount(entries.size, true)
            xAxis.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return labels[value.toInt()]
                }
            }


        }

        lineChart.invalidate()


        return binding.root
    }

}