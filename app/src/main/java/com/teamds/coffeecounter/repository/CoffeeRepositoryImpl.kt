package com.teamds.coffeecounter.repository

import com.teamds.coffeecounter.domain.entity.Coffee

class CoffeeRepositoryImpl : CoffeeRepository{
    override suspend fun saveCoffee(coffee: Coffee) {
        TODO("Not yet implemented")
    }

    override suspend fun getCoffeeList(): List<Coffee> {
        TODO("Not yet implemented")
    }
}