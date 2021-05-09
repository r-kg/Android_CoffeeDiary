package com.teamds.coffeecounter.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.teamds.coffeecounter.domain.entity.Coffee

@Entity(tableName = "coffee")
data class CoffeeEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val count: Int
) {
    companion object{
        fun fromDomain(coffee: Coffee) : CoffeeEntity {
            return CoffeeEntity(
                id = coffee.id ?: 0,
                name = coffee.name,
                count = coffee.count
            )
        }
    }
}

fun CoffeeEntity.toDomain() : Coffee {
    return Coffee(
        id = this.id,
        name = this.name,
        count = this.count
    )
}
