package com.teamds.coffeecounter.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.teamds.coffeecounter.databinding.FragmentHomeBinding
import com.teamds.coffeecounter.presenter.HomePresenter

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(),HomePresenter.View {

    lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val presenter = HomePresenter(this)

        binding = FragmentHomeBinding.inflate(layoutInflater)

        /*
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
        */


        return binding.root
    }


    override fun updateInfo(count: String, caffeine: String) {
        binding.textTodayCup.text = count
        binding.textTodayCaf.text = caffeine
    }
}
