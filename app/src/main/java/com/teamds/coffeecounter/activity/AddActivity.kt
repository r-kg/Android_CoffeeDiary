package com.teamds.coffeecounter.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        //타이틀바 설정//
        binding.mainActionbar.actionbarText.text = "커피 기록"
        setSupportActionBar(binding.mainActionbar.root as Toolbar);
        supportActionBar?.run{
            setDisplayShowTitleEnabled(false)         //기본 타이틀 비활성화
            setDisplayHomeAsUpEnabled(true)
        }
    }

    //Toolbar back button menu 리스너
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()        // Activity 종료
        }
        return super.onOptionsItemSelected(item)
    }
}