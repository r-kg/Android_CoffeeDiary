package com.teamds.coffeecounter.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.teamds.coffeecounter.BaseViewModel
import com.teamds.coffeecounter.domain.entity.Page
import com.teamds.coffeecounter.repository.PageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val pageRepository : PageRepository) : BaseViewModel() {
    
    private val row = 30
    private var currentPage = 0

    private val pageList = MutableLiveData<ArrayList<Page>>().apply {
        value = arrayListOf()
    }

    fun loadPageList(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = pageRepository.getPageList(currentPage++, row)
            pageList.value!!.addAll(response)
        }
    }

}