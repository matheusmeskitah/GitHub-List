package com.meskitah.githublist.domain.repository

import android.content.Context
import androidx.paging.PagingData
import com.meskitah.githublist.domain.model.PullRequest
import com.meskitah.githublist.domain.model.Repository
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {
    suspend fun getRepos(): Flow<PagingData<Repository>>
    suspend fun getPullRequests(
        user: String,
        repositoryName: String,
        context: Context
    ): Result<List<PullRequest>>
}