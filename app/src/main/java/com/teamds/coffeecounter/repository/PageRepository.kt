package com.teamds.coffeecounter.repository

import com.teamds.coffeecounter.domain.entity.Page
import java.time.LocalDateTime

interface PageRepository {
    suspend fun getPage(id : Int)
    suspend fun getPageList(currentPage : Int, rowsPerPage: Int) : List<Page>
    suspend fun savePage(storeId : Int, coffeeId : Int, imageUri : String, dateTime: LocalDateTime) : Long
    suspend fun deletePage(id : Int)
}