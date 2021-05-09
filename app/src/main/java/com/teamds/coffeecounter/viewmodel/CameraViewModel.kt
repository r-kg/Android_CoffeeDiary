package com.teamds.coffeecounter.viewmodel

import androidx.lifecycle.viewModelScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teamds.coffeecounter.BaseViewModel
import com.teamds.coffeecounter.domain.event.PageAddEvent
import com.teamds.coffeecounter.repository.CoffeeRepository
import com.teamds.coffeecounter.repository.PageRepository
import com.teamds.coffeecounter.repository.StoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import java.time.LocalDateTime

class CameraViewModel(
    private val pageRepository: PageRepository,
    private val storeRepository: StoreRepository,
    private val coffeeRepository: CoffeeRepository
) : BaseViewModel() {

    fun savePage(bottomSheet: BottomSheetDialogFragment, storeName : String, coffeeName : String, imageUri : String){
        val dateTime = LocalDateTime.now()
        var id : Long = 0
        showLoading()

        viewModelScope.launch(Dispatchers.IO) {
            val storeId = storeRepository.saveStore(storeName)
            val coffeeId = coffeeRepository.saveCoffee(coffeeName)

            id = pageRepository.savePage(storeId, coffeeId, imageUri, dateTime)
        }

        bottomSheet.dismiss()
        hideLoading()
        EventBus.getDefault().post(PageAddEvent(id.toInt()))
    }
}