package com.teamds.coffeecounter.ui

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamds.coffeecounter.adapter.CoffeeDataRecyclerAdapter
import com.teamds.coffeecounter.databinding.FragmentDayBinding
import java.time.LocalDate


class DayFragment : Fragment() {

    lateinit var binding : FragmentDayBinding
    var year : Int = LocalDate.now().year
    var month : Int = LocalDate.now().month.value
    var dayOfMonth : Int = LocalDate.now().dayOfMonth

    var layoutManager : RecyclerView.LayoutManager? = null
    var adapter : RecyclerView.Adapter<CoffeeDataRecyclerAdapter.ViewHolder>? = null


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var date : String = LocalDate.of(this.year,this.month, this.dayOfMonth).toString()+"%"
        var dateCoffeeList = CoffeeDatabase.getInstance(requireContext())?.coffeeDao()?.getCoffeeDataByDate(date)
        var recyclerAdapter = CoffeeDataRecyclerAdapter(dateCoffeeList!!)

        binding.recyclerCoffeeData.apply{
            layoutManager = LinearLayoutManager(activity)
            adapter = recyclerAdapter
        }
    }

    fun showCalender(){
        val dialog = DatePickerDialog(this.requireContext(),
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                this.year = year
                this.month = month + 1
                this.dayOfMonth = dayOfMonth

                binding.textSelectedDate.text = "${this.year}년 ${this.month}월 ${this.dayOfMonth}일"

                var date : String = LocalDate.of(this.year,this.month, this.dayOfMonth).toString()+"%"
                var dateCoffeeList = CoffeeDatabase.getInstance(requireContext())?.coffeeDao()?.getCoffeeDataByDate(date)

                var adapter = binding.recyclerCoffeeData.adapter as CoffeeDataRecyclerAdapter
                adapter.setRecyclerList(dateCoffeeList!!)
                adapter.notifyDataSetChanged()


            },year,month-1,dayOfMonth)

        dialog.show()
    }
}