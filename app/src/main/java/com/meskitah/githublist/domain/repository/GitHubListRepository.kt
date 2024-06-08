package com.meskitah.githublist.domain.repository

import androidx.paging.PagingData
import com.meskitah.githublist.domain.model.Repository
import kotlinx.coroutines.flow.Flow

interface GitHubListRepository {
    suspend fun getRepos(): Flow<PagingData<Repository>>
}