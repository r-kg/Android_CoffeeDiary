package com.teamds.coffeecounter.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.teamds.coffeecounter.fragment.main.HomeFragment
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.fragment.main.StatFragment
import com.teamds.coffeecounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //기본 Action Bar 설정
        setSupportActionBar(binding.mainActionbar.root as Toolbar);
        supportActionBar?.run{
            setDisplayShowTitleEnabled(false)         //기본 타이틀 비활성화
            setHomeAsUpIndicator(R.drawable.ic_home)  //Back button ic -> Home ic
        }

        //Tab Layout Data Array
        val tabLayoutTextArray = arrayOf("홈","통계")
        val tabLayoutIconArray = arrayOf(
            R.drawable.ic_home,
            R.drawable.ic_timeline
        )
        
        //ViewPager와 Adapter연결
        val adapter = ViewPagerAdapter(this)
        //val transformation = ViewPagerTransformation()
        binding.mainViewpager.adapter = adapter
        //binding.mainViewpager.setPageTransformer(transformation)
        binding.mainViewpager.isUserInputEnabled = false    //Disable Swiping
        
        //ViewPager와 TabLayout 연결
        TabLayoutMediator(binding.mainTab, binding.mainViewpager){tab, position ->
            //tab.text = tabLayoutTextArray[position]
            tab.setIcon(tabLayoutIconArray[position])
        }.attach()

    }


    //App ActionBar menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_actionbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.actionbar_setting){
            startActivity(Intent(this, SettingActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    //ViewPager2 Adapter Inner Class
    private inner class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa){
        override fun createFragment(position: Int): Fragment {
            return when(position){
                0 -> HomeFragment()
                1 -> StatFragment()
                else -> HomeFragment()
            }
        }
        override fun getItemCount(): Int = 2

    }
}
