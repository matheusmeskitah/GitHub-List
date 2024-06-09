package com.meskitah.githublist.data.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.meskitah.githublist.data.mapper.toPullRequest
import com.meskitah.githublist.data.remote.GitHubApi
import com.meskitah.githublist.domain.model.PullRequest
import com.meskitah.githublist.domain.model.Repository
import com.meskitah.githublist.domain.repository.GitHubRepository
import kotlinx.coroutines.flow.Flow

class GitHubRepositoryImpl(
    private val api: GitHubApi
) : GitHubRepository {

    override suspend fun getRepos(): Flow<PagingData<Repository>> {
        return Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = { RepositoriesPagingSource(api) }
        ).flow
    }

    override suspend fun getPullRequests(
        user: String,
        repositoryName: String,
        context: Context
    ): Result<List<PullRequest>> {
        return try {
            val prs = api.getPullRequests(user = user, repositoryName = repositoryName)

            Result.success(prs.mapNotNull { it.toPullRequest(context) })
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}