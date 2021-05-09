package com.teamds.coffeecounter.data.local.dao

import androidx.room.*
import com.teamds.coffeecounter.data.local.entity.CoffeeEntity
import com.teamds.coffeecounter.data.local.entity.StoreEntity

@Dao
interface CoffeeDao {
    @Query("SELECT * FROM coffee")
    fun getAll(): List<CoffeeEntity>

    @Query("SELECT * FROM coffee WHERE name = (:name)")
    fun getByName(name : String) : CoffeeEntity?

    @Query("UPDATE coffee SET count = count + 1 WHERE id = (:id)")
    fun updateCount(id : Int)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(item: CoffeeEntity) : Long

    @Delete
    fun delete(item: CoffeeEntity)

    @Transaction
    fun insertOrUpdate(coffee : CoffeeEntity) : Int{
        val item = getByName(coffee.name)
        if(item == null){
            return insert(coffee).toInt()
        }else{
            updateCount(item.id)
            return item.id
        }
    }
}