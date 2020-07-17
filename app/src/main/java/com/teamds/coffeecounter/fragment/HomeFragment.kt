package com.teamds.coffeecounter.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.databinding.FragmentHomeBinding
import com.teamds.coffeecounter.databinding.LayoutMainBottomSheetBinding
import com.teamds.coffeecounter.presenter.HomePresenter
import java.time.LocalDate
import java.util.*


class HomeFragment : Fragment(), HomePresenter.View {

    lateinit var binding : FragmentHomeBinding
    lateinit var textCoffee : TextView
    lateinit var textCaf : TextView
    private lateinit var presenter: HomePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        /*---------------------Hooks--------------------------*/
        presenter = HomePresenter(this)
        textCoffee = binding.textCoffeeCount
        textCaf = binding.textCaffeineCount

        val date = LocalDate.now()
        binding.textDate.text = "$date"

        /*----------------------------------------------------*/
        presenter.updateCountText(this.requireContext())


        //*--------------------------fab---------------------*/
        binding.fabAdd.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this.requireContext(), R.style.BottomSheet)
            val bottomSheetBinding = LayoutMainBottomSheetBinding.inflate(layoutInflater)
            bottomSheetDialog.setContentView(bottomSheetBinding.root)

            bottomSheetBinding.npSize.run{
                wrapSelectorWheel = false
                minValue=0
                maxValue=4
                displayedValues = arrayOf("캔/숏","톨/S","그란데/M","벤티/L","리터/1L+")
                value = 1
            }

            bottomSheetBinding.npShot.run{
                wrapSelectorWheel = false
                minValue=0
                maxValue=5
                displayedValues = arrayOf("추가 안함","+1","+2","+3","+4","+5 이상")
                value = 0
            }
            bottomSheetDialog.show()

            bottomSheetBinding.rgCoffee.setOnCheckedChangeListener { group, checkedId ->
                bottomSheetBinding.fabConfirm.let{
                    it.background.setTint(resources.getColor(R.color.colorAccent))
                    it.isEnabled = true
                    it.text = "DONE"
                    it.extend()
                }

            }

            bottomSheetBinding.fabConfirm.setOnClickListener {
                presenter.InsertCoffeeData(this.requireContext(),bottomSheetBinding.rgCoffee.checkedRadioButtonId,bottomSheetBinding.npSize.value,bottomSheetBinding.npShot.value)
                bottomSheetDialog.hide()
            }
        }

        return binding.root
    }


    /*-------------------Presenter Contract----------------------*/
    override fun setCountText(coffee: Int, caffeine: Int) {
        textCoffee.text = "$coffee"
        textCaf.text = "$caffeine"
    }

}