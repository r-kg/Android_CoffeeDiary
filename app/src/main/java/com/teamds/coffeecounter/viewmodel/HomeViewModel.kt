package com.teamds.coffeecounter.viewmodel

import androidx.lifecycle.MutableLiveData
import com.teamds.coffeecounter.BaseViewModel
import com.teamds.coffeecounter.domain.entity.Page

class HomeViewModel : BaseViewModel() {

    val pageList = MutableLiveData<List<Page>>()

}