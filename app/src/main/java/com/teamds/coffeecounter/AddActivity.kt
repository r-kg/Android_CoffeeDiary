package com.teamds.coffeecounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.teamds.coffeecounter.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setSupportActionBar(binding.addActionbar.root as Toolbar)
        binding.addActionbar.actionbarText.text = "커피 기록"
        supportActionBar?.setDisplayShowTitleEnabled(false)         //기본 타이틀 비활성화
        supportActionBar?.setDisplayHomeAsUpEnabled(true)           //Back button 활성화

        binding.seekbarSize.setIndicatorTextFormat("\${TICK_TEXT}")





    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}