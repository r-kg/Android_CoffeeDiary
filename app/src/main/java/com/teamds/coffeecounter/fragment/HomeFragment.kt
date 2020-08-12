package com.teamds.coffeecounter.fragment

import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.databinding.FragmentHomeBinding
import com.teamds.coffeecounter.databinding.LayoutMainBottomSheetBinding
import com.teamds.coffeecounter.presenter.HomePresenter
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class HomeFragment : Fragment(), HomePresenter.View {

    lateinit var binding : FragmentHomeBinding
    lateinit var textCoffee : TextView
    lateinit var textCaf : TextView
    var constraintSet: ConstraintSet = ConstraintSet()
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
        constraintSet.clone(binding.root)

        /*----------------------------------------------------*/
        presenter.updateCountText(this.requireContext())

        binding.textDate.text = date.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 EE요일", Locale.KOREA))


        //*--------------------------fab---------------------*/
        binding.fabAdd.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(this.requireContext(), R.style.BottomSheet)
            val bottomSheetBinding = LayoutMainBottomSheetBinding.inflate(layoutInflater)
            var touchTrigger = true
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
                if(touchTrigger) {
                    touchTrigger=false
                    presenter.insertCoffeeData(
                        this.requireContext(),
                        bottomSheetBinding.rgCoffee.checkedRadioButtonId,
                        bottomSheetBinding.npSize.value,
                        bottomSheetBinding.npShot.value
                    )
                    bottomSheetDialog.hide()
                }
            }
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.imgFlow.setBackgroundResource(R.drawable.coffeeflow_anim)
        val anim = binding.imgFlow.background as AnimationDrawable

        anim.start()
    }

    /*-------------------Presenter Contract----------------------*/
    override fun setCountText(coffee: Int, caffeine: Int) {
        var bias = 1.0 - (0.25 * coffee)

        if(bias <= 0) {
            binding.textTitle.text = "커피그만"
            binding.textTitle.setTextColor(Color.WHITE)
            binding.textDate.setTextColor(Color.WHITE)
            constraintSet.setVisibility(R.id.text_warn,View.VISIBLE)
            bias = 0.0
        }

        constraintSet.setVerticalBias(R.id.img_flow,bias.toFloat())

        val transition  = AutoTransition()
        transition.duration = 500
        transition.interpolator = AccelerateDecelerateInterpolator()
        TransitionManager.beginDelayedTransition(binding.root, transition)

        constraintSet.applyTo(binding.root)

        textCoffee.text = "$coffee"
        textCaf.text = "$caffeine"
    }

}