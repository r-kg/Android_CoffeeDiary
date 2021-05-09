package com.teamds.coffeecounter.ui

import android.os.Bundle
import android.view.Menu
import androidx.navigation.findNavController
import com.teamds.coffeecounter.BaseActivity
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.databinding.ActivityMainBinding
import com.teamds.coffeecounter.domain.event.PageAddEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.hide()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        binding.bottomBar.setupWithNavController(menu!!, navController = findNavController(R.id.nav_host_fragment))
        return true
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onPageAddEvent(event: PageAddEvent){
        binding.navHostFragment.findNavController().navigate(R.id.home_fragment)
    }
}
