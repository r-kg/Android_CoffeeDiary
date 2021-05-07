package com.teamds.coffeecounter.repository

import com.teamds.coffeecounter.domain.entity.Page

interface PageRepository {
    suspend fun getPage(id : Int)
    suspend fun getPageList(currentPage : Int, rowsPerPage: Int) : List<Page>
    suspend fun savePage(page: Page)
    suspend fun deletePage(id : Int)
}