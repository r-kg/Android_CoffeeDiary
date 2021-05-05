package com.teamds.coffeecounter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.databinding.FragmentReportCoffeeBinding
import com.teamds.coffeecounter.viewmodel.ReportPresenter


class ReportCupFragment : Fragment(), ReportPresenter.View.Cup {

    lateinit var binding : FragmentReportCoffeeBinding
    private lateinit var presenter: ReportPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReportCoffeeBinding.inflate(layoutInflater)
        presenter = ReportPresenter(this)

        /*-------------------Stat-------------------*/
        presenter.updateAvgText(this.requireContext(),"Coffee")
        presenter.setMaxAverageDay(this.requireContext())

        //*------------------chart------------------*/
        val entries =  presenter.getChartEntry(this.requireContext(),"coffee")
        val labels = presenter.getChartLabel(entries.size-1)

        drawChart(entries, labels)

        return binding.root
    }

    private fun drawChart(entries: List<Entry>, labels : List<String>){

        val dataSet = LineDataSet(entries,"잔 수")
        dataSet.apply {
            color = ContextCompat.getColor(requireContext(),R.color.colorAccent)
            mode = LineDataSet.Mode.HORIZONTAL_BEZIER
            lineWidth=2f
        }

        val lineData = LineData(dataSet)
        lineData.run {
            setValueTextSize(11.5f)
            setValueFormatter(object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return value.toInt().toString()
                }
            })
        }

        binding.linechart.run{
            data = lineData
            setScaleEnabled(false)
            setPinchZoom(false)
            description=null
            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP       //Legend는 차트 데이터의 범례를 의미합니다.
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
            axisRight.setDrawLabels(false)
            xAxis.setDrawGridLines(false)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setLabelCount(entries.size, true)
            xAxis.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return labels[value.toInt()]
                }
            }

            invalidate()
        }
    }

    override fun updateAVG(avg: Float) {
        val text = "%.2f".format(avg)
        binding.textAvg.text = text

        if(avg >= 2.0) binding.cardAvg.background.setTint(ContextCompat.getColor(this.requireContext(),R.color.white_red))
        else if(avg >=1.6) binding.cardAvg.background.setTint(ContextCompat.getColor(this.requireContext(),R.color.white_yellow))
        else if(avg >= 1.2) binding.cardAvg.background.setTint(ContextCompat.getColor(this.requireContext(),R.color.white_green))
    }

    override fun updateMaxDay(day: String, avg: Float) {

        val text = "%.1f".format(avg)

        binding.stat1Value.text = day
        binding.stat1UnitDesc.text = "평균 $text 잔"
    }

}