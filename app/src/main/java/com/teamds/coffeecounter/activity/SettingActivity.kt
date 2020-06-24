package com.teamds.coffeecounter.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.preference.PreferenceFragmentCompat
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.settingActionbar.actionbarText.text = "환경설정"
        setSupportActionBar(binding.settingActionbar.root as Toolbar)
        supportActionBar?.run {
            setDisplayShowTitleEnabled(false)   //기본 타이틀 비활성화
            setDisplayHomeAsUpEnabled(true)     //Back button 활성화
        }
        
        //Preference Fragment 로부터 환경설정 XML 불러오기
        supportFragmentManager.beginTransaction().replace(
            R.id.content_preference,
            SettingFragment()
        ).commit()

    }
    
    //Toolbar back button menu 리스너
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()        // Activity 종료
        }
        return super.onOptionsItemSelected(item)
    }


}

class SettingFragment : PreferenceFragmentCompat(){
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.main_preference, rootKey)
    }
}


