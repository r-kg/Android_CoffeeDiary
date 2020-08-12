package com.teamds.coffeecounter.activity

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.SimpleDrawerListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.material.navigation.NavigationView
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.databinding.ActivityMainBinding
import com.teamds.coffeecounter.fragment.HomeFragment
import com.teamds.coffeecounter.fragment.ReportFragment


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView : NavigationView
    private lateinit var toolbar : Toolbar
    private lateinit var contentView : ConstraintLayout
    lateinit var binding: ActivityMainBinding
    val END_SCALE = 0.7f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*---------------------Hooks--------------------------*/
        drawerLayout = binding.mainDrawer
        navigationView = binding.navView
        toolbar = binding.mainActionbar.root as Toolbar
        contentView = binding.content

        //--------------------Ads----------------------------//
        MobileAds.initialize(this)
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)


        /*--------------------Tool bar-------------------------*/
        setSupportActionBar(toolbar);
        supportActionBar?.run{
            setDisplayShowTitleEnabled(false)         //기본 타이틀 비활성화
            setHomeAsUpIndicator(R.drawable.ic_home)  //Back button ic -> Home ic
        }

        /*---------------Navigation Drawer Menu------------------*/
        navigationView.bringToFront()
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if(savedInstanceState == null) {
            navigationView.setCheckedItem(R.id.nav_home)
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                HomeFragment()
            ).commit()
        }

        //-----------------drawer----------------------------//
        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        //drawerLayout.setScrimColor(Color.TRANSPARENT);

        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        //drawerLayout.setScrimColor(resources.getColor(R.color.colorPrimary))
        drawerLayout.addDrawerListener(object : SimpleDrawerListener() {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

                // Scale the View based on current slide offset
                val diffScaledOffset: Float = slideOffset * (1 - END_SCALE)
                val offsetScale = 1 - diffScaledOffset
                contentView.scaleX = offsetScale
                contentView.scaleY = offsetScale

                // Translate the View, accounting for the scaled width
                val xOffset = drawerView.width * slideOffset
                val xOffsetDiff: Float = contentView.width * diffScaledOffset / 2
                val xTranslation = xOffset - xOffsetDiff
                contentView.translationX = xTranslation
            }
        })
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
            R.id.nav_home -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                    HomeFragment()
                ).commit()
            }
            R.id.nav_report -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                    ReportFragment()
                ).commit()
            }
            R.id.nav_setting -> {
                val intent = Intent(this,SettingActivity()::class.java)
                startActivity(intent)
            }

            /*
            R.id.nav_share ->{

            }

             */
            R.id.nav_feedback ->{
                val TO = arrayOf("gundaegi2@gmail.com")
                val CC = arrayOf("")
                val emailIntent = Intent(Intent.ACTION_SEND)
                emailIntent.data = Uri.parse("mailto:")
                emailIntent.type = "text/plain"

                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO)
                emailIntent.putExtra(Intent.EXTRA_CC, CC)
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "커피몇잔 Feedback Email")
                emailIntent.putExtra(Intent.EXTRA_TEXT, "")

                try {
                    startActivity(Intent.createChooser(emailIntent, "보내는중..."))
                    finish()
                } catch (ex: ActivityNotFoundException) {
                    Toast.makeText(
                        this@MainActivity,
                        "There is no email client installed.", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
