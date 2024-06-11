package com.meskitah.githublist.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.meskitah.githublist.data.mapper.toRepository
import com.meskitah.githublist.data.remote.GitHubApi
import com.meskitah.githublist.domain.model.Repository

class RepositoriesPagingSource(
    private val api: GitHubApi
) : PagingSource<Int, Repository>() {
    override suspend fun load(params: LoadParams<Int>) : LoadResult<Int, Repository>   {
        return try {
            val currentPage = params.key ?: 1
            val gitResult = api.getRepos(page = currentPage)

            gitResult?.items
                ?.mapNotNull { it.toRepository() }
                ?.let {
                    LoadResult.Page(
                        data = it,
                        prevKey = if (currentPage == 1) null else currentPage - 1,
                        nextKey = if (it.isEmpty()) null else currentPage + 1
                    )
                }
                ?: throw Exception()
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        return state.anchorPosition
    }
}