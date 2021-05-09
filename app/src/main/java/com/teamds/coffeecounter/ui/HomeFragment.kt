package com.teamds.coffeecounter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.databinding.BindingAdapter
import com.teamds.coffeecounter.BaseFragment
import com.teamds.coffeecounter.R
import com.teamds.coffeecounter.adapter.HomeGridAdapter
import com.teamds.coffeecounter.databinding.HomeFragmentBinding
import com.teamds.coffeecounter.domain.entity.Page
import com.teamds.coffeecounter.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeFragmentBinding>(R.layout.home_fragment) {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.vm = viewModel
        viewModel.loadPageList()
    }

    object BindingConversion{
        @BindingAdapter("page_list")
        @JvmStatic
        fun loadPageList(gridView: GridView, pageList: ArrayList<Page>){
            //val adapter = gridView.adapter as HomeGridAdapter
            gridView.adapter = HomeGridAdapter(gridView.context, pageList)
        }
    }

}