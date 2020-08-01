package com.teamds.coffeecounter.fragment.report

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.databinding.FragmentDayBinding
import org.koin.android.ext.android.bind
import java.time.LocalDate


class DayFragment : Fragment() {

    lateinit var binding : FragmentDayBinding
    var year : Int = LocalDate.now().year
    var month : Int = LocalDate.now().month.value
    var dayOfMonth : Int = LocalDate.now().dayOfMonth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDayBinding.inflate(layoutInflater)

        //------------Calender---------------//
        binding.textSelectedDate.text = "${year}년 ${month}월 ${dayOfMonth}일"

        binding.btnCalender.setOnClickListener {
            showCalender()
        }


        return binding.root
    }

    fun showCalender(){
        val dialog = DatePickerDialog(this.requireContext(),
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                this.year = year
                this.month = month + 1
                this.dayOfMonth = dayOfMonth

                binding.textSelectedDate.text = "${this.year}년 ${this.month}월 ${this.dayOfMonth}일"

            },year,month-1,dayOfMonth)

        dialog.show()
    }
}