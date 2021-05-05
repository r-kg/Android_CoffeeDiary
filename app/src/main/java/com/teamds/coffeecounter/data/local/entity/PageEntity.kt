package com.teamds.coffeecounter.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.teamds.coffeecounter.domain.entity.Page
import java.time.LocalDateTime

@Entity(tableName = "page")
data class PageEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @ColumnInfo
    val shopId: Int,
    @ColumnInfo
    val coffeeId: Int,
    @ColumnInfo
    val imageUrl: String,
    @ColumnInfo
    val dateTime: String,
    ){
}

fun PageEntity.toDomain() : Page{
    return Page(
        id = this.id,
        shopId = this.shopId,
        coffeeId = this.coffeeId,
        imageUrl = this.imageUrl,
        dateTime = LocalDateTime.parse(this.dateTime)
    )
}

fun PageEntity.fromDomain(page : Page) : PageEntity{
    return PageEntity(
        id = page.id ?: 0,
        shopId = page.shopId,
        coffeeId = page.coffeeId,
        imageUrl = page.imageUrl,
        dateTime = page.dateTime.toString()
    )
}
