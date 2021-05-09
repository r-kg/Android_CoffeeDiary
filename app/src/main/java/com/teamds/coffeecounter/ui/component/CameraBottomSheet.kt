package com.teamds.coffeecounter.ui.component

import android.net.Uri
import android.os.Bundle
import android.util.Log
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
import java.io.File

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
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel

        val uriString = arguments?.getString("uri")
        val uri = Uri.parse(uriString)

        binding.image.setImageURI(Uri.parse(uriString))

        binding.btnConfirm.setOnClickListener {
            val storeName = binding.textInputStore.text.toString()
            val coffeeName = binding.textInputCoffee.text.toString()
            viewModel.savePage(storeName,coffeeName, uriString!!)
        }

        binding.btnCancel.setOnClickListener {
            val file = File(uri.path!!)
            file.delete()
            dismiss()
        }

        return binding.root
    }
}