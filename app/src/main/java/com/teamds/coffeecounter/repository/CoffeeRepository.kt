package com.teamds.coffeecounter.repository

import com.teamds.coffeecounter.domain.entity.Coffee

interface CoffeeRepository {
    suspend fun saveCoffee(coffee: Coffee)
    suspend fun getCoffeeList() : List<Coffee>
}