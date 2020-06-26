package com.teamds.coffeecounter.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        //타이틀바 설정//
        setSupportActionBar(binding.mainActionbar.root as Toolbar);
        binding.mainActionbar.actionbarText.text = "환경설정"
        supportActionBar?.run{
            setDisplayShowTitleEnabled(false)         //기본 타이틀 비활성화
            setHomeAsUpIndicator(R.drawable.ic_home)  //Back button ic -> Home ic
        }



    }
}