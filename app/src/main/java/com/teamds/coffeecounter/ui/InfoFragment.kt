package com.teamds.coffeecounter.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamds.coffeecounter.BaseFragment
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.databinding.InfoFragmentBinding
import com.teamds.coffeecounter.viewmodel.InfoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class InfoFragment : BaseFragment<InfoFragmentBinding>(R.layout.info_fragment) {

    private val viewModel: InfoViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}