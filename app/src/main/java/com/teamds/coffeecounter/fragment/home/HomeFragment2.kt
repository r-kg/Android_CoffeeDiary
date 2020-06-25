package com.teamds.coffeecounter.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.databinding.FragmentHome2Binding


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment2 : Fragment() {

    lateinit var binding : FragmentHome2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var coffeeId : Int
        binding = FragmentHome2Binding.inflate(layoutInflater)

        binding.radioGroup1.setOnCheckedChangeListener { group, checkedId ->
    binding.radioGroup2.clearCheck()
    when(checkedId){
        R.id.radioButton1 -> coffeeId = 1
        R.id.radioButton2 -> coffeeId = 2
        R.id.radioButton3 -> coffeeId = 3
    }
}

binding.radioGroup2.setOnCheckedChangeListener { group, checkedId ->
    binding.radioGroup1.clearCheck()
    when(checkedId){
        R.id.radioButton4 -> coffeeId = 4
        R.id.radioButton5 -> coffeeId = 5
        R.id.radioButton6 -> coffeeId = 6
    }
}

return binding.root
}




}