package com.teamds.coffeecounter.repository

import com.teamds.coffeecounter.data.local.dao.CoffeeDao
import com.teamds.coffeecounter.domain.entity.Coffee

class CoffeeRepositoryImpl(val localDataSource : CoffeeDao) : CoffeeRepository{
    override suspend fun saveCoffee(coffee: Coffee) {
        TODO("Not yet implemented")
    }

    override suspend fun getCoffeeList(): List<Coffee> {
        TODO("Not yet implemented")
    }
}