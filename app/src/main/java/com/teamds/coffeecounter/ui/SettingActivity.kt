package com.teamds.coffeecounter.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.databinding.ActivitySettingBinding
import com.teamds.coffeecounter.data.local.CoffeeDatabase
import com.teamds.coffeecounter.model.dailydb.DailyData
import com.teamds.coffeecounter.data.local.DailyDatabase
import java.time.LocalDate

class SettingActivity : AppCompatActivity() {

    lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*--------------------Tool bar-------------------------*/
        setSupportActionBar(binding.settingActionbar.root)
        binding.settingActionbar.actionbarText.text = "환경설정"
        supportActionBar?.run {
            setDisplayShowTitleEnabled(false)   //기본 타이틀 비활성화
            setDisplayHomeAsUpEnabled(true)     //Back button 활성화
        }
        
        /*-----Preference Fragment 로부터 환경설정 XML 불러오기------*/
        supportFragmentManager.beginTransaction().replace(
            R.id.content_preference,
            SettingFragment()
        ).commit()


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}


/*---------------------Preference Fragment----------------------------*/

class SettingFragment : PreferenceFragmentCompat(){
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.main_preference, rootKey)
    }


    /*-------------Preference Button Click Listener------------------*/
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


