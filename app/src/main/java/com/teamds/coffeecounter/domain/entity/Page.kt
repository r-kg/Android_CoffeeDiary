package com.teamds.coffeecounter.domain.entity

import java.time.LocalDateTime

data class Page(
    val id: Int?,
    val shopId: Int,
    val coffeeId: Int,
    val imageUrl: String,
    val dateTime: LocalDateTime,
) {
}