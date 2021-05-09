package com.teamds.coffeecounter.repository

import com.teamds.coffeecounter.data.local.dao.StoreDao
import com.teamds.coffeecounter.data.local.entity.StoreEntity
import com.teamds.coffeecounter.domain.entity.Store

class StoreRepositoryImpl(private val localDataSource : StoreDao) : StoreRepository {
    override suspend fun saveStore(storeName: String) : Int {
        val store = Store(0, storeName, 1)
        val id = localDataSource.insertOrUpdate(StoreEntity.fromDomain(store))

        return id
    }

    override suspend fun getStoreList(): List<Store> {
        TODO("Not yet implemented")
    }

}