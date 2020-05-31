package com.teamds.coffeecounter

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.teamds.coffeecounter.databinding.FragmentStatBinding

/**
 * A simple [Fragment] subclass.
 */
class StatFragment : Fragment() {

    lateinit var binding: FragmentStatBinding
    lateinit var dwmTextArray : Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentStatBinding.inflate(layoutInflater)

        val adapter = ViewPagerAdapter(this.requireActivity())
        binding.statPager.adapter = adapter

        ///------------------------------------------------------------///
        /*
        binding.statPager.apply {
            clipToPadding = false   // allow full width shown with padding
            clipChildren = false    // allow left/right item is not clipped
            offscreenPageLimit = 2  // make sure left/right item is rendered
        }

        // increase this offset to show more of left/right
        val offsetPx = 13.dpToPx(resources.displayMetrics)
        binding.statPager.setPadding(offsetPx, offsetPx, offsetPx, offsetPx)

        // increase this offset to increase distance between 2 items
        val pageMarginPx = 2.dpToPx(resources.displayMetrics)
        val marginTransformer = MarginPageTransformer(pageMarginPx)
        binding.statPager.setPageTransformer(marginTransformer)
        */

        ///------------------------------------------------------------///

        binding.statCkboxCup.isChecked = true
        binding.statCkboxCaf.isChecked = true

        var fragPosition : Int = 0;
        val dwmTextArray = arrayOf("일간","주간","월간")

        val clickListener = View.OnClickListener { view ->
            when(view){
                binding.statDwmButtonLeft ->{
                     if(fragPosition > 0 ){
                         fragPosition --;
                     }
                }
                binding.statDwmButtonRight -> {
                    if(fragPosition < 2){
                        fragPosition ++;
                    }
                }
            }

            binding.statPager.setCurrentItem(fragPosition,true)
        }
        binding.statPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                fragPosition = position
                binding.statDwmText.text = dwmTextArray[fragPosition]
            }
        })

        binding.statDwmButtonLeft.setOnClickListener(clickListener)
        binding.statDwmButtonRight.setOnClickListener(clickListener)

        return binding.root
    }

    fun Int.dpToPx(displayMetrics: DisplayMetrics): Int = (this * displayMetrics.density).toInt()

    private inner class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa){
        override fun createFragment(position: Int): Fragment {
            return when(position){
                0 -> StatDayFragment()
                1 -> StatWeekFragment()
                2  -> StatMonthFragment()
                else -> StatDayFragment()
            }
        }
        override fun getItemCount(): Int = 3
    }



}
