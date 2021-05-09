package com.teamds.coffeecounter.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.teamds.coffeecounter.BaseViewModel
import com.teamds.coffeecounter.domain.entity.Coffee
import com.teamds.coffeecounter.domain.entity.Store
import com.teamds.coffeecounter.repository.CoffeeRepository
import com.teamds.coffeecounter.repository.PageRepository
import com.teamds.coffeecounter.repository.StoreRepository
import kotlinx.coroutines.launch

class CameraViewModel(
    private val pageRepository: PageRepository,
    private val storeRepository: StoreRepository,
    private val coffeeRepository: CoffeeRepository
) : BaseViewModel() {

    fun savePage(storeName : String, coffeeName : String){

        viewModelScope.launch {
            val storeId = storeRepository.saveStore(storeName)
            val coffeeId = coffeeRepository.saveCoffee(coffeeName)
        }

    }




}