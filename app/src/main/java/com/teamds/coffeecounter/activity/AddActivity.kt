package com.teamds.coffeecounter.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.teamds.coffeecounter.presenter.HomePresenter
import com.teamds.coffeecounter.databinding.ActivityAddBinding


class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        //타이틀바 //
        binding.mainActionbar.actionbarText.text = "커피 기록"
        setSupportActionBar(binding.mainActionbar.root as Toolbar);
        supportActionBar?.run{
            setDisplayShowTitleEnabled(false)         //기본 타이틀 비활성화
            setDisplayHomeAsUpEnabled(true)
        }


        //두 RadioGroup 체크박스 공유하도록 listener 설정
        lateinit var listener1 : RadioGroup.OnCheckedChangeListener
        lateinit var listener2 : RadioGroup.OnCheckedChangeListener

        listener1 = RadioGroup.OnCheckedChangeListener { group, checkedId ->
            binding.fabAdd.show()
                if (checkedId != -1) {
                    binding.rg2.run{
                        setOnCheckedChangeListener(null) // remove the listener before clearing so we don't throw that stackoverflow exception
                        clearCheck() // clear the second RadioGroup
                        setOnCheckedChangeListener(listener2) //reset the listener
                    }
                }
            }

        listener2 = RadioGroup.OnCheckedChangeListener { group, checkedId ->
            binding.fabAdd.show()
                if (checkedId != -1) {
                    binding.rg1.run{
                        setOnCheckedChangeListener(null)
                        clearCheck()
                        setOnCheckedChangeListener(listener1)
                    }
                }
            }

        binding.rg1.setOnCheckedChangeListener(listener1)
        binding.rg2.setOnCheckedChangeListener(listener2)

        binding.fabAdd.setOnClickListener {

            finish()
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