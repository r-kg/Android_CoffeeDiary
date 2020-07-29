package com.teamds.coffeecounter.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.databinding.ActivitySettingBinding
import com.teamds.coffeecounter.model.coffeedb.CoffeeDatabase
import com.teamds.coffeecounter.model.dailydb.DailyData
import com.teamds.coffeecounter.model.dailydb.DailyDatabase
import java.time.LocalDate

lateinit var getintent: Intent

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

        getintent = intent

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

    override fun onPreferenceTreeClick(preference: Preference?): Boolean {

        when(preference?.key){
            "btn_reset" -> {
                val localDateNow: LocalDate = LocalDate.now()
                val builder = AlertDialog.Builder(this.requireContext())
                builder.run{
                    setTitle("데이터 초기화")
                    setMessage("초기화된 데이터는 복구 할 수 없습니다")
                    setPositiveButton("YES") { dialog, which -> // Do nothing but close the dialog
                        DailyDatabase.getInstance(context)?.dailyDao()?.reset()
                        CoffeeDatabase.getInstance(context)?.coffeeDao()?.reset()
                        DailyDatabase.getInstance(context)?.dailyDao()?.insert(DailyData(localDateNow,0,0,0))
                        dialog.dismiss()
                    }
                    setNegativeButton("NO") { dialog, which -> // Do nothing but close the dialog
                        dialog.dismiss()
                    }
                }

                val alert = builder.create()
                alert.show()

            }
        }

        return false
    }
}


