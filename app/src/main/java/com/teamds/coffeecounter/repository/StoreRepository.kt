package com.teamds.coffeecounter.repository

import com.teamds.coffeecounter.domain.entity.Store

interface StoreRepository {
    suspend fun saveStore(storeName: String) : Int
    suspend fun getStoreList(): List<Store>
}