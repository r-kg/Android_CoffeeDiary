package com.teamds.coffeecounter.repository

import com.teamds.coffeecounter.domain.entity.Page

class PageRepositoryImpl : PageRepository{
    override suspend fun getPage(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getPageList(currentPage: Int, rowsPerPage: Int): List<Page> {
        TODO("Not yet implemented")
    }

    override suspend fun savePage(page: Page) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePage(id: Int) {
        TODO("Not yet implemented")
    }
}