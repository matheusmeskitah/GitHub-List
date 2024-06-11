package com.meskitah.githublist.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.meskitah.githublist.domain.model.PullRequest
import com.meskitah.githublist.domain.model.Repository
import com.meskitah.githublist.domain.model.UserPR
import com.meskitah.githublist.domain.repository.GitHubRepository
import kotlinx.coroutines.flow.Flow

class GitHubRepositoryFake : GitHubRepository {

    var shouldReturnError = false

    override suspend fun getRepos(): Flow<PagingData<Repository>> {
        return Pager(
            config = PagingConfig(pageSize = 1, prefetchDistance = 2),
            pagingSourceFactory = { RepositoriesPagingSourceFake(shouldReturnError) }
        ).flow
    }

    override suspend fun getPullRequests(
        user: String,
        repositoryName: String
    ): Result<List<PullRequest>> {
        return if (shouldReturnError) {
            Result.failure(Throwable())
        } else {
            Result.success(
                listOf(
                    PullRequest(
                        1,
                        "user",
                        "2024-05-29T17:04:52Z",
                        "Title",
                        UserPR(22, null, "Login")
                    )
                )
            )
        }
    }
}
