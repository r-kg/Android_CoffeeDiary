package com.teamds.coffeecounter.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.teamds.coffeecounter.domain.entity.Store

@Entity(tableName = "store")
data class StoreEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val count: Int
) {
}

fun StoreEntity.toDomain() : Store {
    return Store(
        id = this.id,
        name = this.name,
        count = this.count
    )
}

fun StoreEntity.fromDomain(store: Store) : StoreEntity {
    return StoreEntity(
        id = store.id ?: 0,
        name = store.name,
        count = store.count
    )
}