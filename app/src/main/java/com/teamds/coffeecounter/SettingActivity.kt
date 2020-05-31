package com.teamds.coffeecounter

import android.app.ActivityOptions
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import android.view.MenuItem
import android.view.Window
import androidx.appcompat.widget.Toolbar
import androidx.preference.PreferenceFragmentCompat
import com.teamds.coffeecounter.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.content_preference,SettingFragment()).commit()

        setSupportActionBar(binding.settingActionbar.root as Toolbar)
        binding.settingActionbar.actionbarText.text = "환경설정"
        supportActionBar?.setDisplayShowTitleEnabled(false)         //기본 타이틀 비활성화
        supportActionBar?.setDisplayHomeAsUpEnabled(true)           //Back button 활성화
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}

class SettingFragment : PreferenceFragmentCompat(){
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.main_preference, rootKey)
    }
}
