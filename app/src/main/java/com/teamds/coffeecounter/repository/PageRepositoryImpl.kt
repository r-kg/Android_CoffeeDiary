package com.teamds.coffeecounter.repository

import com.teamds.coffeecounter.data.local.dao.PageDao
import com.teamds.coffeecounter.data.local.entity.toDomain
import com.teamds.coffeecounter.domain.entity.Page

class PageRepositoryImpl(private val localDataSource : PageDao) : PageRepository{
    override suspend fun getPage(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getPageList(currentPage: Int, rowsPerPage: Int): List<Page> {
        val response = localDataSource.getPageList(currentPage * rowsPerPage, rowsPerPage).map { pageEntity -> pageEntity.toDomain() }.toList()
        return response
    }

    override suspend fun savePage(page: Page) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePage(id: Int) {
        TODO("Not yet implemented")
    }
}