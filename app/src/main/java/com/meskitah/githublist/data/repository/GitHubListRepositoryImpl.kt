package com.meskitah.githublist.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.meskitah.githublist.data.remote.GitHubListApi
import com.meskitah.githublist.domain.model.Repository
import com.meskitah.githublist.domain.repository.GitHubListRepository
import kotlinx.coroutines.flow.Flow

class GitHubListRepositoryImpl(
    private val api: GitHubListApi
) : GitHubListRepository {

    override suspend fun getRepos(): Flow<PagingData<Repository>> {
        return Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = { RepositoriesPagingSource(api) }
        ).flow
    }
}