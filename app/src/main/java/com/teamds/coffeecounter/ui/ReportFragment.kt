package com.teamds.coffeecounter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.teamds.coffeecounter.databinding.FragmentReportBinding


class ReportFragment : Fragment() {

    lateinit var binding : FragmentReportBinding
    lateinit var viewPager2: ViewPager2
    lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReportBinding.inflate(layoutInflater)

        /*---------------------Hooks--------------------------*/
        viewPager2 = binding.reportViewpager
        tabLayout = binding.reportTablayout

        /*---------------Viewpager2--------------*/
        val adapter = ViewPagerAdapter(this.requireActivity())
        viewPager2.adapter = adapter

        val tabLayoutText = arrayOf("날짜별","커피","카페인")

        TabLayoutMediator(tabLayout,viewPager2){ tab, position ->  
            tab.text = tabLayoutText[position]
        }.attach()


        return binding.root
    }

    private inner class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa){
        override fun createFragment(position: Int): Fragment {
            return when(position){
                0 -> DayFragment()
                1 -> ReportCupFragment()
                2 -> ReportCaffeineFragment()
                else -> ReportCupFragment()
            }
        }
        override fun getItemCount(): Int = 3
    }

}