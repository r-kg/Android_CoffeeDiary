package com.teamds.coffeecounter.data.local.dao

import androidx.room.*
import com.teamds.coffeecounter.data.local.entity.StoreEntity
import com.teamds.coffeecounter.domain.entity.Store

@Dao
interface StoreDao {
    @Query("SELECT * FROM store")
    fun getAll(): List<StoreEntity>

    @Query("SELECT * FROM store WHERE name = (:name)")
    fun getByName(name : String) : StoreEntity?

    @Query("UPDATE store SET count = count + 1 WHERE id = (:id)")
    fun updateCount(id : Int)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(item: StoreEntity) : Long

    @Delete
    fun delete(item: StoreEntity)

    @Transaction
    fun insertOrUpdate(store : StoreEntity) : Int{
        val item = getByName(store.name)
        if(item == null){
            return insert(store).toInt()
        }else{
            updateCount(item.id)
            return item.id
        }
    }
}