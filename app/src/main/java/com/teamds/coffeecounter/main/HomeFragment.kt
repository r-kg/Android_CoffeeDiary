package com.teamds.coffeecounter.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.teamds.coffeecounter.HomeStatus
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.databinding.FragmentHomeBinding
import com.teamds.coffeecounter.stat.StatCoffeePieFragment
import com.teamds.coffeecounter.stat.StatDayPieFragment
import com.teamds.coffeecounter.stat.StatWeeklyFragment

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)

        val adapter = ViewPagerAdapter(this.requireActivity())
        binding.homeViewpager.adapter = adapter
        binding.homeViewpager.isUserInputEnabled=false


        return binding.root
    }

    private inner class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa){
        override fun createFragment(position: Int): Fragment {
            return when(position){
                0 -> HomeStatus()
                else -> HomeStatus()
            }
        }
        override fun getItemCount(): Int = 1
    }

}
