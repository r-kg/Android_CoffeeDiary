package com.teamds.coffeecounter.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamds.coffeecounter.BaseFragment
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.databinding.CameraFragmentBinding
import com.teamds.coffeecounter.viewmodel.CameraViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CameraFragment : BaseFragment<CameraFragmentBinding>(R.layout.camera_fragment) {

    private val viewModel: CameraViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}