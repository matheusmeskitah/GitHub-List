package com.meskitah.githublist.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.meskitah.githublist.domain.model.Owner
import com.meskitah.githublist.domain.model.Repository

class RepositoriesPagingSourceFake(private val isError: Boolean) : PagingSource<Int, Repository>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        return try {
            if (isError)
                throw Exception()

            val currentPage = params.key ?: 1
            val gitResult =
                listOf(Repository(
                    1,
                    "description",
                    2,
                    "Name",
                    Owner(11, null, "login"),
                    3)
                )

            LoadResult.Page(
                data = gitResult,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (gitResult.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        return state.anchorPosition
    }
}