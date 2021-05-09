package com.teamds.coffeecounter.repository

import android.util.Log
import com.teamds.coffeecounter.data.local.dao.StoreDao
import com.teamds.coffeecounter.domain.entity.Store

class StoreRepositoryImpl(val localDataSource : StoreDao) : StoreRepository {
    override suspend fun saveStore(store: Store) {

    }

    override suspend fun getStoreList(): List<Store> {
        TODO("Not yet implemented")
    }

}