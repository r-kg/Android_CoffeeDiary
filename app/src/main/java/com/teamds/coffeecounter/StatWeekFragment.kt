package com.teamds.coffeecounter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamds.coffeecounter.databinding.FragmentStatChildBinding

/**
 * A simple [Fragment] subclass.
 */
class StatWeekFragment : Fragment() {

    private lateinit var binding : FragmentStatChildBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStatChildBinding.inflate(layoutInflater)

        binding.textView2.text = "Week Fragment"


        return binding.root
    }

}
