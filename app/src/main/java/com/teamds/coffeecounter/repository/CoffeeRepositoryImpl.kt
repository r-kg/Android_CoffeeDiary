package com.teamds.coffeecounter.repository

import com.teamds.coffeecounter.data.local.dao.CoffeeDao
import com.teamds.coffeecounter.data.local.entity.CoffeeEntity
import com.teamds.coffeecounter.domain.entity.Coffee

class CoffeeRepositoryImpl(val localDataSource : CoffeeDao) : CoffeeRepository{
    override suspend fun saveCoffee(coffeeName: String) : Int {
        val coffee = Coffee(0, coffeeName, 1)
        val id = localDataSource.insertOrUpdate(CoffeeEntity.fromDomain(coffee))
        return id
    }

    override suspend fun getCoffeeList(): List<Coffee> {
        TODO("Not yet implemented")
    }
}