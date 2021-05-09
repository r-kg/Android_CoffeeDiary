package com.teamds.coffeecounter.ui.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.databinding.CameraBottomSheetBinding
import com.teamds.coffeecounter.viewmodel.CameraViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CameraBottomSheet : BottomSheetDialogFragment() {

    private val viewModel: CameraViewModel by sharedViewModel()
    lateinit var binding : CameraBottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.camera_bottom_sheet, container, false)

        binding.btnConfirm.setOnClickListener {
            viewModel.savePage("test","test")
        }


        return binding.root
    }
}