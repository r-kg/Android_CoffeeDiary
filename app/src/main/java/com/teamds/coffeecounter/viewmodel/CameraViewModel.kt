package com.teamds.coffeecounter.viewmodel

import com.teamds.coffeecounter.BaseViewModel
import com.teamds.coffeecounter.repository.CoffeeRepository
import com.teamds.coffeecounter.repository.PageRepository
import com.teamds.coffeecounter.repository.StoreRepository

class CameraViewModel(
    private val pageRepository: PageRepository,
    private val storeRepository: StoreRepository,
    private val coffeeRepository: CoffeeRepository
) : BaseViewModel() {



}