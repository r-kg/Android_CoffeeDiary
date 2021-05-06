package com.teamds.coffeecounter.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teamds.coffeecounter.BaseFragment
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.adapter.HomeGridAdapter
import com.teamds.coffeecounter.databinding.HomeFragmentBinding
import com.teamds.coffeecounter.viewmodel.HomeViewModel

class HomeFragment : BaseFragment<HomeFragmentBinding>(R.layout.home_fragment) {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.gridView.adapter = HomeGridAdapter(context, arrayListOf())
    }

}