package com.teamds.coffeecounter.ui.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.databinding.CameraBottomSheetBinding

class CameraBottomSheet : BottomSheetDialogFragment() {

    lateinit var binding : CameraBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.camera_bottom_sheet, container, false)
        return binding.root
    }
}