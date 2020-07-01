package com.teamds.coffeecounter.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.multidex.MultiDex
import androidx.room.Room
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.teamds.coffeecounter.fragment.main.HomeFragment
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.database.CoffeeDatabase
import com.teamds.coffeecounter.fragment.main.StatFragment
import com.teamds.coffeecounter.databinding.ActivityMainBinding
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView : NavigationView
    lateinit var toolbar : Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //--------------------Ads----------------------------//
        MobileAds.initialize(this)
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)


        /*---------------------Hooks--------------------------*/
        drawerLayout = binding.mainDrawer
        navigationView = binding.navView
        toolbar = binding.mainActionbar.root as Toolbar

        /*--------------------Tool bar-------------------------*/
        setSupportActionBar(toolbar);
        supportActionBar?.run{
            setDisplayShowTitleEnabled(false)         //기본 타이틀 비활성화
            setHomeAsUpIndicator(R.drawable.ic_home)  //Back button ic -> Home ic
        }

        /*---------------Navigation Drawer Menu------------------*/
        navigationView.bringToFront()
        val toggle : ActionBarDrawerToggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)
        navigationView.setCheckedItem(R.id.nav_home)

    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.nav_setting -> {
                val intent = Intent(this,SettingActivity()::class.java)
                startActivity(intent)
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
