package com.teamds.coffeecounter.repository

import com.teamds.coffeecounter.data.local.dao.PageDao
import com.teamds.coffeecounter.data.local.entity.PageEntity
import com.teamds.coffeecounter.data.local.entity.toDomain
import com.teamds.coffeecounter.domain.entity.Page
import java.time.LocalDateTime

class PageRepositoryImpl(private val localDataSource : PageDao) : PageRepository{
    override suspend fun getPage(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getPageList(currentPage: Int, rowsPerPage: Int): List<Page> {
        return localDataSource.getPageList(currentPage * rowsPerPage, rowsPerPage)
            .map { pageEntity -> pageEntity.toDomain() }.toList()
    }

    override suspend fun savePage(storeId : Int, coffeeId : Int, imageUri : String, dateTime: LocalDateTime) : Long{
        val page = Page(0, storeId, coffeeId, imageUri, dateTime)
        return localDataSource.insert(PageEntity.fromDomain(page))
    }

    override suspend fun deletePage(id: Int) {
        TODO("Not yet implemented")
    }
}