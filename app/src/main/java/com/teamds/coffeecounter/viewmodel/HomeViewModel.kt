package com.teamds.coffeecounter.viewmodel

import androidx.lifecycle.MutableLiveData
import com.teamds.coffeecounter.BaseViewModel
import com.teamds.coffeecounter.domain.entity.Page
import com.teamds.coffeecounter.repository.PageRepository

class HomeViewModel(private val pageRepository : PageRepository) : BaseViewModel() {

    val pageList = MutableLiveData<List<Page>>()

}