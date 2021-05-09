package com.teamds.coffeecounter.repository

import com.teamds.coffeecounter.domain.entity.Coffee

interface CoffeeRepository {
    suspend fun saveCoffee(coffeeName: String) : Int
    suspend fun getCoffeeList() : List<Coffee>
}