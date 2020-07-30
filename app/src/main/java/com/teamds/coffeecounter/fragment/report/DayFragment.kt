package com.teamds.coffeecounter.fragment.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.databinding.FragmentDayBinding
import org.koin.android.ext.android.bind


class DayFragment : Fragment() {

    lateinit var binding : FragmentDayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDayBinding.inflate(layoutInflater)

        

        return binding.root
    }
}