package com.teamds.coffeecounter.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.SimpleDrawerListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.databinding.ActivityMainBinding
import com.teamds.coffeecounter.databinding.BottomSheetBinding
import com.teamds.coffeecounter.fragment.HomeFragment
import com.teamds.coffeecounter.fragment.ReportFragment
import com.teamds.coffeecounter.presenter.MainPresenter

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainPresenter.View {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView : NavigationView
    private lateinit var toolbar : Toolbar
    private lateinit var contentView : ConstraintLayout
    private lateinit var presenter: MainPresenter
    lateinit var binding: ActivityMainBinding
    val END_SCALE = 0.7f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //--------------------Ads----------------------------//
        MobileAds.initialize(this)
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)


        /*---------------------Hooks--------------------------*/
        drawerLayout = binding.mainDrawer
        navigationView = binding.navView
        toolbar = binding.mainActionbar.root as Toolbar
        contentView = binding.content
        presenter = MainPresenter(this)

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

        binding.fabAdd.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheet)
            val bottomSheetBinding = BottomSheetBinding.inflate(layoutInflater)
            bottomSheetDialog.setContentView(bottomSheetBinding.root)
            bottomSheetDialog.show()

            bottomSheetBinding.rgCoffee.setOnCheckedChangeListener { group, checkedId ->
                bottomSheetBinding.fabConfirm.let{
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        it.background.setTint(resources.getColor(R.color.colorAccent))
                    }
                    it.isClickable = true
                    it.text = "DONE"
                    it.extend()
                }

            }
        }

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
                binding.fabAdd.show()
            }
            R.id.nav_report -> {
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                    ReportFragment()
                ).commit()
                binding.fabAdd.hide()
            }
            R.id.nav_setting -> {
                val intent = Intent(this,SettingActivity()::class.java)
                startActivity(intent)
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }



}
