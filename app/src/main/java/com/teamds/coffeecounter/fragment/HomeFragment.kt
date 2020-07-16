package com.teamds.coffeecounter.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.databinding.FragmentHomeBinding
import com.teamds.coffeecounter.databinding.FragmentReportBinding
import com.teamds.coffeecounter.presenter.HomePresenter
import com.teamds.coffeecounter.presenter.MainPresenter
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*


class HomeFragment : Fragment(), HomePresenter.View {

    lateinit var binding : FragmentHomeBinding
    lateinit var textCoffee : TextView
    lateinit var textCaf : TextView
    private lateinit var presenter: HomePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        /*---------------------Hooks--------------------------*/
        presenter = HomePresenter(this)
        textCoffee = binding.textCoffeeCount
        textCaf = binding.textCaffeineCount



        val date = LocalDate.now()
        binding.textDate.text = "$date"

        return binding.root
    }

}