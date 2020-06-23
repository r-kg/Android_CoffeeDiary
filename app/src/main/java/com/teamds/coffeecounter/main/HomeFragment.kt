package com.teamds.coffeecounter.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.teamds.coffeecounter.home.HomeStatusFragment
import com.teamds.coffeecounter.databinding.FragmentHomeBinding
import com.teamds.coffeecounter.home.HomeAddFragment1
import com.teamds.coffeecounter.home.HomeAddFragment2

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
        //binding.homeViewpager.isUserInputEnabled=false

        binding.homeViewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

            }
        })

        return binding.root
    }

    private inner class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa){
        override fun createFragment(position: Int): Fragment {
            return when(position){
                0 -> HomeStatusFragment()
                1 -> HomeAddFragment1()
                2 -> HomeAddFragment2()
                else -> HomeStatusFragment()
            }
        }
        override fun getItemCount(): Int = 3
    }

}
