package com.teamds.coffeecounter.ui

import android.os.Bundle
import android.view.Menu
import androidx.navigation.findNavController
import com.teamds.coffeecounter.BaseActivity
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {


        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.hide()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        binding.bottomBar.setupWithNavController(menu!!, navController = findNavController(R.id.nav_host_fragment))
        return true
    }
}
